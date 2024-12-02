/* CUSTOMERS */
/*
INSERT INTO customer (tax_id_code, name, surname, birth_city, birth_city_province, birth_date, residence_street, house_number, residence_city, residence_city_cap, residence_province, phone_number, phone_number_2, e_mail )
VALUES ('tax_id_code', 'name', 'surname', 'birth_city', 'birth_city_province', 'birth_date', 'residence_street', 'house_number','residence_city', 'residence_city_cap', 'residence_province', 'phone_number', 'phone_number_2', 'e_mail');
*/

/* PROFESSIONAL ROLE */
/*
INSERT INTO professional_role (name, description) 
VALUES ('name','description');
*/

/* EMPLOYEES */
/* 
INSERT INTO employee(name, surname, title, birth_date, phone_number, phone_number_2, e_mail )
VALUES ('name', 'surname', 'title', 'birth_date', 'phone_number', 'phone_number_2', 'e_mail' );
*/

/* HAS PROFESSIONAL ROLE */
/*
INSERT INTO has_professional_role( id_employee, id_professional_role) 
VALUES ( 'id_employee', 'id_professional_role');
*/

/* TREATMENT */
/*
INSERT INTO treatment (name, description, cost )
VALUES ('name', 'description', 'cost');
*/

/* APPOINTMENT */
/* 
INSERT INTO appointment(date, time, id_customer, id_doctor, id_treatment, is_done, bill_number, note)
VALUES ('date', 'time', 'id_customer', 'id_doctor', 'id_treatment', 'is_done', 'bill_number', 'note');
*/

/* CUSTOMERS */
INSERT INTO customer (tax_id_code, name, surname, birth_city, birth_city_province, birth_date, residence_street, house_number, residence_city, residence_city_cap, residence_province, phone_number, phone_number_2, e_mail )
VALUES ('1AAAAAAAAAAAAAAA', 'Mario', 'Rossi', 'Cosmo', 'RM', '1990-10-10', 'Leonardo Da Vinci', '2', 'Cosenza', '09091', 'CS', '1234567890', '2345678901', 'mariorossi@email.it' );

INSERT INTO customer (tax_id_code, name, surname, birth_city, birth_city_province, birth_date, residence_street, house_number, residence_city, residence_city_cap, residence_province, phone_number, phone_number_2, e_mail )
VALUES ('1BBBBBBBBBBBBBBB', 'Giancarlo', 'Verdi', 'Roma', 'RM', '1980-10-10', 'Donatello', '11', 'Cosenza', '09088', 'CS', '9888776688', '2132762354', 'giancarloversi@email.it' );

INSERT INTO customer (tax_id_code, name, surname, birth_city, birth_city_province, birth_date, residence_street, house_number, residence_city, residence_city_cap, residence_province, phone_number, phone_number_2, e_mail )
VALUES ('1AAAAACCCAAAAAAA', 'Giovanni', 'Bianchi', 'Viterbo', 'RM', '1980-10-10', 'Michelangelo', '2', 'Cosenza', '09091', 'CS', '1234500000', '2349998901', 'giovannib@email.it' );

INSERT INTO customer (tax_id_code, name, surname, birth_city, birth_city_province, birth_date, residence_street, house_number, residence_city, residence_city_cap, residence_province, phone_number, phone_number_2, e_mail )
VALUES ('1BBBBBBBBBBDDDDD', 'Giusy', 'Verdi', 'Roma', 'RM', '1980-10-10', 'Donatello', '20', 'Cosenza', '11088', 'CS', '9888776111', '2132762111', 'giusy@email.it' );

INSERT INTO customer (tax_id_code, name, surname, birth_city, birth_city_province, birth_date, residence_street, house_number, residence_city, residence_city_cap, residence_province, phone_number, phone_number_2, e_mail )
VALUES ('1AAAAAAAAARRRRRE', 'Billy', 'Levi', 'Napoli', 'NA', '1990-10-10', 'Garibaldi', '5', 'Modena', '09091', 'MO', '1111167890', '2222228901', 'billy@email.it' );

INSERT INTO customer (tax_id_code, name, surname, birth_city, birth_city_province, birth_date, residence_street, house_number, residence_city, residence_city_cap, residence_province, phone_number, phone_number_2, e_mail )
VALUES ('1BBBRRRFBBBBBBBB', 'Leo', 'Biaggi', 'Roma', 'RM', '1990-10-10', 'Cavour', '11', 'Cosenza', '09088', 'CS', '9888776688', '2132762354', 'giro@email.it' );

INSERT INTO customer (tax_id_code, name, surname, birth_city, birth_city_province, birth_date, residence_street, house_number, residence_city, residence_city_cap, residence_province, phone_number, phone_number_2, e_mail )
VALUES ('1AKKKAAGAAAAAAAA', 'Milo', 'Isi', 'Cosmo', 'RM', '1994-7-10', 'Vittorio Emanuele', '2', 'Cosenza', '09091', 'CS', '1234567890', '2345678901', 'milo@email.it' );

INSERT INTO customer (tax_id_code, name, surname, birth_city, birth_city_province, birth_date, residence_street, house_number, residence_city, residence_city_cap, residence_province, phone_number, phone_number_2, e_mail )
VALUES ('12222BBHBBBBBBBB', 'Ciro', 'Adi', 'Roma', 'RM', '1989-7-10', 'Donatello', '11', 'Cosenza', '09088', 'CS', '9888776688', '2132762354', 'ciro@email.it' );


