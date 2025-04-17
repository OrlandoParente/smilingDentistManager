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

                    // Add hash to the URL
                    // A js script in requires will intercept it for scroll the page on the right position
                    window.location.hash = '#anchor-point-appointments';
                    window.location.reload();

                    // this is not good couse page reload is needed
                    // window.location.href = window.location.href.split('#')[0] + '#anchor-point-appointments';
                } else {
                    alert("Failed to Delete Appointment");
                }
            });
        }

    });

 } )();