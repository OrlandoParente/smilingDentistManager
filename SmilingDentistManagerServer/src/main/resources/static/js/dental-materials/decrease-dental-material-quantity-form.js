( function () {

    document.addEventListener('DOMContentLoaded', function () {

      console.log("loaded -> /js/dental-materials/decrese-dental-material-quantity-form.js");

      Array.from( 
        document.getElementsByClassName("dentalMaterialIds") 
      ).forEach( element => {

    
        var dentalMaterialId = element.innerText;
        console.log( "decrease quantity dentalMaterialIds: " + dentalMaterialId );

        // Recovery form items
        // const htmlModalAddDecreaseDentalMaterial = document.getElementById( "decreaseDentalMaterialModalToggle" + dentalMaterialId );
        // const bsModalAddDecreaseDentalMaterial = new bootstrap.Modal(htmlModalDecreaseDentalMaterial);
        const formDecreaseDentalMaterial = document.getElementById( "decreaseDentalMaterialForm" + dentalMaterialId );
        console.log( "formDecreaseDentalMaterial: " + formDecreaseDentalMaterial + " ID: " + "decreaseDentalMaterialForm" + dentalMaterialId );
        const btnDecreaseDentalMaterial = document.getElementById( "btnDecreaseDentalMaterial" + dentalMaterialId );
        console.log( "btnDecreaseDentalMaterial: " + btnDecreaseDentalMaterial );
        const errMsg = document.getElementById("errMsg" + dentalMaterialId );
        const errMsgText = document.getElementById("errMsgText" + dentalMaterialId );

        

        const btnXCloseModal = document.getElementById( "btnXCloseModal" + dentalMaterialId );


      
        // block preventDefault and do post request for manage response messages
        btnDecreaseDentalMaterial.addEventListener('click', function( event ){

          // prevent default behavior 
          event.preventDefault();

          // Reset messages
          resetMessages();

          // Recovery data from the Put DecreaseDentalMaterial Form
          const formDataDecreaseDentalMaterial = new FormData(formDecreaseDentalMaterial);

          // NOTE: For use @ResponseBody on the server I have to sent data in json format
          // const formDataObj = Object.fromEntries(formDataDecreaseDentalMaterial.entries());
          // const jsonFormData = JSON.stringify(formDataObj);

          // check if mandatory fields are not empty
          if( formDataDecreaseDentalMaterial.get('quantity').trim() === ""  ){

            errMsg.style.display = "block";
            errMsgText.innerText = "Mandatory field is empty";

            return;
          }

          // control print
          console.log("Decrease quantity PATCH url: " + formDecreaseDentalMaterial.action);
          console.log("params={ id = " + formDataDecreaseDentalMaterial.get('id')+ "; "
                              +" quantity= " + formDataDecreaseDentalMaterial.get('quantity') + "}")

          // Do post AJAX request
          fetch( formDecreaseDentalMaterial.action, { 
                   method:'PATCH', 
                   body:formDataDecreaseDentalMaterial
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