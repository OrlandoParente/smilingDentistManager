( function () {

  document.addEventListener('DOMContentLoaded', function () {

    Array.from( 
      document.getElementsByClassName("postExpenseTypes") 
    ).forEach( element => {


      var postExpenseType = element.innerText;
      console.log( "postExpenseType: " + postExpenseType );

    
      // Link select and input text expense tag --------------------------------------------------------------
      const selectExpenseTag = document.getElementById("selectExpenseTag" + postExpenseType );
      const inputTextExpenseTag = document.getElementById("inputTextExpenseTag" + postExpenseType );

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
      
      // Set tag ----------------------------------------------------------------------------------------------
      var tagText = document.getElementById( "tagText" + postExpenseType );
      if( tagText !== null ){
        inputTextExpenseTag.value = tag.innerText;
      }
      // ------------------------------------------------------------------------------------------------------
  



      // Recovery form items
      // const htmlModalPostExpense = document.getElementById( "putExpenseModalToggle" + postExpenseType );
      // const bsModalPostExpense = new bootstrap.Modal(htmlModalPostExpense);
      const formPostExpense = document.getElementById( "addExpenseForm" + postExpenseType );
      console.log( "formPostExpense: " + formPostExpense + " ID: " + "addExpenseForm" + postExpenseType );
      const btnPostExpense = document.getElementById( "btnAddExpense" + postExpenseType );
      console.log( "btnAddExpense: " + btnPostExpense );
      const errMsg = document.getElementById("errMsg" + postExpenseType );
      const errMsgText = document.getElementById("errMsgText" + postExpenseType );

      
      const btnXCloseModal = document.getElementById( "btnXCloseModal" + postExpenseType );

    
      // block preventDefault and do post request for manage response messages
      btnPostExpense.addEventListener('click', function( event ){
       
        // prevent default behavior 
        event.preventDefault();

        // Reset messages
        resetMessages();

        // Recovery data from the Add Expense Form
        const formDataPostExpense = new FormData(formPostExpense);

        // NOTE: For use @ResponseBody on the server I have to sent data in json format
        // const formDataObj = Object.fromEntries(formDataPostExpense.entries());
        // const jsonFormData = JSON.stringify(formDataObj);

        // check if mandatory fields are not empty
        if( formDataPostExpense.get("date").trim() === ""  || formDataPostExpense.get("amount").trim() === "" ){

          errMsg.style.display = "block";
          errMsgText.innerText = "Mandatory field is missing";

          return;
        }


        // control print
        console.log("POST url: " + formPostExpense.action);
        console.log("id=" + formDataPostExpense.get("id")
                      + "; date=" + formDataPostExpense.get("date") 
                      + "; amount=" + formDataPostExpense.get("amount")
                      + "; description=" + formDataPostExpense.get("description")
                      + "; tag=" + formDataPostExpense.get("tag"));
                      


        // Do post AJAX request
        fetch( formPostExpense.action, { 
                 method:'POST', 
                 body:formDataPostExpense
                 // NOTE: For use @ResponseBody on the server I have to sent data in json format
                 // headers: {
                 //    'Content-Type': 'application/json' // Imposta l'intestazione del contenuto come JSON
                 // },
                 // body: jsonFormData
                 } )
          .then( response => {
            if( response.status == 200 ) {
              // bsModalPostExpense.hide();
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