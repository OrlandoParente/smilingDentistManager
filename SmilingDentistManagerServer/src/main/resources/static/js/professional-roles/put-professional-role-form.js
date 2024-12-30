( function () {

    document.addEventListener('DOMContentLoaded', function () {

      Array.from( 
        document.getElementsByClassName("professionalRoleIds") 
      ).forEach( element => {

    
        var professionalRoleId = element.innerText;
        console.log( "professionalRoleIds: " + professionalRoleId );

        // Recovery form items
        // const htmlModalAddProfessionalRole = document.getElementById( "putProfessionalRoleModalToggle" + professionalRoleId );
        // const bsModalAddProfessionalRole = new bootstrap.Modal(htmlModalPutProfessionalRole);
        const formPutProfessionalRole = document.getElementById( "putProfessionalRoleForm" + professionalRoleId );
        console.log( "formPutProfessionalRole: " + formPutProfessionalRole + " ID: " + "putProfessionalRoleForm" + professionalRoleId );
        const btnPutProfessionalRole = document.getElementById( "btnPutProfessionalRole" + professionalRoleId );
        console.log( "btnPutProfessionalRole: " + btnPutProfessionalRole );
        const errMsg = document.getElementById("errMsg" + professionalRoleId );
        const errMsgText = document.getElementById("errMsgText" + professionalRoleId );

        

        const btnXCloseModal = document.getElementById( "btnXCloseModal" + professionalRoleId );


      
        // block preventDefault and do post request for manage response messages
        btnPutProfessionalRole.addEventListener('click', function( event ){

          // prevent default behavior 
          event.preventDefault();

          // Reset messages
          resetMessages();

          // Recovery data from the Put ProfessionalRole Form
          const formDataPutProfessionalRole = new FormData(formPutProfessionalRole);

          // NOTE: For use @ResponseBody on the server I have to sent data in json format
          // const formDataObj = Object.fromEntries(formDataPutProfessionalRole.entries());
          // const jsonFormData = JSON.stringify(formDataObj);

          // check if mandatory fields are not empty
          if( formDataPutProfessionalRole.get('name') === "" || formDataPutProfessionalRole.get('name') === " " ){

            errMsg.style.display = "block";
            errMsgText.innerText = "Failed to put Professional Role";

            return;
          }

          // control print
          console.log("PUT url: " + formPutProfessionalRole.action);

          // Do post AJAX request
          fetch( formPutProfessionalRole.action, { 
                   method:'PUT', 
                   body:formDataPutProfessionalRole
                   // NOTE: For use @ResponseBody on the server I have to sent data in json format
                   // headers: {
                   //    'Content-Type': 'application/json' // Imposta l'intestazione del contenuto come JSON
                   // },
                   // body: jsonFormData
                   } )
            .then( response => {
              if( response.status == 200 ) {
                window.location.reload();
              } else {
                errMsg.style.display = "block";
                errMsgText.innerText = "Failed to put Professional Role";
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