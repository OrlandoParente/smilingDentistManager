( function () {

    document.addEventListener('DOMContentLoaded', function () {

      Array.from( 
        document.getElementsByClassName("assumeEmployeeIds") 
      ).forEach( element => {

    
        var employeeId = element.innerText;
        console.log( "assumeEmployeeIds: " + employeeId );

        // Recovery form items
        // const htmlModalAddEmployee = document.getElementById( "putEmployeeModalToggle" + employeeId );
        // const bsModalAddEmployee = new bootstrap.Modal(htmlModalAssumeEmployee);
        const formAssumeEmployee = document.getElementById( "assumeEmployeeForm" + employeeId );
        console.log( "formAssumeEmployee: " + formAssumeEmployee + " ID: " + "assumeEmployeeForm" + employeeId );
        const btnAssumeEmployee = document.getElementById( "btnAssumeEmployee" + employeeId );
        console.log( "btnAssumeEmployee: " + btnAssumeEmployee );
        const errMsg = document.getElementById("errMsg" + employeeId );
        const errMsgText = document.getElementById("errMsgText" + employeeId );

        

        const btnXCloseModal = document.getElementById( "btnXCloseModalAssume" + employeeId );


        // if this doesn't exist we need to do a PUT request
        // otherwise a POST request
        const spanAssumeIdPeriod = document.getElementById( "assumeIdPeriod" + employeeId );
        var methodReq = ( spanAssumeIdPeriod !== null ) ? 'PUT' : 'POST';
        var url = ( spanAssumeIdPeriod !== null )
                  ? document.getElementById("urlPutWorkPeriod").getAttribute("href")     // from common-top-page
                  : document.getElementById("urlPostWorkPeriod").getAttribute("href");   // from common-top-page
      
        // block preventDefault and do post request for manage response messages
        btnAssumeEmployee.addEventListener('click', function( event ){

          // prevent default behavior 
          event.preventDefault();

          // Reset messages
          resetMessages();

          // Recovery data from the Assume Employee Form
          const formDataAssumeEmployee = new FormData(formAssumeEmployee);

          // NOTE: For use @ResponseBody on the server I have to sent data in json format
          // const formDataObj = Object.fromEntries(formDataAssumeEmployee.entries());
          // const jsonFormData = JSON.stringify(formDataObj);

          // check if mandatory fields are not empty
          // if( formDataAssumeEmployee.get('name') === "" || formDataAssumeEmployee.get('name') === " " ){

          //   errMsg.style.display = "block";
          //   errMsgText.innerText = "Field name is mandatory field to insert the employee";

          //   return;
          // }

          // control print
          console.log( methodReq + " url: " + url);

          // Do post AJAX request
          fetch( url , { 
                   method: methodReq, 
                   body:formDataAssumeEmployee
                   // NOTE: For use @ResponseBody on the server I have to sent data in json format
                   // headers: {
                   //    'Content-Type': 'application/json' // Imposta l'intestazione del contenuto come JSON
                   // },
                   // body: jsonFormData
                   
          } ).then( response => {
              
            if( response.status == 200 ) {

                window.location.reload();
                console.log( 'Assume Employee success');
                // return response.json();

            } else {
              errMsg.style.display = "block";    
              errMsgText.innerText = "Failed to put Employee ";
              
              // return response.json().then(err => { throw new Error(err.message || 'Failed to put Employee') });

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