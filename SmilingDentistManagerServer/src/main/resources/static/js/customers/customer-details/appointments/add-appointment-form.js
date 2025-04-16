( function () {

    document.addEventListener('DOMContentLoaded', function () {
        // Recovery form items
        // const htmlModalAddAppointment = document.getElementById("addAppointmentModalToggle");
        // const bsModalAddAppointment = new bootstrap.Modal(htmlModalAddAppointment);
        const formAddAppointment = document.getElementById("addAppointmentForm");
        const btnAddAppointment = document.getElementById("btnAddAppointment");
        const errMsg = document.getElementById("errMsg");
        const errMsgText = document.getElementById("errMsgText");

        const btnXCloseModal = document.getElementById("btnXCloseModal");

        console.log("formAddAppointment -> " + formAddAppointment);

        // block preventDefault and do post request for manage response messages
        btnAddAppointment.addEventListener('click', function( event ){

          // prevent default behavior
          event.preventDefault();

          // Reset messages
          resetMessages();

          // Recovery data from the Add Appointment Form
          const formDataAddAppointment = new FormData(formAddAppointment);

          // NOTE: For use @ResponseBody on the server I have to sent data in json format
          // const formDataObj = Object.fromEntries(formDataAddAppointment.entries());
          // const jsonFormData = JSON.stringify(formDataObj);


          // control print
          console.log("POST url: " + formAddAppointment.action);
          console.log("date=" + formDataAddAppointment.get("date")
                      + "; time=" + formDataAddAppointment.get("time")
                      + "; idCustomer=" + formDataAddAppointment.get("idCustomer"));

          // Do post AJAX request
          fetch( formAddAppointment.action, {
                   method:'POST',
                   body:formDataAddAppointment
                   // NOTE: For use @ResponseBody on the server I have to sent data in json format
                   // headers: {
                   //    'Content-Type': 'application/json' // Imposta l'intestazione del contenuto come JSON
                   // },
                   // body: jsonFormData
                   } )
            .then( response => {
              if( response.status == 200 ) {

                // Add hash to the URL
                // A js script in requires will intercept it for scroll the page on the right position
                window.location.hash = '#anchor-point-appointments';

                // reload
                window.location.reload();

              } else {
                errMsg.style.display = "block";
                errMsgText.innerText = "Failed to post appointment";
              }
            });
            // .then( response => response.json() )
            // .then( data => {

            // })

        });

        // Reset messages on modal close
        btnXCloseModal.addEventListener("click", resetMessages)

        // Reset messages
        function resetMessages(){
          errMsg.style.display = "none";
          errMsgText.innerText = "";
        }
        

    });


})()