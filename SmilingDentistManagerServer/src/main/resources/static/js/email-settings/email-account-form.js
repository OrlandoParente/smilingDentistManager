( function () {

    document.addEventListener('DOMContentLoaded', function () {

      console.log('LOADING -> js/email-settings/email-account-form.js');

      // Recovery form items
      const formEmailAccount = document.getElementById( "formEmailAccount"  );
      console.log( "formEmailAccount: " + formEmailAccount + " ID: " + "formEmailAccount"  );
      const btnSaveEmailAccount = document.getElementById( "btnSaveEmailAccount"  );
      console.log( "btnSaveEmailAccount: " + btnSaveEmailAccount );
      const errMsg = document.getElementById("errMsg" + "formEmailAccount" );
      const errMsgText = document.getElementById("errMsgText" + "formEmailAccount" );


      // block preventDefault and do post request for manage response messages
      btnSaveEmailAccount.addEventListener('click', function( event ){

        // prevent default behavior 
        event.preventDefault();

        // Reset messages
        resetMessages();

        // Recovery data from the Put Employee Form
        const formDataEmailAccount = new FormData(formEmailAccount);

        // NOTE: For use @ResponseBody on the server I have to sent data in json format
        // const formDataObj = Object.fromEntries(formEmailAccount.entries());
        // const jsonFormData = JSON.stringify(formDataObj);

        // check if mandatory fields are not empty -----------------------------------------------------
        if( formDataEmailAccount.get('host').trim() === '' || formDataEmailAccount.get('port').trim() === ''
            || formDataEmailAccount.get('username').trim() === '' || formDataEmailAccount.get('password').trim() === ''
            || formDataEmailAccount.get('enableAuth').trim() === '' || formDataEmailAccount.get('enableTLS').trim() === ''
            || formDataEmailAccount.get('enableSSL').trim() === ''  ){

           errMsg.style.display = "block";
           errMsgText.innerText = "All fields are mandatory";

           return;
        }

        // ---------------------------------------------------------------------------------------------

        // control print
        console.log("POST url: " + formEmailAccount.action);

        // Do post AJAX request
        fetch( formEmailAccount.action, { 
                  method:'POST', 
                  body:formDataEmailAccount
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
              console.log( 'Email account  saved with success');
              alert("Success")
              return response.json();

            } else {

              console.log('Error: ' + error ); 
              errMsg.style.display = "block";
              errMsgText.innerText = "Failed to save email account  ";
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