( function () {

    document.addEventListener('DOMContentLoaded', function () {


      var customerId = document.getElementById("putCustomerDetailsId").value;
      console.log( "customerId from customer-details: " + customerId );

      // Recovery form items
      const formPutCustomer = document.getElementById( "putCustomerForm" + customerId );
      console.log( "formPutCustomer: " + formPutCustomer + " ID: " + "putCustomerForm" + customerId );
      const btnPutCustomer = document.getElementById( "btnPutCustomer" + customerId );
      console.log( "btnPutCustomer: " + btnPutCustomer );
      const errMsg = document.getElementById("errMsg" + customerId );
      const errMsgText = document.getElementById("errMsgText" + customerId );

      

      const btnXCloseModal = document.getElementById( "btnXCloseModal" + customerId );


      
      // block preventDefault and do post request for manage response messages
      btnPutCustomer.addEventListener('click', function( event ){

        // prevent default behavior 
        event.preventDefault();

        // Reset messages
        resetMessages();

        // Recovery data from the Put Customer Form
        const formDataPutCustomer = new FormData(formPutCustomer);

        // NOTE: For use @ResponseBody on the server I have to sent data in json format
        // const formDataObj = Object.fromEntries(formDataPutCustomer.entries());
        // const jsonFormData = JSON.stringify(formDataObj);

        // check if mandatory fields are not empty
        // if( formDataPutCustomer.get('name') === "" || formDataPutCustomer.get('name') === " " ){

        //   errMsg.style.display = "block";
        //   errMsgText.innerText = "Field name is mandatory field to insert the customer";

        //   return;
        // }

        // control print
        console.log("PUT url: " + formPutCustomer.action);

        // Do post AJAX request
        fetch( formPutCustomer.action, { 
                  method:'PUT', 
                  body:formDataPutCustomer
                  // NOTE: For use @ResponseBody on the server I have to sent data in json format
                  // headers: {
                  //    'Content-Type': 'application/json' // Imposta l'intestazione del contenuto come JSON
                  // },
                  // body: jsonFormData
                  } )
          .then( response => {
            if( response.status == 200 ) {
              
              console.log( 'Put Customer success');

              window.location.reload();

              // this need if you want chain another ".then"
              // return response.json();

            } else {
              errMsg.style.display = "block";
              errMsgText.innerText = "Failed to put Customer ";
            }
          }).catch( error => {
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

     
})()