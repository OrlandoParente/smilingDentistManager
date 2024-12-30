( function () {

    document.addEventListener('DOMContentLoaded', function () {
        // Recovery form items
        // const htmlModalAddTreatment = document.getElementById("addTreatmentModalToggle");
        // const bsModalAddTreatment = new bootstrap.Modal(htmlModalAddTreatment);
        const formAddTreatment = document.getElementById("addTreatmentForm");
        const btnAddTreatment = document.getElementById("btnAddTreatment");
        const errMsg = document.getElementById("errMsg");
        const errMsgText = document.getElementById("errMsgText");

        const btnXCloseModal = document.getElementById("btnXCloseModal");


      
        // block preventDefault and do post request for manage response messages
        btnAddTreatment.addEventListener('click', function( event ){
         
          // prevent default behavior 
          event.preventDefault();

          // Reset messages
          resetMessages();

          // Recovery data from the Add Treatment Form
          const formDataAddTreatment = new FormData(formAddTreatment);

          // NOTE: For use @ResponseBody on the server I have to sent data in json formatmployee/calendar/day
          // const formDataObj = Object.fromEntries(formDataAddTreatment.entries());
          // const jsonFormData = JSON.stringify(formDataObj);


          // control print
          console.log("POST url: " + formAddTreatment.action);

          // Do post AJAX request
          fetch( formAddTreatment.action, { 
                   method:'POST', 
                   body:formDataAddTreatment
                   // NOTE: For use @ResponseBody on the server I have to sent data in json format
                   // headers: {
                   //    'Content-Type': 'application/json' // Imposta l'intestazione del contenuto come JSON
                   // },
                   // body: jsonFormData
                   } )
            .then( response => {
              if( response.status == 200 ) {
                console.log( "LOCATION: " + window.location.href );
                window.location.reload();
                
              } else {
                errMsg.style.display = "block";
                errMsgText.innerText = "Failed to post the treatment";
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