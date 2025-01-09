//  IIFEs Immediately Invoked Function Expressions
( function() {

    document.addEventListener( 'DOMContentLoaded' , function (){

        // Add 
        Array.from( 
            document.getElementsByClassName("medicalHistoryIds") 
        ).forEach( element => {

            var medicalHistoryId = element.innerText;

            console.log("medicalHistoryId: " + medicalHistoryId);


            btnDelete = document.getElementById('btnDeleteMedicalHistory' + medicalHistoryId);
          
            btnDelete.addEventListener('click', function(){
                // we need the anonymous function otherwise it call immediatly the function  
                deleteAppointmentById(medicalHistoryId);
            });

        });

        // Button for delete the appointment
        function deleteAppointmentById(id){

            // from common-top-page fragment
            var url = document.getElementById('urlDeleteMedicalHistory').getAttribute('href') + '?id=' + id;
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
                    alert("Failed to Delete Medical History");
                }
            });
        }

    });

 } )();