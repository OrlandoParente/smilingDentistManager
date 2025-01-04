//  IIFEs Immediately Invoked Function Expressions
( function() {

    document.addEventListener( 'DOMContentLoaded' , function (){

        // Add 
        Array.from( 
            document.getElementsByClassName("employeeIds") 
        ).forEach( element => {

            var employeeId = element.innerText;

            console.log("employeeId: " + employeeId);


            btnDelete = document.getElementById('btnDeleteEmployee' + employeeId);
          
            btnDelete.addEventListener('click', function(){
                // we need the anonymous function otherwise it call immediatly the function  
                deleteAppointmentById(employeeId);
            });

        });

        // Button for delete the employee
        function deleteAppointmentById(id){

            // from common-top-page fragment
            var url = document.getElementById('urlDeleteEmployeeById').getAttribute('href') + '?id=' + id;
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
                    alert("Failed to Delete the Employee");
                }
            });
        }

    });

 } )();