( function () {

    document.addEventListener('DOMContentLoaded', function () {

      Array.from( 
        document.getElementsByClassName("medicalHistoryIds") 
      ).forEach( element => {

    
        var medicalHistoryId = element.innerText;
        console.log( "medicalHistoryIds: " + medicalHistoryId );

        // Link select and input text medical history type -----------------------------------------------------
        const medicalHistorySelectType = document.getElementById("medicalHistorySelectType" + medicalHistoryId );
        const medicalHistoryInputTextType = document.getElementById("medicalHistoryInputTextType" + medicalHistoryId );

        console.log( "medicalHistoryInputTextType --> " + medicalHistoryInputTextType );
        // we need the same value at the beginning 
        medicalHistoryInputTextType.value = medicalHistorySelectType.value;

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
        const medicalHistorySelectCategory = document.getElementById("medicalHistorySelectCategory" + medicalHistoryId );
        const medicalHistoryInputTextCategory = document.getElementById("medicalHistoryInputTextCategory" + medicalHistoryId );

        // we need the same value at the beginning 
        medicalHistoryInputTextCategory.value = medicalHistorySelectCategory.value;

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
        // const htmlModalAddMedicalHistory = document.getElementById( "putMedicalHistoryModalToggle" + medicalHistoryId );
        // const bsModalAddMedicalHistory = new bootstrap.Modal(htmlModalPutMedicalHistory);
        const formPutMedicalHistory = document.getElementById( "putMedicalHistoryForm" + medicalHistoryId );
        console.log( "formPutMedicalHistory: " + formPutMedicalHistory + " ID: " + "putMedicalHistoryForm" + medicalHistoryId );
        const btnPutMedicalHistory = document.getElementById( "btnPutMedicalHistory" + medicalHistoryId );
        console.log( "btnPutMedicalHistory: " + btnPutMedicalHistory );
        const errMsg = document.getElementById("errMsg" + medicalHistoryId );
        const errMsgText = document.getElementById("errMsgText" + medicalHistoryId );

        

        const btnXCloseModal = document.getElementById( "btnXCloseModal" + medicalHistoryId );


      
        // block preventDefault and do post request for manage response messages
        btnPutMedicalHistory.addEventListener('click', function( event ){

          // prevent default behavior 
          event.preventDefault();

          // Reset messages
          resetMessages();

          // Recovery data from the Put MedicalHistory Form
          const formDataPutMedicalHistory = new FormData(formPutMedicalHistory);

          // NOTE: For use @ResponseBody on the server I have to sent data in json format
          // const formDataObj = Object.fromEntries(formDataPutMedicalHistory.entries());
          // const jsonFormData = JSON.stringify(formDataObj);

          // check if mandatory fields are not empty
          if( formDataPutMedicalHistory.get('type').trim() === "" 
              || formDataPutMedicalHistory.get('category').trim() === "" 
              || formDataPutMedicalHistory.get('name').trim() === "" 
            ){

              errMsg.style.display = "block";
              errMsgText.innerText = "Mandatory field is empty";

              return;
            }

          // control print
          console.log("PUT url: " + formPutMedicalHistory.action);

          // Do post AJAX request
          fetch( formPutMedicalHistory.action, { 
                   method:'PUT', 
                   body:formDataPutMedicalHistory
                   // NOTE: For use @ResponseBody on the server I have to sent data in json format
                   // headers: {
                   //    'Content-Type': 'application/json' // Imposta l'intestazione del contenuto come JSON
                   // },
                   // body: jsonFormData
                   } )
            .then( response => {
              if( response.status == 200 ) {
                window.location.reload();
              } else {
                errMsg.style.display = "block";
                errMsgText.innerText = "Failed to put Professional Role";
              }
            });
            // .then( response => response.json() )
            // .then( data => {

            // })

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