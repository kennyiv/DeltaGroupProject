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
    
    Database(){
        try {
            conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/" + db + "?user=" + user + "&password=" + password);
            stmt = conn.createStatement();
        } catch (Exception e) {
            System.out.print("Could not connect to DB: " + e);
        }
    }
    
    public Integer searchPetName(String animalName, String lastName, String operator){
        return 1;
    }
    
    public Boolean checkApptTime(Date checkDate, Date checkTime, Date durationTime){
        return true;
    }
    
    public void loadAppointment(Integer animalID, Date apptDate, Date apptTime, String doctor, Integer duration){
        
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
    
    public Integer postAnimal(String petName, String lastName, String firstName,
                              String Gender, String weight){
        
        return 1;
    }
    
    public void updateAnimal(Integer animalID, String weight){
        
    }
    
    public String[][] postMedication(Integer animalID, String medication, String medicationInfo){
        // Will need to implement arrayList
        //Post the new medication then return back a list of known medications
        
        String[][] medData = { 
            { "Heartgard", "Once a month to prevent heartworm" },
            { "Aspirin Powder", "Apple flavor" }
        }; 
        
        return medData;
        
    }
    
    public String[][] postShots(Integer animalID, String shotList, String shotDate){
        // Will need to implement arrayList
        //Post my shot
        
        //Now return a list of all known shots
        String[][] shotData = { 
            { "9-3-18", "Rabies" },
            { "9-3-18", "Distemper" }
        }; 
        
        return shotData;
        
    }
    
    public String[][] getShots(Integer animalID){
        /* Will need to implement arrayList
        try {
            String query = "SELECT medication.medication_name, petmedication.petmedication_added FROM medication INNER JOIN petmedication ON medication.medication_id = petmedication.medication_id INNER JOIN pet ON pet.pet_id = petmedication.pet_id WHERE pet.pet_id = \""+ animalID +"\";";
            ResultSet rs = stmt.executeQuery(query);
            String[][] shots;
            int i = 0;
            while(rs.next()) {
                String medicationName = rs.getString("medication_name");
                String date = rs.getString("petmedication_added");
                shots[i][1] = medicationName;
                shots[i][2] = date;
            }
        } catch (Exception e) {
            System.out.print("Could not get animalID: " + e);
        }
        String[][] err = { 
            { "error", "error" }
        };  
        return err;
        */
        //Now return a list of all known shots
        String[][] shotData = { 
            { "9-3-18", "Ok" },
            { "9-3-18", "Distemper" }
        }; 
        
        return shotData;
    }
    
    public String[][] getMeds(Integer animalID){
        // Will need to implement arrayList
        String[][] medData = { 
            { "Heartgard", "Once a month to prevent heartworm" },
            { "Aspirin Powder", "Apple flavor" }
        }; 
        
        return medData;
        
    }
    
    public String[][] getAppt(Integer animalID){
        // Will need to implement arrayList
        String[][] apptData = { 
            { "9-3-19", "9:30am", "30" },
            { "9-12-19", "10:00am", "15" }
        };
        
        return apptData;
    }
}
