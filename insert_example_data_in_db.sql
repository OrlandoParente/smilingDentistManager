/* CUSTOMERS */
/*
INSERT INTO customer (tax_id_code, name, surname, birth_city, birth_city_province, birth_date, residence_street, residence_city, residence_province, phone_number, phone_number_2, e_mail )
VALUES ('tax_id_code', 'name', 'surname', 'birth_city', 'birth_city_province', 'birth_date', 'residence_street', 'residence_city', 'residence_province', 'phone_number', 'phone_number_2', 'e_mail');
*/

/* EMPLOYEES */
/* INSERT INTO employee(name, surname, title, birth_date, phone_number, phone_number_2, e_mail )
VALUES ('name', 'surname', 'title', 'birth_date', 'phone_number', 'phone_number_2', 'e_mail' );*/

/* TREATMENT */
/*
INSERT INTO treatment (name, description, cost )
VALUES ('name', 'description', 'cost');
*/

/* CUSTOMERS */
INSERT INTO customer (tax_id_code, name, surname, birth_city, birth_city_province, birth_date, residence_street, residence_city, residence_province, phone_number, phone_number_2, e_mail )
VALUES ('1AAAAAAAAAAAAAAA', 'Mario', 'Rossi', 'Cosmo', 'RM', '1990-10-10', 'Leonardo Da Vinci', 'Cosenza', 'CS', '1234567890', '2345678901', 'mariorossi@email.it' );


/* EMPLOYEES */
INSERT INTO employee(name, surname, title, birth_date, phone_number, phone_number_2, e_mail )
VALUES ('Gianni', 'Marca', 'Dott.', '1979-01-01', '1212122334', null, 'giannimarca@email.com' );

INSERT INTO employee(name, surname, title, birth_date, phone_number, phone_number_2, e_mail )
VALUES ('Eddy', 'Vetri', 'Sig.', '1979-02-10', '1254122334', null, 'eddyvetri@email.com' );

INSERT INTO employee(name, surname, title, birth_date, phone_number, phone_number_2, e_mail )
VALUES ('Mila', 'Catogno', 'Sig.na', '1989-02-10', '1254124314', null, 'mila@email.com' );

INSERT INTO employee(name, surname, title, birth_date, phone_number, phone_number_2, e_mail )
VALUES ('Giusy', 'Frocca', 'Dott.ssa', '1982-02-10', '1254120301', null, 'giusyfrocca@email.com' );

/* TREATMENT */

INSERT INTO treatment (name, description, cost )
VALUES ('Pulizia dei denti', 'Toglie il tartaro', '50');
