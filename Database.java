/*
 * File: Database.java
 * Author: Neil Kohan, Stephanie Brinegar, Kenneth Harris, Jeremy Lantz
 * Course: CMSC 495
 * Project: vetclinic
 * Date: 09/22/2019
 * Platform: Windows 10
 * Compiler: Netbeans 82
 * Purpose: Retrives data from database files, Loads records to database files
 */
package vetclinic;

import java.util.Date;

public class Database {
    
    Database(){
        
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
        return "Fido";
    }
    
    public String getFirstName(Integer animalID){
        return "John";
    }
    
    public String getLastName(Integer animalID){
        return "Doe";
    }
    
    public String getGender(Integer animalID){
        return "Male";
    }
    
    public String getWeight(Integer animalID){
        return "55.3";
    }
    
    public Integer postAnimal(String petName, String lastName, String firstName,
                              String Gender, String weight){
        
        return 1;
    }
    
    public void updateAnimal(Integer animalID, String weight){
        
    }
    
    public String[][] postMedication(Integer animalID, String medication, String medicationInfo){
        //Post the new medication then return back a list of known medications
        
        String[][] medData = { 
            { "Heartgard", "Once a month to prevent heartworm" },
            { "Aspirin Powder", "Apple flavor" }
        }; 
        
        return medData;
        
    }
    
    public String[][] postShots(Integer animalID, String shotList, String shotDate){
        
        //Post my shot
        
        //Now return a list of all known shots
        String[][] shotData = { 
            { "9-3-18", "Rabies" },
            { "9-3-18", "Distemper" }
        }; 
        
        return shotData;
        
    }
    
    public String[][] getShots(Integer animalID){
        
        //Now return a list of all known shots
        String[][] shotData = { 
            { "9-3-18", "Rabies" },
            { "9-3-18", "Distemper" }
        }; 
        
        return shotData;
    }
    
    public String[][] getMeds(Integer animalID){
        String[][] medData = { 
            { "Heartgard", "Once a month to prevent heartworm" },
            { "Aspirin Powder", "Apple flavor" }
        }; 
        
        return medData;
        
    }
    
    public String[][] getAppt(Integer animalID){
        String[][] apptData = { 
            { "9-3-19", "9:30am", "30" },
            { "9-12-19", "10:00am", "15" }
        };
        
        return apptData;
    }
}
