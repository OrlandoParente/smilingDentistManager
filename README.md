# Smiling dentist manager
*Smiling dentist manager* it's an open source management for dentist studios. 
It's born by listening the needs of [Dental Smile](https://linkedin.com/company/dental-smile-srls) studio, and how they needs a mangement software.
I thinked to myself, I have the opportunity to do a great Java Open Source software that solve a real-life problem, 
because I will have a continuos feedback (whatelse the software needs to have, bugs, etc ...) and at least one company will use my software (that's great).  
So I tell them, I can do it! But it will be Open Source, and here we are!

## Description
This software is composed by two clients and one server:  
- The [first client](https://github.com/OrlandoParente/smilingDentistManager/tree/main/smilingDentinstManagerClient) is a desktop application written with Swing. In future probably I will re-writing it with Java FX.  
  To date I *do not* raccomend to use this client, cause it miss some functionalities.
- The [second client](https://github.com/OrlandoParente/smilingDentistManager/tree/main/SmilingDentistManagerServer/src/main/resources) is directly integrated in the server and it's a web application gui written with bootstrap.
- The [server](https://github.com/OrlandoParente/smilingDentistManager/tree/main/SmilingDentistManagerServer) is a MVC application written with SpringBoot. It has:   
  - [API Controllers](https://github.com/OrlandoParente/smilingDentistManager/tree/main/SmilingDentistManagerServer/src/main/java/sdms/controller/api), for a generic use of the server. You can build your own client and use this as a server throught this APIs.
  - [Web Controllers](https://github.com/OrlandoParente/smilingDentistManager/tree/main/SmilingDentistManagerServer/src/main/java/sdms/controller/web), that are controllers dedicated to the client directly integrated with it
 
## Why you should use my application
If you want to use this application you should keep in mind, as written in the licence, I have no liability for any damage derivated by the use of this software.  

On other hand, in my option, there are a couple of reason for use this software:
- *It's Open Source*, that's mean:
   - You, or anyelse for you, can check the code for be sure there is nothing weird;
   - You, or anyelse for you can edit it for adapt to your own needs (add functionalities, change colors, add languages, etc ...)
- *Choose your cost*, namely:
   - Even though you can keep smilingDentistManager for free, you need to upload it somewhere for make it works properly (I mean, it's a server after all). But for sure, you can choose the best solution for you both in terms of *costs* and *resources allocated*

 ## Quick start
( I will cover this section soon )

## Usage
After the installation of the server (and the database) you can use the follow APIs  
with this structure: **[ServerURL]/[API]/[anyParameterOrPathVariable]** (*where* **{variables}** are PathVariables)
### Appointment APIs
```java 
/getAppointments 
```  
```java 
/getAppointmentsByCustomerId/{idCustomer} 
```  
```java 
/getAppointmentsByDoctorId/{idDoctor} 
```  
```java 
/postAppointment?date=DATE&time=TIME&idCustomer=ID_CUSTOMER // facoltative params={idDoctor, idTreatment, isDone, invoiceNumber, payment, notes)
```
<!-- 
```java 
/putSetAppointmentDoneById?id=ID
```  
```java 
/putUnsetAppointmentDoneById?id=ID
```
-->
```java 
/putAppointment?id=ID // facoltative params={ date, time, idCustomer, idDoctor, idTreatment, isDone, invoiceNumber, payment, notes }
```
<!-- 
```java 
/putAppointmentBillNumberById?id=ID&billNumber=BILL_NUMBER
```
```java 
/putAppointmentNoteById?id=ID&note=NOTE
```
```java 
/putAppointmentTreatmentById?id=ID&idTreatment=ID_TREAT
```
-->
```java 
/deleteAppointment?id=ID
```
### Customer APIs
 
```java 
// I need this for keep the compatibility with the Swing client
/getMaxIdCustomer   
```
```java 
/getCustomers
```
```java 
/getCustomerById/{id}
```
```java 
/getCustomersByPartialKeyWordOverAllFields/{keyWord}
```
```java 
/postCustomer?name=NAME&surname=SURNAME // facoltative parms= { eMail, taxIdCode, permission, birthCity,  birthCityProvince, birthDate, 
								                        //                      residenceStreet, houseNumber, residenceCity, residenceCityCap, residenceProvince, 
								                        //                      phoneNumber, phoneNumber2   }
```
```java 
/putCustomer?id=ID                      // facoltative parms= { name, surname, eMail, taxIdCode, permission, birthCity,  birthCityProvince, birthDate, 
								                        //                      residenceStreet, houseNumber, residenceCity, residenceCityCap, residenceProvince, 
								                        //                      phoneNumber, phoneNumber2   }
```
```java 
/deleteCustomer?id=ID
```
### Dental material APIs

```java 
/getDentalMaterials
```
```java 
/getDentalMaterialById/{id}
```
```java 
/postDentalMaterial?name=NAME   // facoltative params= { quantity, description, cost }
```
```java 
/putDentalMaterial?id=ID    // facoltative params= { name, quantity, description, cost }
```

```java 
// Note.: this is a PATCH call
/decreaseDentalMaterialQuantity?id=ID&quantity=QUANTITY    
```
```java 
// Note.: this is a PATCH call
/increaseDentalMaterialQuantity?id=ID&quantity=QUANTITY   
```
```java 
// Note.: this is a PATCH call
/increaseDentalMaterialQuantity?id=ID&quantity=QUANTITY&amount=AMOUNT&date=DATE // facoltative param= { tag }   
```
```java 
/deleteDentalMaterial?id=ID
```
### Employee APIs
```java 
// I need this for keep the compatibility with the Swing client
/getMaxIdEmployee
```
```java 
/getEmployees
```
```java 
/getEmployeesByName/{name}
```
```java 
/getEmployeesBySurname/{surname}
```  
```java 
/getEmployeesByProfessionalRoleName/{professionalRoleName}
```
```java 
/getEmployeesByPartialKeyWordOverAllFields/{keyWord}
```  
```java 
/getEmployeeById/{id}
```  
```java 
/postEmployee?name=NAME&surname=SURNAME&eMail=E_MAIL  // facoltative params={ title, birthDate, phoneNumber, phoneNumber2, salary, permission, startWorkDate }
```  
```java 
/putEmployee?id=ID  // facoltative params={ title, name, surname, birthDate, phoneNumber, phoneNumber2, salary, permission, startWorkDate }
```
```java 
// Note: this is a PATCH call
/employeeChangePassword?id=ID&currentPassword=CURRENT_PSW&newPassword=NEW_PSW
```
```java 
/deleteEmployee?id=ID
```
### Expense APIs
```java 
/getExpenseById/{id}
```  
```java 
/getExpenses
```
```java 
/postExpense?date=DATE&amount=AMOUNT  // facoltative params={ description, tag, idCustomer, idEmployee, idDentalMaterial, dentalMaterialQuantityToAdd }
```
<!-- MAYBE I WILL DELETE THIS  
```java 
@PostMapping("/postCustomerRefund")
	public ResponseEntity<?> postCustomerRefund( @RequestParam("idCustomer") long idCustomer,
			@RequestParam("date") String date, @RequestParam("description") String description,
			@RequestParam("amount") double amount, @RequestParam("tag") String tag) {
```
-->
<!-- MAYBE I WILL DELETE THIS  
```java 
@PostMapping("/postDentalMaterialPurchase")
	public ResponseEntity<?> postDentalMaterialPurchase( @RequestParam("idDentalMaterial") long idDentalMaterial,
			@RequestParam("date") String date, @RequestParam("description") String description,
			@RequestParam("amount") double amount, @RequestParam("tag") String tag) {
	
```
-->
<!-- MAYBE I WILL DELETE THIS  
```java 
@PostMapping("/postSalaryPayment")
	public ResponseEntity<?> postSalaryPayment( @RequestParam("idEmployee") long idEmployee,
			@RequestParam("date") String date, @RequestParam("description") String description,
			@RequestParam("amount") double amount, @RequestParam("tag") String tag) {
		
```
-->


```java 
/putExpense?id=ID // facoltative params= { date,  description, tag, amount }
```  
<!-- MAYBE I WILL DELETE THIS
```java 
// Update the refund to a customer
	@PutMapping("/putCustomerRefund")
	public ResponseEntity<?> putCustomerRefund( @RequestParam("id") long id,
			@RequestParam("idCustomer") long idCustomer, @RequestParam("date") String date, 
			@RequestParam("description") String description, @RequestParam("amount") double amount, 
			@RequestParam("tag") String tag) {
```
-->
<!-- MAYBE I WILL DELETE THIS
```java 
// Update the expense regards purchase of dental materials
	@PutMapping("/putDentalMaterialPurchase")
	public ResponseEntity<?> putDentalMaterialPurchase( @RequestParam("id") long id,
			@RequestParam("idDentalMaterial") long idDentalMaterial, @RequestParam("date") String date, 
			@RequestParam("description") String description, @RequestParam("amount") double amount, 
			@RequestParam("tag") String tag) {
```
-->
<!-- MAYBE I WILL DELETE THIS
```java 
	// Insert the salary payment of an employee
	@PutMapping("/putSalaryPayment")
	public ResponseEntity<?> putSalaryPayment( @RequestParam("id") long id,
			@RequestParam("idEmployee") long idEmployee, @RequestParam("date") String date, 
			@RequestParam("description") String description, @RequestParam("amount") double amount, 
			@RequestParam("tag") String tag) {
```
-->

```java 
/deleteExpense?id=ID
```
### Has medical history APIs
Namely the controller that link one Customer to n Medical Histories  

```java 
/postHasMedicalHistory?idCustomer=ID_CUSTOMER&idMedicalHistory=ID_MEDICAL_HISTORY   // facoltative param = { notes }
```
```java 
/putHasMedicalHistory?id=ID   // facoltative params = { idCustomer, idMedicalHistory, notes }
```  
```java 
/deleteHasMedicalHistory?id=ID
```
```java 
/deleteHasMedicalHistory?idCustomer=ID_CUSTOMER&idMedicalHistory=ID_MEDICAL_HISTORY
```  
### Has professional role APIs
Namely the controller that link a Employee which his (one or more) Professional Role  

```java 
/postLinkEmployeeToProfessionalRole?idEmployee=ID_EMPLOYEE&idProfessionalRole=ID_PROFESSIONAL_ROLE
```  
```java 
/deleteLinkEmployeeWithProfessionalRole?idEmployee=ID_EMPLOYEE&idProfessionalRole=ID_PROFESSIONAL_ROLE
```  
### Login API
```java 
// Note: this is a POST request 
/api/login?username=USERNAME&password=PASSWORD
```  
### Medical history APIs

```java 
/getMedicalsHistoryByCustomer/{idCustomer}
```  
```java 
/getMedicalsHistoryByType/{type}
```
```java 
/getMedicalsHistoryByCategory/{category}
```
```java 
/getMedicalsHistoryByTypeAndCategory/{type}/{category}
```
```java 
/getMedicalHistoryById/{id}
```
```java 
/getMedicalHistoryTypes
```
```java 
/getMedicalHistoryCategories
```
```java 
/postMedicalHistory?name=NAME // facotative params= { type, category, description }
```
```java 
/putMedicalHistory?id=ID   // facotative params= { name, type, category, description }
```
```java 
/deleteMedicalHistoryById?id=ID
```

### Professional role API

```java 
/getProfessionalRoles
```  
```java 
/getProfessionalRolesAssociatedToIdEmployee/{idEmployee}
```  
```java 
/postProfessionalRole?name=NAME   // facoltative param= { description }
```
```java 
/putProfessionalRole?id=ID    // facoltative params= {name, description}
```
```java 
/deleteProfessionalRole?id=ID 
```

### Treatment APIs

```java 
/getTreatmentById/{id}
```  
```java 
/getTreatments
```  
```java 
/getTreatmentsById/{idCustomer}
```  
```java 
/getTreatmentsByBillNumber/{billNumber}
```
```java 
/postTreatment?name=NAME&cost=COST  // facoltative param= { description }
```  
```java 
/putTreatment?id=ID&name=NAME&description=DESCRIPTION&cost=COST  // I will set name, description and cost as facoltative soon
```  
```java 
/deleteTreatment?id=ID
```  

### Work period

```java 
/getWorkPeriodById/{id}
```
```java 
/getWorkPeriodsByEmployeeId/{employeeId}
```
```java 
/getWorkPeriodsByEmployeeEMail/{eMail}
```
```java 
/postWorkPeriod?idEmployee=ID_EMPLOYEE&startDate=START_WORK_DATE    // facoltative params = { endDate, workingAgreement, notes }
```
```java 
/putWorkPeriod?id=ID    // facoltative params= { idEmployee, startDate, endDate, workingAgreement, notes } 
```
```java 
/deleteWorkPeriod?id=ID
```