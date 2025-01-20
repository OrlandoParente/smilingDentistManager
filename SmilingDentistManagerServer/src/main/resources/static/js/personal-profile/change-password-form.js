( function () {

    document.addEventListener('DOMContentLoaded', function () {

      // Recovery form items
      const formChangePassword = document.getElementById( "changePasswordForm"  );
      console.log( "formChangePassword: " + formChangePassword + " ID: " + "changePasswordForm"  );
      const btnChangePassword = document.getElementById( "btnChangePassword"  );
      console.log( "btnChangePassword: " + btnChangePassword );
      const errMsg = document.getElementById("errMsg" + "ChangePassword"  );
      const errMsgText = document.getElementById("errMsgText" + "ChangePassword"  );

      // Error Messages ( from common-top-page)
      const errMsgDifferentConfirmPassword = document.getElementById("errMsgDifferentConfirmPassword").innerText;
      const errMsgWrongPassword = document.getElementById("errMsgWrongPassword").innerText;
      const errMsgMandatoryFieldIsEmpty = document.getElementById("errMsgMandatoryFieldIsEmpty").innerText;

      // block preventDefault and do post request for manage response messages
      btnChangePassword.addEventListener('click', function( event ){

        // prevent default behavior 
        event.preventDefault();

        // Reset messages
        resetMessages();

        // Recovery data from the Assume Employee Form
        const formDataChangePassword = new FormData(formChangePassword);

        // NOTE: For use @ResponseBody on the server I have to sent data in json format
        // const formDataObj = Object.fromEntries(formDataChangePassword.entries());
        // const jsonFormData = JSON.stringify(formDataObj);

        
        // check mandatory fields
        if( formDataChangePassword.get('currentPassword').trim() === ""
            || formDataChangePassword.get('newPassword').trim() === "" ) {

            console.log('enter in check mandatory field if');

            errMsg.style.display = "block";
            errMsgText.innerText = errMsgMandatoryFieldIsEmpty;
              
            return;
        }

        // check if current and new passwords are the same
        var textNewPsw = document.getElementById("newPassword").value;
        var textConfirmNewPsw = document.getElementById("confirmNewPassword").value;

        if( textNewPsw !==  textConfirmNewPsw ){

           errMsg.style.display = "block";
           errMsgText.innerText = errMsgDifferentConfirmPassword;

           return;
        }

        // control print
        console.log( "PATCH url: " + formChangePassword.action);

        // Do post AJAX request
        fetch( formChangePassword.action , { 
                  method: 'PATCH', 
                  body: formDataChangePassword
                  // NOTE: For use @ResponseBody on the server I have to sent data in json format
                  // headers: {
                  //    'Content-Type': 'application/json' // Imposta l'intestazione del contenuto come JSON
                  // },
                  // body: jsonFormData
                  
        } ).then( response => {
            
          if( response.status == 200 ) {

              window.location.reload();
              console.log( 'Password changed with success');
              alert('Password changed');
              // return response.json();

          } else if( response.status == 400 ) {
            errMsg.style.display = "block";    
            errMsgText.innerText = errMsgWrongPassword;
            
            // return response.json().then(err => { throw new Error(err.message || 'Failed to put Employee') });

          } else {
            errMsg.style.display = "block";    
            errMsgText.innerText = "Change password faild: Internal error server";
         
          }

        }).catch( error => {

          // errMsg.style.display = "block";
          // errMsgText.innerText = "Failed to put Employee ";
          // errMsgText.innerHTML = "Failed to put Employee ";
          // errMsgText.innerHTML += "<br>Server error: " + error;
          // errMsgText.innerHTML += "<br>Server error message: " + error.message;
          console.log('Error: ' + error );
          // console.log('Error: ' + error.message ); 
        });

      });

      // Reset messages
      function resetMessages(){
        errMsg.style.display = "none";
        errMsgText.innerText = "";
      }
    

    });
        
      
})()