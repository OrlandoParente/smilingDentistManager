( function () {

    document.addEventListener('DOMContentLoaded', function () {

      // Recovery form items
      const formPutEmployee = document.getElementById( "putEmployeeForm"  );
      console.log( "formPutEmployee: " + formPutEmployee + " ID: " + "putEmployeeForm"  );
      const btnPutEmployee = document.getElementById( "btnPutEmployee"  );
      console.log( "btnPutEmployee: " + btnPutEmployee );
      const errMsg = document.getElementById("errMsg" + "personal-info" );
      const errMsgText = document.getElementById("errMsgText" + "personal-info" );

      

      // const btnXCloseModal = document.getElementById( "btnXCloseModal" + employeeId );


      // -------------------------------------------------------------------------------------------------
      const btnAddProfessionalRoleSlot = document.getElementById("btnAddProfessionalRoleSlot"  );
      const slotProfessionalRole = document.getElementById("hereAppendProfessionalRoleSlot"  );
      const labelProfessionalRole = document.getElementById("professionalRoleLabel"  );
      // from requires
      const professionalRoles = Array.from( document.getElementsByClassName("professionalRoleNames" ) );
      const urlPostHasProfessionalRole = document.getElementById("urlPostHasProfessionalRole").getAttribute("href");
      const urlDeleteHasProfessionalRole = document.getElementById("urlDeleteHasProfessionalRole").getAttribute("href");
      // const urlPutWorkPeriod = document.getElementById("urlPutWorkPeriod").getAttribute("href");
      // -------------------------------------------------------------------------------------------------

      //
      btnAddProfessionalRoleSlot.addEventListener('click', function( event ){

        const row = document.createElement("div");
        const div = document.createElement("div");
        const label = document.createElement("label");
        const select = document.createElement("select");
        const delButton = document.createElement("button");

        row.setAttribute("class","row g-3 mt-3");
        div.setAttribute("class","col-12  d-flex align-items-center mt-1")

        label.textContent = labelProfessionalRole.innerText;
        label.setAttribute("class","form-label mb-1 col-md-auto me-2");

        professionalRoleSelectsClass = " professionalRoleSelects" ;
        select.setAttribute("class", "form-select me-2 " + professionalRoleSelectsClass );

        delButton.setAttribute("class","btn btn-danger");
        delButton.textContent = "X";

        professionalRoles.forEach( element => {

          const option = document.createElement("option");

          option.setAttribute("value", element.getAttribute('id') );
          option.textContent = element.innerText;

          // console.log('select option -> ' + element.getAttribute('id') + ' ' + element.innerText );

          select.appendChild( option );

        });

        div.appendChild( label );
        div.appendChild( select );
        div.appendChild( delButton );
        row.appendChild( div );
        slotProfessionalRole.appendChild( row );

        delButton.addEventListener('click', function( event ){

          event.preventDefault();

          slotProfessionalRole.removeChild( row );
        });

      });



      // block preventDefault and do post request for manage response messages
      btnPutEmployee.addEventListener('click', function( event ){

        // prevent default behavior 
        event.preventDefault();

        // Reset messages
        resetMessages();

        // Recovery data from the Put Employee Form
        const formDataPutEmployee = new FormData(formPutEmployee);

        // NOTE: For use @ResponseBody on the server I have to sent data in json format
        // const formDataObj = Object.fromEntries(formDataPutEmployee.entries());
        // const jsonFormData = JSON.stringify(formDataObj);

        // check if mandatory fields are not empty
        // if( formDataPutEmployee.get('name') === "" || formDataPutEmployee.get('name') === " " ){

        //   errMsg.style.display = "block";
        //   errMsgText.innerText = "Field name is mandatory field to insert the employee";

        //   return;
        // }

        // check if there are duplicated professional roles selected -----------------------------------

        // from common-top-page
        const errMsgDuplicateProfessionalRoleSelected = document.getElementById("errMsgDuplicateProfessionalRoleSelected").innerText; 
        var arrProfRolesSelected = Array.from( document.getElementsByClassName("professionalRoleSelects"  ) );

        for( let i = 0; i < arrProfRolesSelected.length; i++) {
          for( let j = i + 1; j < arrProfRolesSelected.length; j ++ ) {
            if( arrProfRolesSelected[i].options[ arrProfRolesSelected[i].selectedIndex ].innerText.trim() 
                === arrProfRolesSelected[j].options[arrProfRolesSelected[j].selectedIndex].innerText.trim() ) {

              console.log(  arrProfRolesSelected[i].options[ arrProfRolesSelected[i].selectedIndex ].innerText 
                            + " === " 
                            + arrProfRolesSelected[j].options[arrProfRolesSelected[j].selectedIndex].innerText  )

              errMsg.style.display = "block";
              errMsgText.innerText = errMsgDuplicateProfessionalRoleSelected;

              return;
            }
          }
        }

        // ---------------------------------------------------------------------------------------------

        // control print
        console.log("PUT url: " + formPutEmployee.action);

        // Do post AJAX request
        fetch( formPutEmployee.action, { 
                  method:'PUT', 
                  body:formDataPutEmployee
                  // NOTE: For use @ResponseBody on the server I have to sent data in json format
                  // headers: {
                  //    'Content-Type': 'application/json' // Imposta l'intestazione del contenuto come JSON
                  // },
                  // body: jsonFormData
                  } )
          .then( response => {
            if( response.status == 200 ) {
              // window.location.reload();
              console.log( 'Put Employee success');
              return response.json();

            } else {
              errMsg.style.display = "block";
              errMsgText.innerText = "Failed to put Employee ";
            }
          }).then( data => {

            // First delete the existing professional roles ...
            const deletePromises = Array.from(
              // in requires-employees  "professionalRolesIds"  are id of professional roles linked to the employee
              document.getElementsByClassName( "professionalRolesIds"  )
            ).map( pr =>{
              var urlDel = urlDeleteHasProfessionalRole + '?idEmployee=' + data.id + '&idProfessionalRole=' + pr.innerText;

              console.log('urlDelProfessionalRoleLink: ' + urlDel );

              fetch( urlDel , {
                method : 'DELETE'
              }).then( response => {

                if( response.status == 200 ){
                  console.log('Successful deleted < idEmployee=' + data.id + ', idProfessionalRole=' + pr.innerText + ' >');
                } else {
                  throw new Error('Failed to post link < idEmployee=' + data.id + ', idProfessionalRole=' + pr.innerText + ' >');
                }
              })
            })

            return Promise.all(deletePromises).then(() => data);

          }).then( ( data ) => {

              // ... Then add the selected professional roles ...
              const addPromises = Array.from( 
                document.getElementsByClassName("professionalRoleSelects"  ) 
              ).map( select => {

                var url = urlPostHasProfessionalRole + '?idEmployee=' + data.id + '&idProfessionalRole=' + select.value;

                console.log( 'urlPostHasProfessionalRole : ' + urlPostHasProfessionalRole );

                fetch( url , {
                  method : 'POST'
                }).then( response => {

                  if( response.status == 200 ){
                    console.log('Successful post link < idEmployee=' + data.id + ', idProfessionalRole=' + select.value+' >');
                  } else {
                    throw new Error('Failed to post link < idEmployee=' + data.id + ', idProfessionalRole=' + select.value + ' >');
                  }
                })

              });
            
              return Promise.all(addPromises).then(() => data);  

          // }) .then( ( data ) => {

          //   // ... Then add edit work periods
          //   const putWorkPeriodsPromises = Array.from( 
          //     document.getElementsByClassName("workPeriodIds"  ) 
          //   ).map( el => {

          //     var workPeriodId = el.innerText;

          //     var startDate = document.getElementById("startWorkDate" + workPeriodId).value;
          //     if( startDate.trim() === "" ) startDate = "del";

          //     var endDate = document.getElementById("endWorkDate" + workPeriodId).value;
          //     if( endDate.trim() === "" ) endDate = "del";

          //     // HERE we can also edit 'workingAgreement' and 'notes'

          //     var urlWP = urlPutWorkPeriod + '?id=' + workPeriodId + '&idEmployee=' + data.id 
          //                                   + '&startDate=' + startDate + '&endDate=' + endDate;

          //     console.log( 'urlPutWorkPeriod : ' + urlPutWorkPeriod );

          //     fetch( urlWP , {
          //       method : 'PUT'
          //     }).then( response => {

          //       if( response.status == 200 ){
          //         console.log('Successful put work period id=' + workPeriodId);
          //       } else {
          //         throw new Error('Failed to put work period id=' + workPeriodId );
          //       }
          //     })

          //   });
          
          //   return Promise.all(putWorkPeriodsPromises);  

        }).then(() => {

            console.log( 'LOCATION: ' + window.location.href );
            window.location.reload();

          }).catch( error => {
            console.log('Error: ' + error ); 
          });

      });

      // Reset messages on modal close
      // btnXCloseModal.addEventListener("click", resetMessages);

      // Reset messages
      function resetMessages(){
        errMsg.style.display = "none";
        errMsgText.innerText = "";
      }

    });
 
})()