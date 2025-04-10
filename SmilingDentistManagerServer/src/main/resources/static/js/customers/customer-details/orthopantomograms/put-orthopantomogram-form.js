( function() {

    document.addEventListener('DOMContentLoaded', function() {

        console.log('load put-orthopantomogram-form.js');

        Array.from( document.getElementsByClassName("orthopantomogramIds") ).forEach( element => {

            var orthoId = element.innerText;
            console.log( 'put-orthopantomogram-form.js -> orthoId : ' + orthoId );

            const formPutOrthopantomogram = document.getElementById( 'putOrthopantomogramForm' + orthoId );
            const btnPutOrthopantomogram = document.getElementById( 'btnPutOrthopantomogram' + orthoId );

            const errMsg = document.getElementById("errMsgOrthopantomogram" + orthoId );
            const errMsgText =  document.getElementById("errMsgTextOrthopantomogram" + orthoId );
    
            console.log( 'formPutOrthopantomogram:' + 'putOrthopantomogramForm' + orthoId + ' Element: ' + formPutOrthopantomogram );
            console.log( 'btnPutOrthopantomogram:' + 'putOrthopantomogramForm' + orthoId + ' Element: ' + formPutOrthopantomogram );

            btnPutOrthopantomogram.addEventListener( 'click', function( event ) {

                event.preventDefault();

                resetMessages();

                formDataPutOrthopantomogram = new FormData( formPutOrthopantomogram );

                fetch( formPutOrthopantomogram.action , {
                    method : 'PUT',
                    body : formDataPutOrthopantomogram
                } )
                .then( response => {

                    if( response.status === 200 ){

                        window.location.reload();
                        console.log( 'Orthopantomogram updated with success' );
                    } else {
                        errMsg.style.display = "block";
                        errMsgText.innerText = "Failed to upload Orthopantomogram ";
        
                    }

                } )

            } );


            // Reset messages
            function resetMessages(){
                errMsg.style.display = "none";
                errMsgText.innerText = "";
            }


        });

    });


} ) ();