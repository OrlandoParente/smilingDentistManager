( function(){

    document.addEventListener('DOMContentLoaded', function(){

        const anchorPointNavAppointments = '#anchor-point-nav-appointments';

        const btnSaveChanges = document.getElementById('btnSaveDentalAnatomyChanges');
        // from common-top-page
        const urlPatchTooth = document.getElementById('urlPatchTooth');
        const urlUpdateTeethList = document.getElementById('urlUpdateTeethList');

        // -----------------------------------------------------------------------------------------
        // memorize appointment ids for add it to update if the new selection has no teeth selected for this 
        var oldSelectedAppointmentId = [];

        // Initialize dental anatomy check boxes
        // And selects
        Array.from( document.getElementsByClassName('cbDentalAnatomy') ).forEach( cb => {

            const selectTooth = document.getElementById( 'select' + cb.value );
            const imageTooth = document.getElementById( 'imgTooth' + cb.value );

            oldSelectedAppointmentId.push( selectTooth.value );

            console.log( 'imgTooth -> ' + imageTooth );

            // init
            funcSelectTooth( selectTooth, cb, imageTooth );

            // Add eventListener on change for select a teeth
            selectTooth.addEventListener( 'change', function(){
                console.log('change event -> item selected -> ' + selectTooth.value );
                funcSelectTooth( selectTooth, cb, imageTooth );
            });
 
        });

        // -----------------------------------------------------------------------------------------


        // -----------------------------------------------------------------------------------------

        const selectInvoiceNumber = document.getElementById("selectInvoiceNumber");
        // From common-top-page
        const urlResetSearchByInvoiceNumber = document.getElementById("urlResetSearchByInvoiceNumber");

        // Script for manage the research on invoice number
        selectInvoiceNumber.addEventListener( 'click', function ( event ) {

            // Add invoiceNumber to urlResetSearchByInvoiceNumber for get the url that we want
            var urlSearch = urlResetSearchByInvoiceNumber;
            
            if( selectInvoiceNumber.value.trim() !== "" && selectInvoiceNumber.value.trim() !== "-1"  )
                urlSearch += '/' + selectInvoiceNumber.value;

            window.location.href = urlSearch + anchorPointNavAppointments ;
        } );

        // -----------------------------------------------------------------------------------------

        // -----------------------------------------------------------------------------------------

        // Script for save changes about teeth selected 
        btnSaveChanges.addEventListener('click', function( event ){

            event.preventDefault();

            // Fetch the number of calls to do (equals to the number of appointment ids selected) --------
            var mapAppointmentIdTeeth = new Map();
            
            Array.from(document.getElementsByClassName('cbDentalAnatomy')).forEach( cb => {
                
                let appId = document.getElementById('select' + cb.value).value;
                
                // simulates continue
                if( appId == -1 || appId == '-1' ) return;

                console.log( 'APP-ID : ' + appId );

                if( mapAppointmentIdTeeth.has( appId ) ){
                    let strTeeth = mapAppointmentIdTeeth.get( appId );
                    strTeeth += ',' + cb.value;
                    mapAppointmentIdTeeth.set( appId, strTeeth );
                } else {
                    mapAppointmentIdTeeth.set( appId, '' + cb.value );
                }

            } );

            // Add idAppointment which new selection is empty (for update them as well)
            oldSelectedAppointmentId.forEach( idAppSelectedBefore => {
                if( idAppSelectedBefore != -1 && ! mapAppointmentIdTeeth.has( idAppSelectedBefore ) ) {
                    mapAppointmentIdTeeth.set( idAppSelectedBefore, '' );
                    console.log(' ---------------------------------> old selected id appointments -> ' + idAppSelectedBefore );
                }
            } );

            var keysAppointmentIdTeeth = Array.from( mapAppointmentIdTeeth.keys() );

            // -------------------------------------------------------------------------------------------

            keysAppointmentIdTeeth.forEach( k => console.log( 'keysAppointmentIdTeeth -> ' + k ) );

            Promise.all( keysAppointmentIdTeeth.map( appId => {

                let urlPatchTeeth = urlUpdateTeethList + '?idAppointment=' + appId + '&teeth=' + mapAppointmentIdTeeth.get(appId);

                console.log('url patch teeth : ' + urlPatchTeeth );

                fetch(
                    urlPatchTeeth, 
                    { method: 'PATCH' }
                ).then( response => {
                    if( response.status === 200 ){
                        console.log('teeth successfully updated');
                    } else {
                        // <=================== TO EDIT: show error message
                        console.log('Error response code: ' + response.status );
                        
                    }
                })


            })).then(() => {
                // Changes saved successfully
                console.log('Changes saved successfully.');


                // <=================== TO EDIT: show sucessful message 
                alert('changes successful saved');

                // .split('#')[0] is needed for remove possible other anchor points 
                window.location.href = window.location.href.split('#')[0] +  anchorPointNavAppointments ;
                // window.location.reload();

            }).catch( (err) => {
                 console.log('An error occurred while saving changes.');
                 console.log('ERROR: ' + err);
                 alert('ERROR');
            });

        } );

        // -----------------------------------------------------------------------------------------

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