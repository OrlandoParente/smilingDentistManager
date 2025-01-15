( function () {

    document.addEventListener('DOMContentLoaded', function () {

      console.log("loaded -> /js/dental-materials/increase-dental-material-quantity-form.js");

      Array.from( 
        document.getElementsByClassName("dentalMaterialIds") 
      ).forEach( element => {

    
        var dentalMaterialId = element.innerText;
        console.log( "increase quantity dentalMaterialIds: " + dentalMaterialId );


        // Automatic expense set up based on dental materials quantity ----------------------------------------
  
        const textFieldQuantity = document.getElementById( "quantity" + dentalMaterialId );
        const textFieldAmount = document.getElementById( "amount" + dentalMaterialId ); // expense text field
        
        // from requires-dental-materials
        const dentalMaterialCost = document.getElementById( "dentalMaterialCost" + dentalMaterialId ).innerText;

        textFieldQuantity.addEventListener( "change" , function(){

          textFieldAmount.value = textFieldQuantity.value * dentalMaterialCost;

        });

        // ----------------------------------------------------------------------------------------------------

        // Recovery form items
        
        const formIncreaseDentalMaterial = document.getElementById( "increaseDentalMaterialForm" + dentalMaterialId );
        console.log( "formIncreaseDentalMaterial: " + formIncreaseDentalMaterial + " ID: " + "increaseDentalMaterialForm" + dentalMaterialId );
        const btnIncreaseDentalMaterial = document.getElementById( "btnIncreaseDentalMaterial" + dentalMaterialId );
        console.log( "btnIncreaseDentalMaterial: " + btnIncreaseDentalMaterial );
        const errMsg = document.getElementById("errMsg" + "increaseDentalMaterialQuantity" + dentalMaterialId );
        const errMsgText = document.getElementById("errMsgText" + "increaseDentalMaterialQuantity" + dentalMaterialId );

        

        const btnXCloseModal = document.getElementById( "btnXCloseModal" + dentalMaterialId );


      
        // block preventDefault and do post request for manage response messages
        btnIncreaseDentalMaterial.addEventListener('click', function( event ){

          // prevent default behavior 
          event.preventDefault();

          // Reset messages
          resetMessages();

          // Recovery data from the Put IncreaseDentalMaterial Form
          const formDataIncreaseDentalMaterial = new FormData(formIncreaseDentalMaterial);

          // NOTE: For use @ResponseBody on the server I have to sent data in json format
          // const formDataObj = Object.fromEntries(formDataIncreaseDentalMaterial.entries());
          // const jsonFormData = JSON.stringify(formDataObj);

          // check if mandatory fields are not empty
          if( formDataIncreaseDentalMaterial.get('quantity').trim() === ""
              || formDataIncreaseDentalMaterial.get('amount').trim() === ""
              || formDataIncreaseDentalMaterial.get('date').trim() === ""  ){

            errMsg.style.display = "block";
            errMsgText.innerText = "Mandatory field is empty";

            return;
          }

          // control print
          console.log("Increase quantity PATCH url: " + formIncreaseDentalMaterial.action);
          console.log("params={ id = " + formDataIncreaseDentalMaterial.get('id')+ "; "
                              +" quantity= " + formDataIncreaseDentalMaterial.get('quantity') + "}")

          // Do post AJAX request
          fetch( formIncreaseDentalMaterial.action, { 
                   method:'PATCH', 
                   body:formDataIncreaseDentalMaterial
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