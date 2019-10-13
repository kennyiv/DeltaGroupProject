-- Note: the usage of local variables and limit one will create bugs later on with duplicate pet names / clients, will need to rework Database calls / GUI
-- Example data to populate tables
	INSERT INTO client (client_firstname, client_lastname) VALUES ("Firstname", "Lastname");
	INSERT INTO pet (pet_name, pet_gender, pet_weight) VALUES ("PetName", "M", 42);
	-- Assign PetName to client Lastname
		-- Could not determine how to add cliend_id and pet_id with subquery, so used temp local variables
		SELECT client_id INTO @clientID FROM client WHERE client.client_lastname = "Lastname" LIMIT 1;
		SELECT pet_id INTO @petID FROM pet WHERE pet.pet_name = "PetName" LIMIT 1;
	INSERT INTO clientPet (client_id, pet_id) VALUES (@clientID, @petID);
	-- Create medication
	INSERT INTO medication (medication_name, medication_info) VALUES ("Chloramphenicol","Broad-spectrum antibotic for bacterial infections, and some single-cell pathogens.");
	-- Assign medication to pet
		SELECT medication_id INTO @medicationID FROM medication WHERE medication.medication_name = "Chloramphenicol" LIMIT 1; 
		SELECT pet_id INTO @petID FROM pet WHERE pet.pet_name = "PetName" LIMIT 1;
	INSERT INTO petmedication (pet_id, medication_id) VALUES (@petID, @medicationID);
	-- Create an appointment
		SELECT pet_id INTO @petID FROM pet WHERE pet.pet_name = "PetName" LIMIT 1;
		SELECT client_id INTO @clientID FROM client WHERE client.client_lastname = "Lastname" LIMIT 1;
	INSERT INTO appointment (pet_id, client_id, doctor_name, appointment_date, appointment_time, appointment_duration) VALUES (@petID, @clientID, "Dr. Pepper", "12/12/12", TIME("12:12:12"), 64);
	
-- Search calls
	-- Search for owner's lastname, pet name, gender, weight based on owner's last name
	SELECT client.client_lastname, pet.pet_name, pet.pet_gender, pet.pet_weight FROM pet INNER JOIN clientPet ON clientPet.pet_id = pet.pet_id INNER JOIN client ON clientPet.client_id = client.client_id WHERE client.client_lastname = "Lastname";
	-- Search for owner's lastname, pet name, gender, weight based on pet's name
	SELECT client.client_lastname, pet.pet_name, pet.pet_gender, pet.pet_weight FROM pet INNER JOIN clientPet ON clientPet.pet_id = pet.pet_id INNER JOIN client ON clientPet.client_id = client.client_id WHERE pet.pet_name = "PetName";
	-- Search for owner's lastname, pet name, gender, weight based on Owner and pet name
	SELECT client.client_lastname, pet.pet_name, pet.pet_gender, pet.pet_weight FROM pet INNER JOIN clientPet ON clientPet.pet_id = pet.pet_id INNER JOIN client ON clientPet.client_id = client.client_id WHERE pet.pet_name = "PetName" AND client.client_lastname = "Lastname";

-- Schedule an appointment
	-- Appointment_date may be deprciated in a future release for appointment_time which contains date+time
	SELECT client_id INTO @clientID FROM client WHERE client.client_lastname = "Lastname" LIMIT 1;
	SELECT pet_id INTO @petID FROM pet WHERE pet.pet_name = "PetName" LIMIT 1;
	-- Create an appointment for an existing pet and client
	INSERT INTO appointment (pet_id, client_id, doctor_name, appointment_date, appointment_time, appointment_duration) VALUES (@clientID, @petID, "DoctorName", "12/12/2012", "12:12", 30);
	
-- Client Card
	-- Select all medication name, info for a pet
	SELECT medication_name, medication_info FROM medication INNER JOIN petmedication ON medication.medication_id = petmedication.medication_id INNER JOIN pet ON pet.pet_id = petmedication.pet_id WHERE pet.pet_name = "PetName";
	-- Select appointments for a pet
	SELECT appointment_date, appointment_time, appointment_duration FROM appointment INNER JOIN pet ON pet.pet_id = appointment.pet_id WHERE pet.pet_name = "PetName";
	-- Select appointments for a client
	SELECT appointment_date, appointment_time, appointment_duration FROM appointment INNER JOIN client ON client.client_id = appointment.client_id WHERE client.client_lastname = "Lastname";