package sdms.security;

import org.slf4j.Logger;
import java.security.Key;
import java.util.Date;
import java.util.function.Function;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

@Component
public class JwtUtil {
	
	private static final Logger Logger = LoggerFactory.getLogger(JwtUtil.class);
	private final int DEFAULT_JWT_EXPIRATION_IN_MINUTES = 120;
	
	private Key secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
	
	@Value("${jwt.expiration.minutes:120}") 
	private String strJwtExpirationInMinutes; 
	
	private int jwtExpirationInMinutes; 

	
    public String generateToken(String username) {
    	
    	// Get the token lifetime in minutes from the properties --------------------
    	try {
    		if( strJwtExpirationInMinutes != null ) jwtExpirationInMinutes = Integer.parseInt( strJwtExpirationInMinutes.trim() );
    	} catch ( NumberFormatException e ) {
    		System.err.println( e.getMessage() + "Set default value : " + DEFAULT_JWT_EXPIRATION_IN_MINUTES );
    		jwtExpirationInMinutes = DEFAULT_JWT_EXPIRATION_IN_MINUTES;
    	}
    	
    	Logger.info("jwtExpirationInMinutes : " + jwtExpirationInMinutes );
    	
    	// --------------------------------------------------------------------------
    	
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * jwtExpirationInMinutes))  // 1000 ms * 60 s = 1 min
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean validateToken(String token, String username) {
        return extractUsername(token).equals(username) && !isTokenExpired(token);
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }
    
    // ------------------------------------------------------------------
    public String extractJwtFromRequest(HttpServletRequest request) {
    	
		// Get Authorization from the cookies
		Cookie[] cookies = request.getCookies();
		
		String jwtToken = null;
    	
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            jwtToken = bearerToken.substring(7);  // Rimuove il "Bearer " dalla stringa
        
        } 
        else if (cookies != null) { // Check if there are the Authorization in the cookies
            
			for (Cookie cookie : cookies) {
				
                if ("Authorization".equals(cookie.getName())) {
                    jwtToken = cookie.getValue();
                    try {
                    	validateToken(jwtToken,  extractUsername(jwtToken));
                    } catch (Exception e) {	// if token not valid return null
						jwtToken = null;
					}
                    
                }
            }
        }
        return jwtToken;
    }

    private boolean isTokenExpired(String token) {
        return extractClaim(token, Claims::getExpiration).before(new Date());
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claimsResolver.apply(claims);
    }
	
}
