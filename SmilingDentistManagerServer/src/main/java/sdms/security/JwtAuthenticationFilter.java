package sdms.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private JwtUtil jwtUtil;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		
			String authHeader = request.getHeader("Authorization");
			String username = null;
			String jwtToken = null;
			
			if (authHeader != null && authHeader.startsWith("Bearer ")) {
			    jwtToken = authHeader.substring(7);
			    username = jwtUtil.extractUsername(jwtToken);
			}
			
			if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			    if (jwtUtil.validateToken(jwtToken, username)) {
			        UsernamePasswordAuthenticationToken authToken =
			                new UsernamePasswordAuthenticationToken(username, null, null);
			        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			        SecurityContextHolder.getContext().setAuthentication(authToken);
			    }
			}
			
			filterChain.doFilter(request, response);
		
	}

}
