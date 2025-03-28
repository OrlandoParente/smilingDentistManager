( function () {

    document.addEventListener('DOMContentLoaded', function () {

      console.log('LOADING -> js/email-settings/email-template-recall-form.js');

      // Recovery form items
      const formEmailTemplateRecall = document.getElementById( "formEmailTemplateRecall"  );
      console.log( "formEmailTemplateRecall: " + formEmailTemplateRecall + " ID: " + "formEmailTemplateRecall"  );
      const btnSaveEmailTemplateRecall = document.getElementById( "btnSaveEmailTemplateRecall"  );
      console.log( "btnSaveEmailTemplateRecall: " + btnSaveEmailTemplateRecall );
      const errMsg = document.getElementById("errMsg" + "formEmailTemplateRecall" );
      const errMsgText = document.getElementById("errMsgText" + "formEmailTemplateRecall" );


      // block preventDefault and do post request for manage response messages
      btnSaveEmailTemplateRecall.addEventListener('click', function( event ){

        // prevent default behavior 
        event.preventDefault();

        // Reset messages
        resetMessages();

        // Recovery data from the Put Employee Form
        const formDataEmailTemplateRecall = new FormData(formEmailTemplateRecall);

        // NOTE: For use @ResponseBody on the server I have to sent data in json format
        // const formDataObj = Object.fromEntries(formEmailTemplateRecall.entries());
        // const jsonFormData = JSON.stringify(formDataObj);

        // check if mandatory fields are not empty -----------------------------------------------------
        if( formDataEmailTemplateRecall.get('from').trim() === '' 
            || formDataEmailTemplateRecall.get('subject').trim() === ''
            || formDataEmailTemplateRecall.get('text').trim() === ''  ){

           errMsg.style.display = "block";
           errMsgText.innerText = "All fields are mandatory";

           return;
        }

        // ---------------------------------------------------------------------------------------------

        // control print
        console.log("POST url: " + formEmailTemplateRecall.action);

        // Do post AJAX request
        fetch( formEmailTemplateRecall.action, { 
                  method:'POST', 
                  body:formDataEmailTemplateRecall
                  // NOTE: For use @ResponseBody on the server I have to sent data in json format
                  // headers: {
                  //    'Content-Type': 'application/json' // Imposta l'intestazione del contenuto come JSON
                  // },
                  // body: jsonFormData
                  } )
          .then( response => {
            if( response.status == 200 ) {
              // window.location.reload();
              console.log( 'LOCATION: ' + window.location.href );
              window.location.reload();

              console.log( 'Recall email temaplate  saved with success');
              alert("Success"); 
              return response.json();

            } else {
               errMsg.style.display = "block";
               errMsgText.innerText = "Failed to save recall email template ";
            }
        
        })
        
      });

      // Reset messages on modal close
      // btnXCloseModal.addEventListener("click", resetMessages);

      // Reset messages
      function resetMessages(){
        errMsg.style.display = "none";
        errMsgText.innerText = "";
      }

    });
 
})();