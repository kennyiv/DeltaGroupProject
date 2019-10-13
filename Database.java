/*
 * File: Database.java
 * Author: Neil Kohan, Stephanie Brinegar, Kenneth Harris, Jeremy Lantz
 * Course: CMSC 495
 * Project: vetclinic
 * Date: 09/22/2019
 * Platform: Windows 10
 * Compiler: Netbeans 82
 * Purpose: Retrives data from database files, Loads records to database files
 * 
 * Requirements: Add maraidb-java-client-2.5 to libraries, also requires a running mariadb instance
 */
package vetclinic;

import java.util.Date;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
        
public class Database {
    
    // Connection info for database
    private String db = "test";
    private String user = "root";
    private String password = "";
    
    private Connection conn = null;
    private Statement stmt = null;
    
    private String[][] shotList;
    private String[][] apptList;
    private String[][] medList;
    
    Database(){
        try {
            conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/" + db + "?user=" + user + "&password=" + password);
            stmt = conn.createStatement();
        } catch (Exception e) {
            System.out.print("Could not connect to DB: " + e);
        }
    }
    
    // Returns 1 on sucess or 0 on failure
    public Integer searchPetName(String animalName, String lastName, String operator){
        try {
            String query;
            if(operator.equals("and")) {
                query = "SELECT client.client_lastname, pet.pet_name, pet.pet_gender, pet.pet_weight FROM pet INNER JOIN clientPet ON clientPet.pet_id = pet.pet_id INNER JOIN client ON clientPet.client_id = client.client_id WHERE pet.pet_name = \"" + animalName + "\" AND client.client_lastname = \"" + lastName +"\";";
            } else if(operator.equals("or")) {
                query = "SELECT client.client_lastname, pet.pet_name, pet.pet_gender, pet.pet_weight FROM pet INNER JOIN clientPet ON clientPet.pet_id = pet.pet_id INNER JOIN client ON clientPet.client_id = client.client_id WHERE pet.pet_name = \"" + animalName + "\" OR client.client_lastname = \"" + lastName +"\";";
            } else {
                // Error
                return 0;
            }
            stmt.executeQuery(query);
            return 1;
        } catch (Exception e) {
            System.out.print("Could not get animalID: " + e);
        }
        return 0;
    }
    
    // Check availability, true if available, false otherwise
    public Boolean checkApptTime(Date checkDate, Date checkTime, Date durationTime){
        try {
            String query = "SELECT appointment_id FROM appointment WHERE appointment_date = '" + checkDate + "';";
            ResultSet rs = stmt.executeQuery(query);
            if(rs.getString("appointment_id") != null) {
                return true;
            } else {
                return false;
            }   
        } catch (Exception e) {
            System.out.print("Could not get animalID: " + e);
        }
        return false;
    }
    
    // Create an appointment, true if successful false otherwise
    public Boolean loadAppointment(Integer animalID, Integer clientID, Date apptDate, Date apptTime, String doctor, Integer duration){
        try {
            String query = "INSERT INTO appointment (pet_id, client_id, doctor_name, appointment_date, appointment_time, appointment_duration) VALUES ('" + animalID + "', '" + clientID + " '" + doctor + "', '" + apptDate + "', '" + apptTime + "', '" + duration + "');";
            ResultSet rs = stmt.executeQuery(query);
            if(rs.getObject(1) == null) {
                return false;
            } else {
                return true;
            }   
        } catch (Exception e) {
            System.out.print("Could not get animalID: " + e);
        }
        return false;
    }
    
    public String getAnimalName(Integer animalID){
        try {
            String query = "SELECT pet_name FROM pet WHERE pet_id = " + animalID + ";";
            ResultSet rs = stmt.executeQuery(query);
            return rs.getString("pet_name");
        } catch (Exception e) {
            System.out.print("Could not get animalID: " + e);
        }
        return "error";
    }
    
    public String getFirstName(Integer animalID){
        try {
            String query = "SELECT client.client_firstname FROM pet INNER JOIN clientPet ON clientPet.pet_id = pet.pet_id INNER JOIN client ON clientPet.client_id = client.client_id WHERE pet.pet_id = \""+ animalID +"\";";
            ResultSet rs = stmt.executeQuery(query);
            return rs.getString("client_firstname");
        } catch (Exception e) {
            System.out.print("Could not get animalID: " + e);
        }
        return "error";
    }
    
