/*
 * File: RunInput.java
 * Author: Neil Kohan, Stephanie Brinegar, Kenneth Harris, Jeremy Lantz
 * Course: CMSC 495
 * Project: vetclinic
 * Date: 09/22/2019
 * Platform: Windows 10
 * Compiler: Netbeans 82
 * Purpose: Handles requests from GUI input, processes information, passes data back to GUI
 */
package vetclinic;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

public class RunInput {
    
    Database Database = new Database();
    String requestedField;
    static Integer checkID;
    
    public Integer checkSearch(String petName, String lastName, String operator){
        //check for a valid animal ID
        //String checkPetName = petName;
        //String checkLastName =  lastName;
        //String checkOperator = operator;
        checkID = Database.searchPetName(petName, lastName, operator);
        
        if (checkID.equals(0)){
            JOptionPane.showMessageDialog(null, "Animal not found", "Error",JOptionPane.WARNING_MESSAGE);
        } 
        return checkID;
    }
    
    public Boolean checkNewAppt(String petName, String firstName, String lastName,
                    String doctor, String apptDate, String apptTime, String Duration){
        //Load some default variables
        Date checkDate;
        Date checkTime;
        Integer checkDuration;
        Date durationTime;
        
        //First validate the animal ID
        Integer checkID = Database.searchPetName(petName, lastName, "AND");
        if (checkID.equals(0)){
           JOptionPane.showMessageDialog(null, "Animal not found", "Error",JOptionPane.WARNING_MESSAGE);
           return false;
        }
        
        //Next check to ensure they entered a valid date
        SimpleDateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
        try{
            checkDate = dateformat.parse(apptDate);
        } catch (ParseException e){
            JOptionPane.showMessageDialog(null, "Invalid date entered the required format is DD/MM/YYYY", "Error",JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        //Next validate the time
        SimpleDateFormat timeformat = new SimpleDateFormat("hh:mm a");
        try{
            checkTime = timeformat.parse(apptTime);
        } catch (ParseException e){
            JOptionPane.showMessageDialog(null, "Invalid time entered the requried format is HH:MM in 24 hour format", "Error",JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        //Lastly verify the duration
        try{
            checkDuration = Integer.parseInt(Duration);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid duration was entered", "Error",JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        //Now that we have verified everything check to see if we have a scheduling conflict
        //start by adding the duration to the appointment time
        durationTime = checkTime; 
        
        //Once we have our calculated date pass the appointment time details to 
        //the database for checking
        Boolean isGoodAppt = Database.checkApptTime(checkDate, checkTime, durationTime);
        if (isGoodAppt == false){
            JOptionPane.showMessageDialog(null, "Requested appointment overlaps existing appointment", "Error",JOptionPane.WARNING_MESSAGE);
        }
        
        //If we made it all the way down here pass the database the appointment
        //details to load to the database
        Database.loadAppointment(checkID, checkDate, checkTime, doctor, checkDuration);
       
        return true; 
    }
    
    public String getAnimalInfo(Integer inputID, String fileField){
        //Retrieve the specified field from the data base
        switch (fileField){
            case "animalName":
                requestedField = Database.getAnimalName(inputID);
                break;
            case "firstName":
                requestedField = Database.getFirstName(inputID);
                break;
            case "lastName":
                requestedField = Database.getLastName(inputID);
                break;
            case "weight":
                requestedField = Database.getWeight(inputID);
                break;
            case "gender":
                requestedField = Database.getGender(inputID);
                break;
            default:
                
        }
        return requestedField;
    }
    
    public Integer checkClient(Integer checkAnimalID, String petName, String gender,
                            String weight, String lastName, String firstName){ 
   
        
        //If we didn't have an animal ID create one otherwise update the weight
        if (checkAnimalID.equals(0)){
            checkAnimalID = Database.postAnimal(petName, lastName, firstName, gender, weight);
        } else {
            Database.updateAnimal(checkAnimalID, weight);
        }
        
        return checkAnimalID;
    }
    
    public String[][] updateShots(Integer animalID, String shot, String shotDate){
        //Post my new shot then return my shot list
        String[][] shotList = Database.postShots(animalID, shot, shotDate);
        
        return shotList;
        
    }
    
    public String[][] updateMedication(Integer animalID, String medication, String medicationInfo){
        //Post the new medication then return back a list of known medications
        String[][] medList = Database.postMedication(animalID, medication, medicationInfo );
        
        //return th medication list
        return medList;
    }
    
    public String[][] getShotList(Integer animalID){
        //Go get the shot list from the database
        String[][] shotList = Database.getShots(animalID);
        
        //return the shot list to the caller
        return shotList;
       
    }
    
    public String[][] getMedList(Integer animalID){
        //Go get the medication list from the database
        String[][] medList = Database.getMeds(animalID);
        
        //return the medication list to the caller
        return medList;
    }
    
    public String[][] getApptList(Integer animalID){
        //Go ge the appointment list from the database
        String[][] apptList = Database.getAppt(animalID);
        
        //return the appointment list to the caller
        return apptList;
    }
    
}
