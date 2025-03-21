//  IIFEs Immediately Invoked Function Expressions
( function() {

    document.addEventListener( 'DOMContentLoaded' , function (){

        console.log("loaded -> /js/dental-materials/dental-materials-buttons.js");

        // Add 
        Array.from( 
            document.getElementsByClassName("dentalMaterialIds") 
        ).forEach( element => {

            var dentalMaterialId = element.innerText;

            console.log("dentalMaterialId: " + dentalMaterialId);


            btnDelete = document.getElementById('btnDeleteDentalMaterial' + dentalMaterialId);
          
            btnDelete.addEventListener('click', function(){
                // we need the anonymous function otherwise it call immediatly the function  
                deleteAppointmentById(dentalMaterialId);
            });

        });

        // Button for delete the dental material
        function deleteAppointmentById(id){

            // from common-top-page fragment
            var url = document.getElementById('urlDeleteDentalMaterial').getAttribute('href') + '?id=' + id;
            console.log(url);

            fetch( url,{
                method: 'DELETE',
                headers: {
                    'Content-Type': 'application/json'
                }
                // body:{
                //     'id':id
                // }
            })
            .then( response => {
                if( response.ok ){
                    window.location.reload();
                } else {
                    alert("Failed to Delete the DentalMaterial");
                }
            });
        }

    });

 } )();