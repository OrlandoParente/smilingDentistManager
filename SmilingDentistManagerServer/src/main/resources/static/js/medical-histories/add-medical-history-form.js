( function () {

    document.addEventListener('DOMContentLoaded', function () {

        // Link select and input text medical history type -----------------------------------------------------
        const medicalHistorySelectType = document.getElementById("medicalHistorySelectType");
        const medicalHistoryInputTextType = document.getElementById("medicalHistoryInputTextType");

        // When you change on select
        medicalHistorySelectType.addEventListener('change', function(){
          medicalHistoryInputTextType.value = medicalHistorySelectType.value;
        });

        // When you change on input type 
        medicalHistoryInputTextType.addEventListener('input', function() { 
          
          console.log('you are typing in input text type ... ');

          var matchingOption = Array.from(medicalHistorySelectType.options)
                                    .find(option => option.value === medicalHistoryInputTextType.value); 

          if (matchingOption) { 
            medicalHistorySelectType.value = medicalHistoryInputTextType.value; 
          } else { 
            medicalHistorySelectType.value = "";
          }
        });

        // -----------------------------------------------------------------------------------------------------
        

        // Link select and input text medical history category --------------------------------------------------
        const medicalHistorySelectCategory = document.getElementById("medicalHistorySelectCategory");
        const medicalHistoryInputTextCategory = document.getElementById("medicalHistoryInputTextCategory");

        // When you change on select
        medicalHistorySelectCategory.addEventListener('change', function(){
          medicalHistoryInputTextCategory.value = medicalHistorySelectCategory.value;
        });

        // When you change on input type 
        medicalHistoryInputTextCategory.addEventListener('input', function() { 
           
          console.log('you are typing in category input type ... ');

          var matchingOption = Array.from(medicalHistorySelectCategory.options)
                                    .find(option => option.value === medicalHistoryInputTextCategory.value); 

          if (matchingOption) { 
            medicalHistorySelectCategory.value = medicalHistoryInputTextCategory.value; 
          } else { 
            medicalHistorySelectCategory.value = "";
          }
        });

        // -----------------------------------------------------------------------------------------------------
        
        // Recovery form items
        // const htmlModalAddMedicalHistory = document.getElementById("addMedicalHistoryModalToggle");
        // const bsModalAddMedicalHistory = new bootstrap.Modal(htmlModalAddMedicalHistory);
        const formAddMedicalHistory = document.getElementById("addMedicalHistoryForm");
        const btnAddMedicalHistory = document.getElementById("btnAddMedicalHistory");
        const errMsg = document.getElementById("errMsg");
        const errMsgText = document.getElementById("errMsgText");

        const btnXCloseModal = document.getElementById("btnXCloseModal");


        // block preventDefault and do post request for manage response messages
        btnAddMedicalHistory.addEventListener('click', function( event ){
         
          // prevent default behavior 
          event.preventDefault();

          // Reset messages
          resetMessages();

          // Recovery data from the Add MedicalHistory Form
          const formDataAddMedicalHistory = new FormData(formAddMedicalHistory);

          // NOTE: For use @ResponseBody on the server I have to sent data in json formatmployee/calendar/day
          // const formDataObj = Object.fromEntries(formDataAddMedicalHistory.entries());
          // const jsonFormData = JSON.stringify(formDataObj);


          // control print
          console.log("POST url: " + formAddMedicalHistory.action);

          // check if mandatory fields are not empty
          if( formDataAddMedicalHistory.get('type').trim() === "" 
              || formDataAddMedicalHistory.get('category').trim() === "" 
              || formDataAddMedicalHistory.get('name').trim() === "" 
               ){

            errMsg.style.display = "block";
            errMsgText.innerText = "Mandatory field is empty";

            return;
          }

          // Do post AJAX request
          fetch( formAddMedicalHistory.action, { 
                   method:'POST', 
                   body:formDataAddMedicalHistory
                   // NOTE: For use @ResponseBody on the server I have to sent data in json format
                   // headers: {
                   //    'Content-Type': 'application/json' // Imposta l'intestazione del contenuto come JSON
                   // },
                   // body: jsonFormData
                   } )
            .then( response => {
              if( response.status == 200 ) {
                console.log( "LOCATION: " + window.location.href );
                window.location.reload();
                
              } else {
                errMsg.style.display = "block";
                errMsgText.innerText = "Failed to post the medical history";
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

      
})()