( function () {

    document.addEventListener('DOMContentLoaded', function () {


        // Use this for addMedicalHistory in the post form ###################################################
        
        // -------------------------------------------------------------------------------------------------
        
        // const btnAddProfessionalRoleSlot = document.getElementById("btnAddProfessionalRoleSlot");
        // const slotProfessionalRole = document.getElementById("hereAppendProfessionalRoleSlot");
        // const labelProfessionalRole = document.getElementById("professionalRoleLabel");
        // // from requires
        // const professionalRoles = Array.from( document.getElementsByClassName("professionalRoleNames") );
        // const urlPostHasProfessionalRole = document.getElementById("urlPostHasProfessionalRole").getAttribute("href");
        // -------------------------------------------------------------------------------------------------



        // //
        // btnAddProfessionalRoleSlot.addEventListener('click', function( event ){

        //   const row = document.createElement("div");
        //   const div = document.createElement("div");
        //   const label = document.createElement("label");
        //   const select = document.createElement("select");
        //   const delButton = document.createElement("button");

        //   row.setAttribute("class","row g-3 mt-3");
        //   div.setAttribute("class","col-12  d-flex align-items-center mt-3")

        //   label.textContent = labelProfessionalRole.innerText;
        //   label.setAttribute("class","form-label mb-1 col-md-auto me-2");

        //   select.setAttribute("class", "form-select me-2 professionalRoleSelects");

        //   delButton.setAttribute("class","btn btn-danger");
        //   delButton.textContent = "X";

        //   professionalRoles.forEach( element => {

        //     const option = document.createElement("option");

        //     option.setAttribute("value", element.getAttribute('id') );
        //     option.textContent = element.innerText;
  
        //     // console.log( element.innerText );

        //     select.appendChild( option );

        //   });

        //   div.appendChild( label );
        //   div.appendChild( select );
        //   div.appendChild( delButton );
        //   row.appendChild( div );
        //   slotProfessionalRole.appendChild( row );

        //   delButton.addEventListener('click', function( event ){

        //     event.preventDefault();

        //     slotProfessionalRole.removeChild( row );
        //   });

        // });

        // ###################################################################################################

        // Recovery form items
        // const htmlModalAddCustomer = document.getElementById("addCustomerModalToggle");
        // const bsModalAddCustomer = new bootstrap.Modal(htmlModalAddCustomer);
        const formAddCustomer = document.getElementById("addCustomerForm");
        const btnAddCustomer = document.getElementById("btnAddCustomer");
        const errMsg = document.getElementById("errMsg");
        const errMsgText = document.getElementById("errMsgText");

        const btnXCloseModal = document.getElementById("btnXCloseModal");

        // block preventDefault and do post request for manage response messages
        btnAddCustomer.addEventListener('click', function( event ){
         
          // prevent default behavior 
          event.preventDefault();

          // Reset messages
          resetMessages();

          // Recovery data from the Add Customer Form
          const formDataAddCustomer = new FormData(formAddCustomer);

          // NOTE: For use @ResponseBody on the server I have to sent data in json formatmployee/calendar/day
          // const formDataObj = Object.fromEntries(formDataAddCustomer.entries());
          // const jsonFormData = JSON.stringify(formDataObj);


          // control print
          console.log("POST url: " + formAddCustomer.action);

          // Do post AJAX request
          fetch( formAddCustomer.action, { 
                   method:'POST', 
                   body:formDataAddCustomer
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
            })

            // EDIT THIS FOR ADD MEDICAL HISTORY ALREADY IN THE POST FORM ------------------------
            
            // .then( data => {

            //   Array.from( 
            //     document.getElementsByClassName("professionalRoleSelects") 
            //   ).forEach( select => {

            //     console.log("------> id customer : " + data.id );
            //     console.log( "------> selct value : " + select.value );

            //     var url = urlPostHasProfessionalRole + '?idCustomer=' + data.id + '&idProfessionalRole=' + select.value;

            //     fetch( url , {
            //       method : 'POST'
            //     }).then( response => {

            //       if( response.status == 200 ){
            //         console.log( "LOCATION: " + window.location.href );
            //         window.location.reload();
            //         console.log( response.json() )
            //       }

            //     })


            //   });


            // })
            
            // -----------------------------------------------------------------------------------
            
            .catch( error => {
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

          // reload on close for remove childs added with 'btnAddProfessionalRoleSlot'
          window.location.reload();
        });

        // Reset messages
        function resetMessages(){
         errMsg.style.display = "none";
         errMsgText.innerText = "";
        }

      });

      
})()