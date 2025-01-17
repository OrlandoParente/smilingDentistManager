//  IIFEs Immediately Invoked Function Expressions
( function() {

    document.addEventListener( 'DOMContentLoaded' , function (){

        // Delete appoitnemtn 
        Array.from( 
            document.getElementsByClassName("appointmentIds") 
        ).forEach( element => {

            var appointmentId = element.innerText;

            btnDelete = document.getElementById('btnDeleteAppointment' + appointmentId);
          
            btnDelete.addEventListener('click', function(){
                // we need the anonymous function otherwise it call immediatly the function  
                deleteAppointmentById(appointmentId);
            });

        });

        // Delete expense
        Array.from( 
            document.getElementsByClassName("expenseIds") 
        ).forEach( element => {

            var expenseId = element.innerText;

            btnDelete = document.getElementById('btnDeleteExpense' + expenseId);
          
            btnDelete.addEventListener('click', function(){
                // we need the anonymous function otherwise it call immediatly the function  
                deleteExpenseById(expenseId);
            });

        });

        // Button for delete the appointment
        function deleteAppointmentById(id){

            // from common-top-page fragment
            var url = document.getElementById('urlDeleteAppointment').getAttribute('href') + '?id=' + id;
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
                    alert("Failed to Delete Appointment");
                }
            });
        }

        // Button for delete the expense
        function deleteExpenseById(id){

            // from common-top-page fragment
            var url = document.getElementById('urlDeleteExpense').getAttribute('href') + '?id=' + id;
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
                    alert("Failed to Delete Expense");
                }
            });
        }

    });

 } )();