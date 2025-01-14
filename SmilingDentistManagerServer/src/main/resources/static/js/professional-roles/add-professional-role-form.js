( function () {

    document.addEventListener('DOMContentLoaded', function () {
        // Recovery form items
        // const htmlModalAddProfessionalRole = document.getElementById("addProfessionalRoleModalToggle");
        // const bsModalAddProfessionalRole = new bootstrap.Modal(htmlModalAddProfessionalRole);
        const formAddProfessionalRole = document.getElementById("addProfessionalRoleForm");
        const btnAddProfessionalRole = document.getElementById("btnAddProfessionalRole");
        const errMsg = document.getElementById("errMsg");
        const errMsgText = document.getElementById("errMsgText");

        const btnXCloseModal = document.getElementById("btnXCloseModal");


      
        // block preventDefault and do post request for manage response messages
        btnAddProfessionalRole.addEventListener('click', function( event ){
         
          // prevent default behavior 
          event.preventDefault();

          // Reset messages
          resetMessages();

          // Recovery data from the Add ProfessionalRole Form
          const formDataAddProfessionalRole = new FormData(formAddProfessionalRole);

          // NOTE: For use @ResponseBody on the server I have to sent data in json formatmployee/calendar/day
          // const formDataObj = Object.fromEntries(formDataAddProfessionalRole.entries());
          // const jsonFormData = JSON.stringify(formDataObj);


          // control print
          console.log("POST url: " + formAddProfessionalRole.action);

          // Do post AJAX request
          fetch( formAddProfessionalRole.action, { 
                   method:'POST', 
                   body:formDataAddProfessionalRole
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
                errMsgText.innerText = "Failed to post the professional role";
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