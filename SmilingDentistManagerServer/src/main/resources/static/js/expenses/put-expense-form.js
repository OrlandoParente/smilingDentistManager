( function () {

    document.addEventListener('DOMContentLoaded', function () {

      Array.from( 
        document.getElementsByClassName("expenseIds") 
      ).forEach( element => {

        // var expenseId = element.getAttribute("expenseId");
        var expenseId = element.innerText;
        console.log( "expenseId: " + expenseId );


        // Link select and input text expense tag --------------------------------------------------------------
        const selectExpenseTag = document.getElementById("selectExpenseTag" + expenseId );
        const inputTextExpenseTag = document.getElementById("inputTextExpenseTag" + expenseId );

        // When you change on select
        selectExpenseTag.addEventListener('change', function(){
          inputTextExpenseTag.value = selectExpenseTag.value;
        });

        // When you change on input type 
        inputTextExpenseTag.addEventListener('input', function() { 
          
          console.log('you are typing in input text type ... ');

          var matchingOption = Array.from(selectExpenseTag.options)
                                    .find(option => option.value === inputTextExpenseTag.value); 

          if (matchingOption) { 
            selectExpenseTag.value = inputTextExpenseTag.value; 
          } else { 
            selectExpenseTag.value = "";
          }
        });

        // -----------------------------------------------------------------------------------------------------
        



        // Recovery form items
        // const htmlModalPutExpense = document.getElementById( "putExpenseModalToggle" + expenseId );
        // const bsModalPutExpense = new bootstrap.Modal(htmlModalPutExpense);
        const formPutExpense = document.getElementById( "putExpenseForm" + expenseId );
        console.log( "formPutExpense: " + formPutExpense + " ID: " + "putExpenseForm" + expenseId );
        const btnPutExpense = document.getElementById( "btnPutExpense" + expenseId );
        console.log( "btnPutExpense: " + btnPutExpense );
        const errMsg = document.getElementById("errMsg" + expenseId );
        const errMsgText = document.getElementById("errMsgText" + expenseId );

        
        const btnXCloseModal = document.getElementById( "btnXCloseModal" + expenseId );

      
        // block preventDefault and do post request for manage response messages
        btnPutExpense.addEventListener('click', function( event ){
         
          // prevent default behavior 
          event.preventDefault();

          // Reset messages
          resetMessages();

          // Recovery data from the Add Expense Form
          const formDataPutExpense = new FormData(formPutExpense);

          // NOTE: For use @ResponseBody on the server I have to sent data in json format
          // const formDataObj = Object.fromEntries(formDataPutExpense.entries());
          // const jsonFormData = JSON.stringify(formDataObj);

          // check if mandatory fields are not empty
          if( formDataPutExpense.get("date").trim() === ""  || formDataPutExpense.get("amount").trim() === "" ){

            errMsg.style.display = "block";
            errMsgText.innerText = "Mandatory field is missing";

            return;
          }


          // control print
          console.log("POST url: " + formPutExpense.action);
          console.log("id=" + formDataPutExpense.get("id")
                        + "; date=" + formDataPutExpense.get("date") 
                        + "; amount=" + formDataPutExpense.get("amount")
                        + "; description=" + formDataPutExpense.get("description")
                        + "; tag=" + formDataPutExpense.get("tag"));
                        


          // Do post AJAX request
          fetch( formPutExpense.action, { 
                   method:'PUT', 
                   body:formDataPutExpense
                   // NOTE: For use @ResponseBody on the server I have to sent data in json format
                   // headers: {
                   //    'Content-Type': 'application/json' // Imposta l'intestazione del contenuto come JSON
                   // },
                   // body: jsonFormData
                   } )
            .then( response => {
              if( response.status == 200 ) {
                // bsModalPutExpense.hide();
                window.location.reload();
              } else {
                errMsg.style.display = "block";
                errMsgText.innerText = "Failed to put expense";
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

    });
      
})()