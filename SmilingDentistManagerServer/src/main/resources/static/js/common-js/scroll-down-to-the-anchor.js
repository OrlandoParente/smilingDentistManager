( function(){

    // When there is an anchor, first scroll-down to the anchor, then remove it 
    document.addEventListener( 'DOMContentLoaded', function() {

        window.addEventListener('load', () => {
            if (window.location.hash) {
              const anchor = document.querySelector(window.location.hash);
              if (anchor) {
                // scroll-down until the id of the anchor
                anchor.scrollIntoView(); 
  
                // remove the anchor without reload the page
                history.replaceState(null, null, window.location.pathname);              
              }
            }
          });

    } );

} )();