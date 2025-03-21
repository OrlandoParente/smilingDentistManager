( function () {

    document.addEventListener('DOMContentLoaded', function () {

      Array.from( 
        document.getElementsByClassName("customerIds") 
      ).forEach( element => {

    
        var customerId = element.innerText;
        console.log( "customerIds: " + customerId );


        // ################ USE THIS FOR CREATE ADD MEDICAL HISTORY #######################################################
        
        // // -------------------------------------------------------------------------------------------------
        // const btnAddProfessionalRoleSlot = document.getElementById("btnAddProfessionalRoleSlot" + customerId );
        // const slotProfessionalRole = document.getElementById("hereAppendProfessionalRoleSlot" + customerId );
        // const labelProfessionalRole = document.getElementById("professionalRoleLabel" + customerId );
        // // from requires
        // const professionalRoles = Array.from( document.getElementsByClassName("professionalRoleNames" ) );
        // const urlPostHasProfessionalRole = document.getElementById("urlPostHasProfessionalRole").getAttribute("href");
        // const urlDeleteHasProfessionalRole = document.getElementById("urlDeleteHasProfessionalRole").getAttribute("href");
        // const urlPutWorkPeriod = document.getElementById("urlPutWorkPeriod").getAttribute("href");
        // // -------------------------------------------------------------------------------------------------

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

        //   professionalRoleSelectsClass = " professionalRoleSelects" + customerId;
        //   select.setAttribute("class", "form-select me-2 " + professionalRoleSelectsClass );

        //   delButton.setAttribute("class","btn btn-danger");
        //   delButton.textContent = "X";

        //   professionalRoles.forEach( element => {

        //     const option = document.createElement("option");

        //     option.setAttribute("value", element.getAttribute('id') );
        //     option.textContent = element.innerText;
  
        //     // console.log('select option -> ' + element.getAttribute('id') + ' ' + element.innerText );

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


        // ################################################################################################################
      





        // Recovery form items
        // const htmlModalAddCustomer = document.getElementById( "putCustomerModalToggle" + customerId );
        // const bsModalAddCustomer = new bootstrap.Modal(htmlModalPutCustomer);
        const formPutCustomer = document.getElementById( "putCustomerForm" + customerId );
        console.log( "formPutCustomer: " + formPutCustomer + " ID: " + "putCustomerForm" + customerId );
        const btnPutCustomer = document.getElementById( "btnPutCustomer" + customerId );
        console.log( "btnPutCustomer: " + btnPutCustomer );
        const errMsg = document.getElementById("errMsg" + customerId );
        const errMsgText = document.getElementById("errMsgText" + customerId );

        

        const btnXCloseModal = document.getElementById( "btnXCloseModal" + customerId );


        
        // block preventDefault and do post request for manage response messages
        btnPutCustomer.addEventListener('click', function( event ){

          // prevent default behavior 
          event.preventDefault();

          // Reset messages
          resetMessages();

          // Recovery data from the Put Customer Form
          const formDataPutCustomer = new FormData(formPutCustomer);

          // NOTE: For use @ResponseBody on the server I have to sent data in json format
          // const formDataObj = Object.fromEntries(formDataPutCustomer.entries());
          // const jsonFormData = JSON.stringify(formDataObj);

          // check if mandatory fields are not empty
          // if( formDataPutCustomer.get('name') === "" || formDataPutCustomer.get('name') === " " ){

          //   errMsg.style.display = "block";
          //   errMsgText.innerText = "Field name is mandatory field to insert the customer";

          //   return;
          // }

          // control print
          console.log("PUT url: " + formPutCustomer.action);

          // Do post AJAX request
          fetch( formPutCustomer.action, { 
                   method:'PUT', 
                   body:formDataPutCustomer
                   // NOTE: For use @ResponseBody on the server I have to sent data in json format
                   // headers: {
                   //    'Content-Type': 'application/json' // Imposta l'intestazione del contenuto come JSON
                   // },
                   // body: jsonFormData
                   } )
            .then( response => {
              if( response.status == 200 ) {
                
                console.log( 'Put Customer success');

                window.location.reload();

                // this need if you want chain another ".then"
                // return response.json();

              } else {
                errMsg.style.display = "block";
                errMsgText.innerText = "Failed to put Customer ";
              }
            })
            
            // ################ USE THIS FOR CREATE ADD MEDICAL HISTORY #######################################################
            
          //   .then( data => {

          //     // First delete the existing professional roles ...
          //     const deletePromises = Array.from(
          //       // in requires-customers  "professionalRolesIds" + customerId are id of professional roles lined to the customer
          //       document.getElementsByClassName( "professionalRolesIds" + customerId )
          //     ).map( pr =>{
          //       var urlDel = urlDeleteHasProfessionalRole + '?idCustomer=' + data.id + '&idProfessionalRole=' + pr.innerText;

          //       console.log('urlDelProfessionalRoleLink: ' + urlDel );

          //       fetch( urlDel , {
          //         method : 'DELETE'
          //       }).then( response => {

          //         if( response.status == 200 ){
          //           console.log('Successful deleted < idCustomer=' + data.id + ', idProfessionalRole=' + pr.innerText + ' >');
          //         } else {
          //           throw new Error('Failed to post link < idCustomer=' + data.id + ', idProfessionalRole=' + pr.innerText + ' >');
          //         }
          //       })
          //     })

          //     return Promise.all(deletePromises).then(() => data);

          //   }).then( ( data ) => {

          //       // ... Then add the selected professional roles ...
          //       const addPromises = Array.from( 
          //         document.getElementsByClassName("professionalRoleSelects" + customerId ) 
          //       ).map( select => {

          //         var url = urlPostHasProfessionalRole + '?idCustomer=' + data.id + '&idProfessionalRole=' + select.value;

          //         console.log( 'urlPostHasProfessionalRole : ' + urlPostHasProfessionalRole );

          //         fetch( url , {
          //           method : 'POST'
          //         }).then( response => {

          //           if( response.status == 200 ){
          //             console.log('Successful post link < idCustomer=' + data.id + ', idProfessionalRole=' + select.value+' >');
          //           } else {
          //             throw new Error('Failed to post link < idCustomer=' + data.id + ', idProfessionalRole=' + select.value + ' >');
          //           }
          //         })

          //       });
              
          //       return Promise.all(addPromises).then(() => data);  

          //   }).then( ( data ) => {

          //     // ... Then add edit work periods
          //     const putWorkPeriodsPromises = Array.from( 
          //       document.getElementsByClassName("workPeriodIds" + customerId ) 
          //     ).map( el => {

          //       var workPeriodId = el.innerText;

          //       var startDate = document.getElementById("startWorkDate" + workPeriodId).value;
          //       if( startDate.trim() === "" ) startDate = "del";

          //       var endDate = document.getElementById("endWorkDate" + workPeriodId).value;
          //       if( endDate.trim() === "" ) endDate = "del";

          //       // HERE we can also edit 'workingAgreement' and 'notes'

          //       var urlWP = urlPutWorkPeriod + '?id=' + workPeriodId + '&idCustomer=' + data.id 
          //                                     + '&startDate=' + startDate + '&endDate=' + endDate;

          //       console.log( 'urlPutWorkPeriod : ' + urlPutWorkPeriod );

          //       fetch( urlWP , {
          //         method : 'PUT'
          //       }).then( response => {

          //         if( response.status == 200 ){
          //           console.log('Successful put work period id=' + workPeriodId);
          //         } else {
          //           throw new Error('Failed to put work period id=' + workPeriodId );
          //         }
          //       })

          //     });
            
          //     return Promise.all(putWorkPeriodsPromises);  

          // }).then(() => {

          //     console.log( 'LOCATION: ' + window.location.href );
          //     window.location.reload();

          //   })
            
          // ################################################################################################################
            
            .catch( error => {
             console.log('Error: ' + error ); 
            });

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