// with IIFE the button can't see the function 
// ( function() {

    // Button for delete the appointment
    function deleteAppointmentById(btn){

        var id = btn.getAttribute('request-id');
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


// } )();