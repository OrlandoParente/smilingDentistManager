( function () {

    document.addEventListener('DOMContentLoaded', function () {

      Array.from( 
        document.getElementsByClassName("appointmentIds") 
      ).forEach( element => {

        // var appointmentId = element.getAttribute("appointmentId");
        var appointmentId = element.innerText;
        console.log( "appointmentId: " + appointmentId );

        // Recovery form items
        // const htmlModalAddAppointment = document.getElementById( "putAppointmentModalToggle" + appointmentId );
        // const bsModalAddAppointment = new bootstrap.Modal(htmlModalPutAppointment);
        const formPutAppointment = document.getElementById( "putAppointmentForm" + appointmentId );
        console.log( "formPutAppointment: " + formPutAppointment + " ID: " + "putAppointmentForm" + appointmentId );
        const btnPutAppointment = document.getElementById( "btnPutAppointment" + appointmentId );
        console.log( "btnPutAppointment: " + btnPutAppointment );
        const errMsg = document.getElementById("errMsg" + appointmentId );
        const errMsgText = document.getElementById("errMsgText" + appointmentId );

        

        const btnXCloseModal = document.getElementById( "btnXCloseModal" + appointmentId );


      
        // block preventDefault and do post request for manage response messages
        btnPutAppointment.addEventListener('click', function( event ){

          // prevent default behavior 
          event.preventDefault();

          // Reset messages
          resetMessages();

          // Recovery data from the Put Appointment Form
          const formDataPutAppointment = new FormData(formPutAppointment);

          // NOTE: For use @ResponseBody on the server I have to sent data in json format
          // const formDataObj = Object.fromEntries(formDataPutAppointment.entries());
          // const jsonFormData = JSON.stringify(formDataObj);

          // check if mandatory fields are not empty
          if( formDataPutAppointment.get('date') === "" || formDataPutAppointment.get('date') === " " ||  
              formDataPutAppointment.get('time') === "" || formDataPutAppointment.get('time') === " " ||
              formDataPutAppointment.get('idCustomer') === -1 ){

            errMsg.style.display = "block";
            errMsgText.innerText = "Failed to put appointment";

            return;
          }

          // control print
          console.log("PUT url: " + formPutAppointment.action);

          // Do post AJAX request
          fetch( formPutAppointment.action, { 
                   method:'PUT', 
                   body:formDataPutAppointment
                   // NOTE: For use @ResponseBody on the server I have to sent data in json format
                   // headers: {
                   //    'Content-Type': 'application/json' // Imposta l'intestazione del contenuto come JSON
                   // },
                   // body: jsonFormData
                   } )
            .then( response => {
              if( response.status == 200 ) {
                // bsModalAddAppointment.hide();
                window.location.reload();
              } else {
                errMsg.style.display = "block";
                errMsgText.innerText = "Failed to put appointment";
              }
            });
            // .then( response => response.json() )
            // .then( data => {

            // })

        });

        // Reset messages on modal close
        btnXCloseModal.addEventListener("click", resetMessages);

        // Reset messages
        function resetMessages(){
         errMsg.style.display = "none";
         errMsgText.innerText = "";
        }
      
      });

    });
        
      
})()