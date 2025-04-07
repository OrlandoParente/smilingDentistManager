( function () {

    document.addEventListener( 'DOMContentLoaded', function () {

        console.log('load upload-orthopatomogram script');

        const formUploadOrthopantomogram = document.getElementById("formUploadOrthopantomogram");
        const btnUploadOrthopantomogram = document.getElementById("btnUploadOrthopantomogram");

        const errMsg = document.getElementById("errMsgOrthopantomograms");
        const errMsgText =  document.getElementById("errMsgTextOrthopantomograms");


        btnUploadOrthopantomogram.addEventListener( "click" , function () {

             // prevent default behavior 
        event.preventDefault();

        // Reset messages
        resetMessages();

        // Recovery data from the Put Customer Form
        const formDataUploadOrthopantomogram = new FormData(formUploadOrthopantomogram);

        // NOTE: For use @ResponseBody on the server I have to sent data in json format
        // const formDataObj = Object.fromEntries(formDataPutCustomer.entries());
        // const jsonFormData = JSON.stringify(formDataObj);

        console.log( "------------------------------> " + formDataUploadOrthopantomogram.get('idCustomer')  );
        console.log( "------------------------------> " + formDataUploadOrthopantomogram.get('orthopantomogram')  );
        console.log( "------------------------------> " + formDataUploadOrthopantomogram.get('orthopantomogram').name  );

        // check if mandatory fields are not empty
        if( ! formDataUploadOrthopantomogram.get('orthopantomogram') 
            || ! formDataUploadOrthopantomogram.get('orthopantomogram').name ){

           errMsg.style.display = "block";
           errMsgText.innerText = "Choose a file to upload";

           return;
        }

        // control print
        console.log("POST url: " + formUploadOrthopantomogram.action);

        // Do post AJAX request
        fetch( formUploadOrthopantomogram.action, { 
                  method:'POST', 
                  body:formDataUploadOrthopantomogram
                  // NOTE: For use @ResponseBody on the server I have to sent data in json format
                  // headers: {
                  //    'Content-Type': 'application/json' // Imposta l'intestazione del contenuto come JSON
                  // },
                  // body: jsonFormData
                  } )
          .then( response => {
            if( response.status == 200 ) {
              
              window.location.reload();

              console.log( 'Upload Orthopantomogram success');

              alert(Success);
              // this need if you want chain another ".then"
              // return response.json();

            } else {
              errMsg.style.display = "block";
              errMsgText.innerText = "Failed to upload Orthopantomogram ";
            }
          }).catch( error => {
            console.log('Error: ' + error ); 
          });

        });


        // Reset messages
        function resetMessages(){
            errMsg.style.display = "none";
            errMsgText.innerText = "";
        }
        
    } );


} ());