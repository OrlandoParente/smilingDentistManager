( function () {

    document.addEventListener('DOMContentLoaded', function () {

      console.log("loaded -> /js/dental-materials/put-dental-materials-form.js");

      Array.from( 
        document.getElementsByClassName("dentalMaterialIds") 
      ).forEach( element => {

    
        var dentalMaterialId = element.innerText;
        console.log( "dentalMaterialIds: " + dentalMaterialId );

        // Recovery form items
        // const htmlModalAddDentalMaterial = document.getElementById( "putDentalMaterialModalToggle" + dentalMaterialId );
        // const bsModalAddDentalMaterial = new bootstrap.Modal(htmlModalPutDentalMaterial);
        const formPutDentalMaterial = document.getElementById( "putDentalMaterialForm" + dentalMaterialId );
        console.log( "formPutDentalMaterial: " + formPutDentalMaterial + " ID: " + "putDentalMaterialForm" + dentalMaterialId );
        const btnPutDentalMaterial = document.getElementById( "btnPutDentalMaterial" + dentalMaterialId );
        console.log( "btnPutDentalMaterial: " + btnPutDentalMaterial );
        const errMsg = document.getElementById("errMsg" + dentalMaterialId );
        const errMsgText = document.getElementById("errMsgText" + dentalMaterialId );

        

        const btnXCloseModal = document.getElementById( "btnXCloseModal" + dentalMaterialId );


      
        // block preventDefault and do post request for manage response messages
        btnPutDentalMaterial.addEventListener('click', function( event ){

          // prevent default behavior 
          event.preventDefault();

          // Reset messages
          resetMessages();

          // Recovery data from the Put DentalMaterial Form
          const formDataPutDentalMaterial = new FormData(formPutDentalMaterial);

          // NOTE: For use @ResponseBody on the server I have to sent data in json format
          // const formDataObj = Object.fromEntries(formDataPutDentalMaterial.entries());
          // const jsonFormData = JSON.stringify(formDataObj);

          // check if mandatory fields are not empty
          if( formDataPutDentalMaterial.get('name').trim() === ""  ){

            errMsg.style.display = "block";
            errMsgText.innerText = "Mandatory field is empty";

            return;
          }

          // control print
          console.log("PUT url: " + formPutDentalMaterial.action);

          // Do post AJAX request
          fetch( formPutDentalMaterial.action, { 
                   method:'PUT', 
                   body:formDataPutDentalMaterial
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