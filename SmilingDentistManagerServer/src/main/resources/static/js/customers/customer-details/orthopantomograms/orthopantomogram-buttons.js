//  IIFEs Immediately Invoked Function Expressions
( function() {

    document.addEventListener( 'DOMContentLoaded' , function (){

        // Add 
        Array.from( 
            document.getElementsByClassName("orthopantomogramIds") 
        ).forEach( element => {

            var orthopantomogramId = element.innerText;

            console.log("orthopantomogramId: " + orthopantomogramId);


            btnDelete = document.getElementById('btnDeleteOrthopantogram' + orthopantomogramId);
          
            btnDelete.addEventListener('click', function(){
                // we need the anonymous function otherwise it call immediatly the function  
                deleteOrthopantomogramById(orthopantomogramId);
            });

        });

        // Button for delete the customer
        function deleteOrthopantomogramById(id){

            // from common-top-page fragment
            var url = document.getElementById('urlDeleteOrthopantomogram').getAttribute('href') + '?id=' + id;
            console.log(url);

            fetch( url,{
                method: 'DELETE',
                headers: {
                    'Content-Type': 'application/json'
                }
                // body:{
                //     'id':id
                // }
            })
            .then( response => {
                if( response.ok ){
                    window.location.reload();
                } else {
                    alert("Failed to Delete the Orthopantomogram");
                }
            });
        }

    });

 } )();