    public String getLastName(Integer animalID){
        try {
            String query = "SELECT client.client_lastname FROM pet INNER JOIN clientPet ON clientPet.pet_id = pet.pet_id INNER JOIN client ON clientPet.client_id = client.client_id WHERE pet.pet_id = \""+ animalID +"\";";
            ResultSet rs = stmt.executeQuery(query);
            return rs.getString("client_lastname");
        } catch (Exception e) {
            System.out.print("Could not get animalID: " + e);
        }
        return "error";
    }
    
    public String getGender(Integer animalID){
        try {
            String query = "SELECT pet_gender FROM pet WHERE pet_id = \""+ animalID +"\";";
            ResultSet rs = stmt.executeQuery(query);
            return rs.getString("pet_gender");
        } catch (Exception e) {
            System.out.print("Could not get animalID: " + e);
        }
        return "error";
    }
    
    public String getWeight(Integer animalID){
        try {
            String query = "SELECT pet_weight FROM pet WHERE pet_id = \""+ animalID +"\";";
            ResultSet rs = stmt.executeQuery(query);
            return rs.getString("pet_weight");
        } catch (Exception e) {
            System.out.print("Could not get animalID: " + e);
        }
        return "error";
    }
    
    // Return true on success false otherwise
    public Boolean postAnimal(String petName, String lastName, String firstName,
                              String Gender, String weight){
        try {
            String addClient = "INSERT INTO client (client_firstname, client_lastname) VALUES (\"" + firstName + "\", \"" + lastName + "\");";
            stmt.executeQuery(addClient);
            String addPet = "INSERT INTO pet (pet_name, pet_gender, pet_weight) (\"" + petName + "\", \"" + Gender + "\", \"" + weight +"\");";
            stmt.executeQuery(addPet);
            String addPetToClient = "SELECT client_id INTO @clientID FROM client WHERE client.client_lastname = \"" + lastName + "\" LIMIT 1;\n" 
                                  + "SELECT pet_id INTO @petID FROM pet WHERE pet.pet_name = \"" + petName + "\" LIMIT 1;\n"
                                  + "INSERT INTO clientPet (client_id, pet_id) VALUES (@clientID, @petID);";
            ResultSet rs = stmt.executeQuery(addPetToClient);
            if(rs.getObject(1) == null) {
                return false;
            } else {
                return true;
            }   
        } catch (Exception e) {
            System.out.print("Could not get animalID: " + e);
        }
        return false;
    }
    
