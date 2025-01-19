( function () {

    document.addEventListener('DOMContentLoaded', function () {

      Array.from( 
        document.getElementsByClassName("dismissEmployeeIds") 
      ).forEach( element => {

    
        var employeeId = element.innerText;
        console.log( "dismissEmployeeIds: " + employeeId );

        // Recovery form items
        // const htmlModalAddEmployee = document.getElementById( "putEmployeeModalToggle" + employeeId );
        // const bsModalAddEmployee = new bootstrap.Modal(htmlModalDismissEmployee);
        const formDismissEmployee = document.getElementById( "dismissEmployeeForm" + employeeId );
        console.log( "formDismissEmployee: " + formDismissEmployee + " ID: " + "dismissEmployeeForm" + employeeId );
        const btnDismissEmployee = document.getElementById( "btnDismissEmployee" + employeeId );
        console.log( "btnDismissEmployee: " + btnDismissEmployee );
        const errMsg = document.getElementById("errMsg" + employeeId );
        const errMsgText = document.getElementById("errMsgText" + employeeId );

        

        const btnXCloseModal = document.getElementById( "btnXCloseModal" + employeeId );


      
        // block preventDefault and do post request for manage response messages
        btnDismissEmployee.addEventListener('click', function( event ){

          // prevent default behavior 
          event.preventDefault();

          // Reset messages
          resetMessages();

          // Recovery data from the Dismiss Employee Form
          const formDataDismissEmployee = new FormData(formDismissEmployee);

          // NOTE: For use @ResponseBody on the server I have to sent data in json format
          // const formDataObj = Object.fromEntries(formDataDismissEmployee.entries());
          // const jsonFormData = JSON.stringify(formDataObj);

          // check if mandatory fields are not empty
          // if( formDataDismissEmployee.get('name') === "" || formDataDismissEmployee.get('name') === " " ){

          //   errMsg.style.display = "block";
          //   errMsgText.innerText = "Field name is mandatory field to insert the employee";

          //   return;
          // }

          // control print
          console.log("PUT url: " + formDismissEmployee.action);

          // Do post AJAX request
          fetch( formDismissEmployee.action, { 
                   method:'PUT', 
                   body:formDataDismissEmployee
                   // NOTE: For use @ResponseBody on the server I have to sent data in json format
                   // headers: {
                   //    'Content-Type': 'application/json' // Imposta l'intestazione del contenuto come JSON
                   // },
                   // body: jsonFormData
                   } )
            .then( response => {
              if( response.status == 200 ) {
                window.location.reload();
                console.log( 'Dismiss Employee success');
                // return response.json();

              } else {
                errMsg.style.display = "block";
                errMsgText.innerText = "Failed to put Employee ";
                
                // return response.json().then(err => { throw new Error(err.message || 'Failed to put Employee') });
                // return response.text();
              }
            }).catch( error => {
            
              // errMsg.style.display = "block";
              // errMsgText.innerText = "Failed to put Employee ";
              // errMsgText.innerHTML += "<br>Server error: " + error;
              // errMsgText.innerHTML += "<br>Server error message: " + error.message;
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