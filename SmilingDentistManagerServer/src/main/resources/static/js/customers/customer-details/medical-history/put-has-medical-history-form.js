( function () {

    document.addEventListener('DOMContentLoaded', function () {

      Array.from( 
        document.getElementsByClassName("hasMedicalHistoryIds") 
      ).forEach( element => {

        var hasMedicalHistoryId = element.innerText;

        console.log("JS PUT hasMedicalHistoryId: " + hasMedicalHistoryId);

        const formPutHasMedicalHistory = document.getElementById("putHasMedicalHistoryForm" + hasMedicalHistoryId );
        const btnPutHasMedicalHistory = document.getElementById("btnPutHasMedicalHistory" + hasMedicalHistoryId );
        const errMsg = document.getElementById("errMsg");
        const errMsgText = document.getElementById("errMsgText");

        const btnXCloseModal = document.getElementById("btnXCloseModal" + hasMedicalHistoryId );

        // block preventDefault and do post request for manage response messages
        btnPutHasMedicalHistory.addEventListener('click', function( event ){
          
          // prevent default behavior 
          event.preventDefault();

          // Reset messages
          resetMessages();

          // Recovery data from the Put HasMedicalHistory Form
          const formDataPutHasMedicalHistory = new FormData(formPutHasMedicalHistory);

          // NOTE: For use @ResponseBody on the server I have to sent data in json formatmployee/calendar/day
          // const formDataObj = Object.fromEntries(formDataPutHasMedicalHistory.entries());
          // const jsonFormData = JSON.stringify(formDataObj);


          // control print
          console.log("PUT url: " + formPutHasMedicalHistory.action);

          // Do post AJAX request
          fetch( formPutHasMedicalHistory.action, { 
                    method:'PUT', 
                    body:formDataPutHasMedicalHistory
                    // NOTE: For use @ResponseBody on the server I have to sent data in json format
                    // headers: {
                    //    'Content-Type': 'application/json' // Imposta l'intestazione del contenuto come JSON
                    // },
                    // body: jsonFormData
                    } )
            .then( response => {
              if( response.status == 200 ) {

                // console.log( response.json() )

                // uncomment for active the next ".then"
                // return response.json();

                // console.log( "LOCATION: " + window.location.href );
                window.location.reload();
                
                
              } else {
                errMsg.style.display = "block";
                errMsgText.innerText = "Failed to post the customer";
              }
            }).catch( error => {
              console.log('Error: ' + error);
            });
            // .then( response => response.json() )
            // .then( data => {

            // })

        });

        
        // Reset messages and reload on close modal
        btnXCloseModal.addEventListener('click', function(event){

          // Reset messages on modal close
          resetMessages();

          // reload on close 
          window.location.reload();
        });

        // Reset messages
        function resetMessages(){
          errMsg.style.display = "none";
          errMsgText.innerText = "";
        }

      }); // end forEach

    }); 

      
})()