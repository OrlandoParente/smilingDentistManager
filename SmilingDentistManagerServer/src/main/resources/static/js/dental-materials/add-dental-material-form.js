( function () {

    document.addEventListener('DOMContentLoaded', function () {

        console.log("loaded -> /js/dental-materials/add-dental-materials-form.js");

        // Recovery form items
        const formAddDentalMaterial = document.getElementById("addDentalMaterialForm");
        const btnAddDentalMaterial = document.getElementById("btnAddDentalMaterial");
        const errMsg = document.getElementById("errMsg");
        const errMsgText = document.getElementById("errMsgText");

        const btnXCloseModal = document.getElementById("btnXCloseModal");


      
        // block preventDefault and do post request for manage response messages
        btnAddDentalMaterial.addEventListener('click', function( event ){
         
          // prevent default behavior 
          event.preventDefault();

          // Reset messages
          resetMessages();

          // Recovery data from the Add DentalMaterial Form
          const formDataAddDentalMaterial = new FormData(formAddDentalMaterial);

          // NOTE: For use @ResponseBody on the server I have to sent data in json formatmployee/calendar/day
          // const formDataObj = Object.fromEntries(formDataAddDentalMaterial.entries());
          // const jsonFormData = JSON.stringify(formDataObj);


          // control print
          console.log("POST url: " + formAddDentalMaterial.action);

          // check if mandatory fields are not empty
          if( formDataAddDentalMaterial.get('name').trim() === ""  ){

            errMsg.style.display = "block";
            errMsgText.innerText = "Mandatory field is empty";

            return;
          }

          // Do post AJAX request
          fetch( formAddDentalMaterial.action, { 
                   method:'POST', 
                   body:formDataAddDentalMaterial
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
                errMsgText.innerText = "Failed to post the treatment";
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