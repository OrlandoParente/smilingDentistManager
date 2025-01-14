//  IIFEs Immediately Invoked Function Expressions
( function() {

    document.addEventListener( 'DOMContentLoaded' , function (){

        // Add 
        Array.from( 
            document.getElementsByClassName("hasMedicalHistoryIds") 
        ).forEach( element => {

            var hasMedicalHistoryId = element.innerText;

            console.log("hasMedicalHistoryId: " + hasMedicalHistoryId);


            btnDelete = document.getElementById('btnDeleteHasMedicalHistory' + hasMedicalHistoryId);
          
            btnDelete.addEventListener('click', function(){
                // we need the anonymous function otherwise it call immediatly the function  
                deleteAppointmentById(hasMedicalHistoryId);
            });

        });

        // Button for delete the hasMedicalHistory
        function deleteAppointmentById(id){

            // from common-top-page fragment
            var url = document.getElementById('urlDeleteHasMedicalHistory').getAttribute('href') + '?id=' + id;
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
                    alert("Failed to Delete the HasMedicalHistory");
                }
            });
        }

    });

 } )();