( function () {

    document.addEventListener('DOMContentLoaded', function () {

      Array.from( 
        document.getElementsByClassName("treatmentIds") 
      ).forEach( element => {

    
        var treatmentId = element.innerText;
        console.log( "treatmentIds: " + treatmentId );

        // Recovery form items
        // const htmlModalAddTreatment = document.getElementById( "putTreatmentModalToggle" + treatmentId );
        // const bsModalAddTreatment = new bootstrap.Modal(htmlModalPutTreatment);
        const formPutTreatment = document.getElementById( "putTreatmentForm" + treatmentId );
        console.log( "formPutTreatment: " + formPutTreatment + " ID: " + "putTreatmentForm" + treatmentId );
        const btnPutTreatment = document.getElementById( "btnPutTreatment" + treatmentId );
        console.log( "btnPutTreatment: " + btnPutTreatment );
        const errMsg = document.getElementById("errMsg" + treatmentId );
        const errMsgText = document.getElementById("errMsgText" + treatmentId );

        

        const btnXCloseModal = document.getElementById( "btnXCloseModal" + treatmentId );


      
        // block preventDefault and do post request for manage response messages
        btnPutTreatment.addEventListener('click', function( event ){

          // prevent default behavior 
          event.preventDefault();

          // Reset messages
          resetMessages();

          // Recovery data from the Put Treatment Form
          const formDataPutTreatment = new FormData(formPutTreatment);

          // NOTE: For use @ResponseBody on the server I have to sent data in json format
          // const formDataObj = Object.fromEntries(formDataPutTreatment.entries());
          // const jsonFormData = JSON.stringify(formDataObj);

          // check if mandatory fields are not empty
          if( formDataPutTreatment.get('name') === "" || formDataPutTreatment.get('name') === " " ){

            errMsg.style.display = "block";
            errMsgText.innerText = "Field name is mandatory field to insert the treatment";

            return;
          }

          // control print
          console.log("PUT url: " + formPutTreatment.action);

          // Do post AJAX request
          fetch( formPutTreatment.action, { 
                   method:'PUT', 
                   body:formDataPutTreatment
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