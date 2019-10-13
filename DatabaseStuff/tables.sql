-- Table for pet owner information
CREATE TABLE client (
	client_id INT UNSIGNED NOT NULL AUTO_INCREMENT,
	client_lastname VARCHAR(32) NOT NULL,
	client_firstname VARCHAR(32) NOT NULL,
	client_added TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	client_modified TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	CONSTRAINT client_pk PRIMARY KEY (client_id)
);

-- Table for pet information
CREATE TABLE pet (
	pet_id INT UNSIGNED NOT NULL AUTO_INCREMENT,
	pet_name VARCHAR(32) NOT NULL,
	pet_gender CHAR(1),
	pet_weight FLOAT,
	pet_added TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	pet_modified TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	CONSTRAINT pet_pk PRIMARY KEY (pet_id)
);

-- A cross reference table for clients to pets
CREATE TABLE clientPet (
	client_id INT UNSIGNED NOT NULL,
	pet_id INT UNSIGNED NOT NULL,
	CONSTRAINT clientPet_client_fk FOREIGN KEY (client_id) REFERENCES client (client_id) ON DELETE CASCADE ON UPDATE CASCADE,
	CONSTRAINT clientPet_pet_fk FOREIGN KEY (pet_id) REFERENCES pet (pet_id) ON DELETE CASCADE ON UPDATE CASCADE
);

-- Medication table
CREATE TABLE medication (
	medication_id INT UNSIGNED NOT NULL AUTO_INCREMENT,
	medication_name VARCHAR(64) NOT NULL,
	medication_info VARCHAR(512) NOT NULL,
	CONSTRAINT medication_pk PRIMARY KEY (medication_id)
);

-- Cross reference table for medicaitons to pets
CREATE TABLE petMedication (
	pet_id INT UNSIGNED NOT NULL,
	medication_id INT UNSIGNED NOT NULL,
	petMedication_added TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	petMedication_modified TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	CONSTRAINT petMedication_pet_fk FOREIGN KEY (pet_id) REFERENCES pet (pet_id) ON DELETE CASCADE ON UPDATE CASCADE,
	CONSTRAINT petMedication_medication_fk FOREIGN KEY (medication_id) REFERENCES medication (medication_id) ON DELETE CASCADE ON UPDATE CASCADE
);

-- Appointment table; duration is in minutes
CREATE TABLE appointment (
	appointment_id INT UNSIGNED NOT NULL AUTO_INCREMENT,
	pet_id INT UNSIGNED NOT NULL,
	client_id INT UNSIGNED NOT NULL,
	doctor_name VARCHAR(32),
	appointment_date DATE NOT NULL,
	appointment_time TIMESTAMP NOT NULL,
	appointment_duration SMALLINT UNSIGNED NOT NULL,
	appointment_added TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	appointment_modified TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	CONSTRAINT appointment_pk PRIMARY KEY (appointment_id),
	CONSTRAINT appointment_pet_id_fk FOREIGN KEY (pet_id) REFERENCES pet (pet_id) ON DELETE CASCADE ON UPDATE CASCADE,
	CONSTRAINT appointment_client_id_fk FOREIGN KEY (client_id) REFERENCES client (client_id) ON DELETE CASCADE ON UPDATE CASCADE
);