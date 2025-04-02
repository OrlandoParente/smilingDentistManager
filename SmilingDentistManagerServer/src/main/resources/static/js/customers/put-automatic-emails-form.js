( function () {

    document.addEventListener('DOMContentLoaded', function () {

      Array.from( 
        document.getElementsByClassName("customerIds") 
      ).forEach( element => {

    
        var customerId = element.innerText;
        console.log( "customerIds: " + customerId );

        // Recovery form items
        const formPutAutomaticEmails = document.getElementById( "putAutomaticEmailsForm" + customerId );
        console.log( "formPutAutomaticEmails: " + formPutAutomaticEmails + " ID: " + "putAutomaticEmailsForm" + customerId );
        const btnPutAutomaticEmails = document.getElementById( "btnPutAutomaticEmails" + customerId );
        console.log( "btnPutAutomaticEmails: " + btnPutAutomaticEmails );
        const errMsg = document.getElementById("errMsg" + customerId );
        const errMsgText = document.getElementById("errMsgText" + customerId );

        

        const btnXCloseModal = document.getElementById( "btnXCloseModal" + customerId );


        
        // block preventDefault and do post request for manage response messages
        btnPutAutomaticEmails.addEventListener('click', function( event ){

          // prevent default behavior 
          event.preventDefault();

          // Reset messages
          resetMessages();

          // Recovery data from the Put Customer Form
          const formDataPutAutomaticEmails = new FormData(formPutAutomaticEmails);

          // NOTE: For use @ResponseBody on the server I have to sent data in json format
          // const formDataObj = Object.fromEntries(formDataPutAutomaticEmails.entries());
          // const jsonFormData = JSON.stringify(formDataObj);

          // check if mandatory fields are not empty
          // if( formDataPutAutomaticEmails.get('name') === "" || formDataPutAutomaticEmails.get('name') === " " ){

          //   errMsg.style.display = "block";
          //   errMsgText.innerText = "Field name is mandatory field to insert the customer";

          //   return;
          // }

          // control print
          console.log("PUT url: " + formPutAutomaticEmails.action);

          // Do post AJAX request
          fetch( formPutAutomaticEmails.action, { 
                   method:'PUT', 
                   body:formDataPutAutomaticEmails
                   // NOTE: For use @ResponseBody on the server I have to sent data in json format
                   // headers: {
                   //    'Content-Type': 'application/json' // Imposta l'intestazione del contenuto come JSON
                   // },
                   // body: jsonFormData
                   } )
            .then( response => {
              if( response.status == 200 ) {
                
                window.location.reload();

                console.log( 'Update automatic emails in Customer success');
                
                // this need if you want chain another ".then"
                // return response.json();

              } else {
                errMsg.style.display = "block";
                errMsgText.innerText = "Failed to update automatic emails in Customer ";
              }
            })

            .catch( error => {
             console.log('Error: ' + error ); 
            });

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