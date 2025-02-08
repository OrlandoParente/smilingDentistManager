( function(){

    document.addEventListener('DOMContentLoaded', function(){

        const btnSaveChanges = document.getElementById('btnSaveDentalAnatomyChanges');
        // from common-top-page
        const urlPatchTooth = document.getElementById('urlPatchTooth');

        // memorize the teeth to delete in case of edit
        var oldSelectedTeeth = [];

        console.log( "btnSaveChanges -> " + btnSaveChanges );

        // Initialize dental anatomy check boxes
        // And selects
        Array.from( document.getElementsByClassName('cbDentalAnatomy') ).forEach( cb => {

            const selectTooth = document.getElementById( 'select' + cb.value );
            const imageTooth = document.getElementById( 'imgTooth' + cb.value );

            oldSelectedTeeth.push( {idAppointment : selectTooth.value, tooth: cb.value } );

            console.log( 'imgTooth -> ' + imageTooth );

            // init
            funcSelectTooth( selectTooth, cb, imageTooth );

            // Add eventListener on change for select a teeth
            selectTooth.addEventListener( 'change', function(){
                console.log('change event -> item selected -> ' + selectTooth.value );
                funcSelectTooth( selectTooth, cb, imageTooth );
            });
 
        });

        btnSaveChanges.addEventListener('click', function( event ){

            event.preventDefault();

            // First we delete all the old selection
            Promise.all(oldSelectedTeeth.map(el => {
                console.log(el.idAppointment + ' ' + el.tooth);

                // "Continue" if idAppointment == -1
                if (el.idAppointment == -1) {
                    console.log('Skipping fetch for tooth=' + el.tooth + ' because idAppointment is -1');
                    return Promise.resolve();
                }

                var urlDelOldTeethSelection = urlPatchTooth + '?idAppointment=' + el.idAppointment + '&tooth=' + el.tooth + '&delete=true';

                return fetch(urlDelOldTeethSelection, { method: 'PATCH' }).then(response => {
                    if (response.status !== 200) {
                        // <<<<<<<<<<<<<<<<<<================================  TO BE FIXED
                        alert("ERROR!");

                        // errMsg.style.display = "block";
                        // errMsgText.innerText = "Failed to put appointment";  

                        return Promise.reject();
                    }
                });
            })).then(() => {
                // Then, save the new sected values 
                return Promise.all(Array.from(document.getElementsByClassName('cbDentalAnatomy')).map(cb => {

                    var idAppointment = document.getElementById('select' + cb.value).value;
                    var tooth = cb.value;
                    // var del = false;
                    // if (idAppointment == -1) del = true;

                    // "Continue" if idAppointment == -1
                    if (idAppointment == -1) {
                        console.log('Skipping fetch for tooth=' + tooth + ' because idAppointment is -1');
                        return Promise.resolve();
                    }

                    console.log('click btnSaveDentalAnatomyChanges : idAppointment=' + idAppointment + '; tooth=' + tooth + '; del=false'  + ';');

                    var url = urlPatchTooth + '?idAppointment=' + idAppointment + '&tooth=' + tooth + '&delete=false';

                    return fetch(url, { method: 'PATCH' }).then(response => {
                        if (response.status !== 200) {
                            
                            // <<<<<<<<<<<<<<<<<<================================  TO BE FIXED
                            alert("ERROR!");

                            // errMsg.style.display = "block";
                            // errMsgText.innerText = "Failed to put appointment";  

                            return Promise.reject();
                        }
                    });
                }));
            }).then(() => {
                // Changes saved successfully
                console.log('Changes saved successfully.');
                window.location.reload();

                // <=================== TO EDIT: show sucessful message 

            }).catch( () => {
                console.log('An error occurred while saving changes.');
            });


        } );
            

        // check the checkbox and highlight the tooth on the image
        function funcSelectTooth( selTooth, cbTooth, imgTooth ) {

            if( selTooth.value.trim() !== "" && selTooth.value.trim() !== "-1" ) {
                cbTooth.checked = true;
                imgTooth.style.display = "block";
            } else {
                cbTooth.checked = false;
                imgTooth.style.display = "none";
            }

        }
        

    });

} ) ();