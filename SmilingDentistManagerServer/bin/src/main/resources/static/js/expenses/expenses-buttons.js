//  IIFEs Immediately Invoked Function Expressions
( function() {

    document.addEventListener( 'DOMContentLoaded' , function (){

        // Download Expenses Csv ------------------------------------------------------------------------
        const btnDownloadIncomeOutcomeCsv = document.getElementById('btnDownloadIncomeOutcomeCsv');

        // got current url and add to it '?csv=true' 
        var reqUrl = window.location.href + '?csv=true';

        btnDownloadIncomeOutcomeCsv.addEventListener('click', function(){
            window.location.href = reqUrl;
        });
        // ----------------------------------------------------------------------------------------------

        // Delete appoitnemts 
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