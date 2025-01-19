( function () {

    document.addEventListener('DOMContentLoaded', function () {
        // Recovery form items
        // const htmlModalAddEmployee = document.getElementById("addEmployeeModalToggle");
        // const bsModalAddEmployee = new bootstrap.Modal(htmlModalAddEmployee);
        const formAddEmployee = document.getElementById("addEmployeeForm");
        const btnAddEmployee = document.getElementById("btnAddEmployee");
        const errMsg = document.getElementById("errMsg");
        const errMsgText = document.getElementById("errMsgText");

        const btnXCloseModal = document.getElementById("btnXCloseModal");

        // -------------------------------------------------------------------------------------------------
        const btnAddProfessionalRoleSlot = document.getElementById("btnAddProfessionalRoleSlot");
        const slotProfessionalRole = document.getElementById("hereAppendProfessionalRoleSlot");
        const labelProfessionalRole = document.getElementById("professionalRoleLabel");
        // from requires
        const professionalRoles = Array.from( document.getElementsByClassName("professionalRoleNames") );
        const urlPostHasProfessionalRole = document.getElementById("urlPostHasProfessionalRole").getAttribute("href");
        // -------------------------------------------------------------------------------------------------

        // reload on close for remove childs added with 'btnAddProfessionalRoleSlot'
        btnXCloseModal.addEventListener('click', function(event){
          window.location.reload();
        });

        //
        btnAddProfessionalRoleSlot.addEventListener('click', function( event ){

          const row = document.createElement("div");
          const div = document.createElement("div");
          const label = document.createElement("label");
          const select = document.createElement("select");
          const delButton = document.createElement("button");

          row.setAttribute("class","row g-3 mt-3");
          div.setAttribute("class","col-12  d-flex align-items-center mt-3")

          label.textContent = labelProfessionalRole.innerText;
          label.setAttribute("class","form-label mb-1 col-md-auto me-2");

          select.setAttribute("class", "form-select me-2 professionalRoleSelects");

          delButton.setAttribute("class","btn btn-danger");
          delButton.textContent = "X";

          professionalRoles.forEach( element => {

            const option = document.createElement("option");

            option.setAttribute("value", element.getAttribute('id') );
            option.textContent = element.innerText;
  
            // console.log( element.innerText );

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
        btnAddEmployee.addEventListener('click', function( event ){
         
          // prevent default behavior 
          event.preventDefault();

          // Reset messages
          resetMessages();

          // Recovery data from the Add Employee Form
          const formDataAddEmployee = new FormData(formAddEmployee);

          // NOTE: For use @ResponseBody on the server I have to sent data in json formatmployee/calendar/day
          // const formDataObj = Object.fromEntries(formDataAddEmployee.entries());
          // const jsonFormData = JSON.stringify(formDataObj);

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
          console.log("POST url: " + formAddEmployee.action);

          // Do post AJAX request
          fetch( formAddEmployee.action, { 
                   method:'POST', 
                   body:formDataAddEmployee
                   // NOTE: For use @ResponseBody on the server I have to sent data in json format
                   // headers: {
                   //    'Content-Type': 'application/json' // Imposta l'intestazione del contenuto come JSON
                   // },
                   // body: jsonFormData
                   } )
            .then( response => {
              if( response.status == 200 ) {

                // console.log( "LOCATION: " + window.location.href );
                // window.location.reload();
                // console.log( response.json() )


                return response.json();

                
                
              } else {
                errMsg.style.display = "block";
                errMsgText.innerText = "Failed to post the employee";
              }
            }).then( data => {

              Array.from( 
                document.getElementsByClassName("professionalRoleSelects") 
              ).forEach( select => {

                console.log("------> id employee : " + data.id );
                console.log( "------> selct value : " + select.value );

                var url = urlPostHasProfessionalRole + '?idEmployee=' + data.id + '&idProfessionalRole=' + select.value;

                fetch( url , {
                  method : 'POST'
                }).then( response => {

                  if( response.status == 200 ){
                    console.log( "LOCATION: " + window.location.href );
                    window.location.reload();
                    console.log( response.json() )
                  }

                })


              });


            }).catch( error => {
              console.log('Error: ' + error);
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