//  IIFEs Immediately Invoked Function Expressions
( function() {

    document.addEventListener( 'DOMContentLoaded' , function (){

        // Add 
        Array.from( 
            document.getElementsByClassName("appointmentIds") 
        ).forEach( element => {

            var appointmentId = element.innerText;

            btnDelete = document.getElementById('btnDeleteAppointment' + appointmentId);
          
            btnDelete.addEventListener('click', function(){
                // we need the anonymous function otherwise it call immediatly the function  
                deleteAppointmentById(appointmentId);
            });

        });

        // Button for delete the appointment
        function deleteAppointmentById(id){

            // from common-top-page fragment
            var url = document.getElementById('urlDeleteAppointment').getAttribute('href') + '?id=' + id;
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
                    alert("Failed to Delete Appointment");
                }
            });
        }

    });

 } )();