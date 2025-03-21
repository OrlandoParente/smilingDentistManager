( function(){

    document.addEventListener('DOMContentLoaded', function(){

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
        const urlResetSearchByInvoiceNumber = document.getElementById("urlResetSearchByInvoiceNumber");

        // Script for manage the research on invoice number
        selectInvoiceNumber.addEventListener( 'click', function ( event ) {

            // Add invoiceNumber to urlResetSearchByInvoiceNumber for get the url that we want
            var urlSearch = urlResetSearchByInvoiceNumber;
            
            if( selectInvoiceNumber.value.trim() !== "" && selectInvoiceNumber.value.trim() !== "-1"  )
                urlSearch += '/' + selectInvoiceNumber.value;

            window.location.href = urlSearch;
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
                if( appId == -1 )   return;

                if( mapAppointmentIdTeeth.has( appId ) ){
                    let strTeeth = mapAppointmentIdTeeth.get( appId );
                    strTeeth += ',' + cb.value
                    mapAppointmentIdTeeth.set( appId, strTeeth )
                } else {
                    mapAppointmentIdTeeth.set( appId, '' + cb.value );
                }

            } );

            // Add idAppointment which new selection is empty (for update them as well)
            oldSelectedAppointmentId.forEach( idAppSelectedBefore => {
                if( ! mapAppointmentIdTeeth.has( idAppSelectedBefore ) ) {
                    mapAppointmentIdTeeth.set( idAppSelectedBefore, '' );
                }
            } );

            var keysAppointmentIdTeeth = Array.from( mapAppointmentIdTeeth.keys() );

            // -------------------------------------------------------------------------------------------


            Promise.all( keysAppointmentIdTeeth.map( appId => {

                let urlPatchTeeth = urlUpdateTeethList + '?idAppointment=' + appId + '&teeth=' + mapAppointmentIdTeeth.get(appId);

                fetch(
                    urlPatchTeeth, 
                    { method: 'PATCH' }
                ).then( response => {
                    if( response === 200 ){
                        console.log('teeth successfully updated');
                    } else {
                        // <=================== TO EDIT: show error message
                    }
                })


            })).then(() => {
                // Changes saved successfully
                console.log('Changes saved successfully.');
                window.location.reload();

                // <=================== TO EDIT: show sucessful message 

             }).catch( () => {
                 console.log('An error occurred while saving changes.');
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