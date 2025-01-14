( function(){

    Array.from( // convert HTML Collection in array cause HTML Collection doesn't dupport forEach
        // get 
        document.getElementsByClassName('sdm-navbar-dropdown')
    ).forEach(element => {

        element.addEventListener('click', function(event) {
            event.preventDefault();
            
            const dropdownMenu = this.nextElementSibling;
            if (dropdownMenu.classList.contains('d-none') ) {
            
                dropdownMenu.classList.remove('d-none');
                dropdownMenu.classList.add('d-block');
            } else {
                dropdownMenu.classList.remove('d-block');
                dropdownMenu.classList.add('d-none');
            }
        });
    
    });

    // // JavaScript per gestire il click Dashboard offcanvas (da mobile)
    // document.getElementById('navbarDropdownMobile').addEventListener('click', function(event) {
    //     event.preventDefault();
        
    //     const dropdownMenu = this.nextElementSibling;
    //     if (dropdownMenu.classList.contains('d-none') ) {
        
    //         dropdownMenu.classList.remove('d-none');
    //         dropdownMenu.classList.add('d-block');
    //     } else {
    //         dropdownMenu.classList.remove('d-block');
    //         dropdownMenu.classList.add('d-none');
    //     }
    // });




    // // JavaScript per gestire il click Dashboard
    // document.getElementById('navbarDropdown').addEventListener('click', function(event) {
    //     event.preventDefault();
        
    //     const dropdownMenu = this.nextElementSibling;
    //     if (dropdownMenu.classList.contains('d-none') ) {
        
    //         dropdownMenu.classList.remove('d-none');
    //         dropdownMenu.classList.add('d-block');
    //     } else {
    //         dropdownMenu.classList.remove('d-block');
    //         dropdownMenu.classList.add('d-none');
    //     }
    // });


})();

