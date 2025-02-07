( function(){

    document.addEventListener('DOMContentLoaded', function(){

        const btnSaveChanges = document.getElementById('btnSaveDentalAnatomyChanges');
        // from common-top-page
        const urlPatchTooth = document.getElementById('urlPatchTooth');

        // memorize the teeth to delete in case of edit
        // const oldSelectedTeeth

        console.log( "btnSaveChanges -> " + btnSaveChanges );

        btnSaveChanges.addEventListener('click', function( event ){

            event.preventDefault();

            // console.log('click btnSaveDentalAnatomyChanges');

            Array.from( document.getElementsByClassName('cbDentalAnatomy') ).forEach( cb => {

                var idAppointment = document.getElementById('select' + cb.value ).value;
                var tooth = cb.value;
                var del = false;
                if( idAppointment == -1 )
                    del = true;

                console.log( 'click btnSaveDentalAnatomyChanges : idAppointment=' + idAppointment + '; tooth=' + tooth 
                            + '; del=' + del + ';' );

                var url = urlPatchTooth + '?idAppointment=' + idAppointment + '&tooth=' + tooth +'&delete='+ del;
                
                fetch( url, { method : 'PATCH' } ).then( response => {
                    
                    if( response.status == 200 ) {
                        
                        // window.location.reload();
                        

                    } else {

                        // <<<<<<<<<<<<<<<<<<================================  TO BE FIXED
                        alert("ERROR!");

                        // errMsg.style.display = "block";
                        // errMsgText.innerText = "Failed to put appointment";  
                    }

                });

            });
           
        });

    });

} ) ();