    // Return true on success false otherwise
    public Boolean updateAnimal(Integer animalID, String weight){
        try {
            String query = "UPDATE pet SET pet_weight = " + weight + " WHERE pet_id = \""+ animalID +"\";";
            ResultSet rs = stmt.executeQuery(query);
            if(rs.getObject(1) == null) {
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
            System.out.print("Could not get animalID: " + e);
        }
        return false;
    }
    // Add a medication to the database, assign it to that pet, returns all medications for that pet
    public String[][] postMedication(Integer animalID, String medication, String medicationInfo){
        //Initialize the array
        medList = new String[50][2];
        
        try {
            // Add the medication
            String query = "INSERT INTO medication (medication_name, medication_info) VALUES (\""+ medication +"\",\""+ medicationInfo +"\");";
            stmt.executeQuery(query);
            // Assign the medication
            String query2 = "SELECT medication_id INTO @medicationID FROM medication WHERE medication.medication_name = \""+ medication +"\" LIMIT 1;"
                    + "INSERT INTO petmedication (pet_id, medication_id) VALUES (\""+ animalID +"\", @medicationID);";
            stmt.executeQuery(query2);
            // Get medication for that pet
            String query3 = "SELECT medication_name, medication_info FROM medication INNER JOIN petmedication ON medication.medication_id = petmedication.medication_id INNER JOIN pet ON pet.pet_id = petmedication.pet_id WHERE pet.pet_id = \""+ animalID +"\";";
            ResultSet rs = stmt.executeQuery(query3);
            int x = 0;
            int y = 0;
            while(rs.next()) {
                String medName = rs.getString("medication_name");
                String medInfo = rs.getString("medication_info");
                y = 0;
                medList[x][y]= medName;
                y++;
                medList[x][y] = medInfo;
                // now bump the counter and do it again
                x++;
            }
            return medList;
        } catch (Exception e) {
            System.out.print("Could not get animalID: " + e);
        }
        String[][] err = { 
            { "error", "error", "error" }
        };  
        return err;
    }
    
    // Add a shot to the database, assign it to that pet, returns all shots for that pet
    public String[][] postShots(Integer animalID, String shot, String shotInfo){
         //Initialize the array
        shotList = new String[50][2];
        
        try {
            // Add the shot
            String query = "INSERT INTO shot (shot_name, shot_info) VALUES (\""+ shot +"\",\""+ shotInfo +"\");";
            stmt.executeQuery(query);
            // Assign the shot
            String query2 = "SELECT shot_id INTO @shotID FROM shot WHERE shot.shot_name = \""+ shot +"\" LIMIT 1;"
                    + "INSERT INTO petshot (pet_id, shot_id) VALUES (\""+ animalID +"\", @shotID);";
            stmt.executeQuery(query2);
            // Get shots for that pet
            String query3 = "SELECT shot_name, shot_info FROM shot INNER JOIN petshot ON shot.shot_id = petshot.shot_id INNER JOIN pet ON pet.pet_id = petshot.pet_id WHERE pet.pet_id = \""+ animalID +"\";";
            ResultSet rs = stmt.executeQuery(query3);
            int x = 0;
            int y = 0;
            while(rs.next()) {
                String shotName = rs.getString("shot_name");
                String shotInfos = rs.getString("shot_info");
                y = 0;
                shotList[x][y]= shotName;
                y++;
                shotList[x][y] = shotInfos;
                // now bump the counter and do it again
                x++;
            }
            return shotList;
        } catch (Exception e) {
            System.out.print("Could not get animalID: " + e);
        }
        String[][] err = { 
            { "error", "error" }
        };  
        return err;
    }
    
    // Returns all shots for a pet
    public String[][] getShots(Integer animalID){
        //Initialize the array
        shotList = new String[50][2];
        
        try {
            String query = "SELECT shot.shot_name, petShot.petShot_added FROM shot INNER JOIN petshot ON shot.shot_id = petshot.shot_id INNER JOIN pet ON pet.pet_id = petshot.pet_id WHERE pet.pet_id = \""+ animalID +"\";";
            ResultSet rs = stmt.executeQuery(query);
            int x = 0;
            int y = 0;
            while(rs.next()) {
                String shotName = rs.getString("shot_name");
                String shotDate = rs.getString("petShot_added");
                y = 0;
                shotList[x][y]= shotName;
                y++;
                shotList[x][y] = shotDate;
                // now bump the counter and do it again
                x++;
            }
            return shotList;
        } catch (Exception e) {
            System.out.print("Could not get animalID: " + e);
        }
        String[][] err = { 
            { "error", "error" }
        };  
        return err;
        
    }
    
    // Returns all meds for a pet
    public String[][] getMeds(Integer animalID){
         //Initialize the array
        medList = new String[50][2];
        
        try {
            String query = "SELECT medication.medication_name, petMedication.petMedication_added FROM medication INNER JOIN petmedication ON medication.medication_id = petmedication.medication_id INNER JOIN pet ON pet.pet_id = petmedication.pet_id WHERE pet.pet_id = \""+ animalID +"\";";
            ResultSet rs = stmt.executeQuery(query);
            int x = 0;
            int y = 0;
            while(rs.next()) {
                String medicationName = rs.getString("medication_name");
                String date = rs.getString("petmedication_added");
                y = 0;
                medList[x][y]= medicationName;
                y++;
                medList[x][y] = date;
                // now bump the counter and do it again
                x++;
            }
            return medList;
        } catch (Exception e) {
            System.out.print("Could not get animalID: " + e);
        }
        String[][] err = { 
            { "error", "error" }
        };  
        return err; 
    }
    
    // Returns all appointments for an client
    public String[][] getAppt(Integer clientID){
        //Initialize the array
        apptList = new String[50][3];
        
        try {
            String query = "SELECT appointment_date, appointment_time, appointment_duration FROM appointment INNER JOIN client ON client.client_id = appointment.client_id WHERE client.client_id = \""+ clientID +"\";";
            ResultSet rs = stmt.executeQuery(query);
            int x = 0;
            int y = 0;
            while(rs.next()) {
                String apptDate = rs.getString("appointment_date");
                String apptTime = rs.getString("appointment_time");
                String apptDuration = rs.getString("appointment_duration");
                y = 0;
                apptList[x][y]= apptDate;
                y++;
                apptList[x][y] = apptTime;
                y++;
                apptList[x][y]= apptDuration;
                // now bump the counter and do it again
                x++;
            }
            return apptList;
        } catch (Exception e) {
            System.out.print("Could not get animalID: " + e);
        }
        String[][] err = { 
            { "error", "error", "error" }
        };  
        return err;
    }
}