/* PROFESSIONAL ROLE */
INSERT INTO professional_role (name, description) 
VALUES ('Direttore Sanitario','Il capo supremo dello studio');

INSERT INTO professional_role (name, description) 
VALUES ('Medico chirurgo odontoiatra','Odontoiatra plus');

INSERT INTO professional_role (name, description) 
VALUES ('Amministratore unico','Comanda tutto lui');

INSERT INTO professional_role (name, description) 
VALUES ('Igienista dentale','igienizza');

INSERT INTO professional_role (name, description) 
VALUES ('Assistente alla poltrona','Gestisce le prenotazioni e i clienti');

INSERT INTO professional_role (name, description) 
VALUES ('Odontotecnico','Costruisce le dentiere');

INSERT INTO professional_role (name, description) 
VALUES ('Odontoiatra','Si occupa di interno cavo orale');


/* EMPLOYEES */
INSERT INTO employee(name, surname, title, birth_date, phone_number, phone_number_2, e_mail )
VALUES ('Gianni', 'Marca', 'Dott.', '1979-01-01', '1212122334', '', 'giannimarca@email.com' );

INSERT INTO employee(name, surname, title, birth_date, phone_number, phone_number_2, e_mail )
VALUES ('Eddy', 'Vetri', 'Sig.', '1979-02-10', '1254122334', '', 'eddyvetri@email.com' );

INSERT INTO employee(name, surname, title, birth_date, phone_number, phone_number_2, e_mail )
VALUES ('Mila', 'Catogno', 'Sig.na', '1989-02-10', '1254124314', '', 'mila@email.com' );

INSERT INTO employee(name, surname, title, birth_date, phone_number, phone_number_2, e_mail )
VALUES ('Giusy', 'Frocca', 'Dott.ssa', '1982-02-10', '1254120301', '', 'giusyfrocca@email.com' );

INSERT INTO employee(name, surname, title, birth_date, phone_number, phone_number_2, e_mail )
VALUES ('Matteo', 'Gianni', 'Dott.', '1979-03-12', '1222220301', '4445556634', 'mattih@email.com' );


/* HAS PROFESSIONAL ROLE */
INSERT INTO has_professional_role( id_employee, id_professional_role) 
VALUES ( '1', '1');

INSERT INTO has_professional_role( id_employee, id_professional_role) 
VALUES ( '1', '2');

INSERT INTO has_professional_role( id_employee, id_professional_role) 
VALUES ( '2', '3');

INSERT INTO has_professional_role( id_employee, id_professional_role) 
VALUES ( '3', '3');

INSERT INTO has_professional_role( id_employee, id_professional_role) 
VALUES ( '3', '1');

INSERT INTO has_professional_role( id_employee, id_professional_role) 
VALUES ( '3', '4');

INSERT INTO has_professional_role( id_employee, id_professional_role) 
VALUES ( '4', '5');

INSERT INTO has_professional_role( id_employee, id_professional_role) 
VALUES ( '5', '6');

INSERT INTO has_professional_role( id_employee, id_professional_role) 
VALUES ( '5', '7');



/* TREATMENT */

INSERT INTO treatment (name, description, cost )
VALUES ('Pulizia dei denti', 'Toglie il tartaro', '50');


/* APPOINTMENT */ 
INSERT INTO appointment(date, time, id_customer, id_doctor, id_treatment, is_done, bill_number, note)
VALUES ('2023-9-30', '18:00', '1', '1', '1', '0', '', '');


INSERT INTO appointment(date, time, id_customer, id_doctor, id_treatment, is_done, bill_number, note)
VALUES ('2023-9-30', '12:00', '2', '1', '1', '1', '', '');

INSERT INTO appointment(date, time, id_customer, id_doctor, id_treatment, is_done, bill_number, note)
VALUES ('2023-10-26', '12:00', '3', '1', '1', '0', '', '');

INSERT INTO appointment(date, time, id_customer, id_doctor, id_treatment, is_done, bill_number, note)
VALUES ('2023-10-20', '18:00', '4', '1', '1', '0', '', '');


INSERT INTO appointment(date, time, id_customer, id_doctor, id_treatment, is_done, bill_number, note)
VALUES ('2023-10-25', '12:00', '1', '1', '1', '1', '', '');

INSERT INTO appointment(date, time, id_customer, id_doctor, id_treatment, is_done, bill_number, note)
VALUES ('2023-10-22', '18:00', '2', '1', '1', '0', '', '');


INSERT INTO appointment(date, time, id_customer, id_doctor, id_treatment, is_done, bill_number, note)
VALUES ('2023-10-30', '12:00', '2', '1', '1', '1', '', '');

INSERT INTO appointment(date, time, id_customer, id_doctor, id_treatment, is_done, bill_number, note)
VALUES ('2023-11-22', '12:00', '5', '1', '1', '0', '', '');

INSERT INTO appointment(date, time, id_customer, id_doctor, id_treatment, is_done, bill_number, note)
VALUES ('2023-12-20', '18:00', '6', '1', '1', '0', '', '');


INSERT INTO appointment(date, time, id_customer, id_doctor, id_treatment, is_done, bill_number, note)
VALUES ('2023-12-25', '12:00', '7', '1', '1', '1', '', '');




