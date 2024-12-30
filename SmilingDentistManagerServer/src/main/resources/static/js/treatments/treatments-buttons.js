//  IIFEs Immediately Invoked Function Expressions
( function() {

    document.addEventListener( 'DOMContentLoaded' , function (){

        // Add 
        Array.from( 
            document.getElementsByClassName("treatmentIds") 
        ).forEach( element => {

            var treatmentId = element.innerText;

            console.log("treatmentId: " + treatmentId);


            btnDelete = document.getElementById('btnDeleteTreatment' + treatmentId);
          
            btnDelete.addEventListener('click', function(){
                // we need the anonymous function otherwise it call immediatly the function  
                deleteAppointmentById(treatmentId);
            });

        });

        // Button for delete the treatment
        function deleteAppointmentById(id){

            // from common-top-page fragment
            var url = document.getElementById('urlDeleteTreatment').getAttribute('href') + '?id=' + id;
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
                    alert("Failed to Delete the Treatment");
                }
            });
        }

    });

 } )();