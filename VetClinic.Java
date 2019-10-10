/*
 * File: VetClinic.java
 * Author: Stephanie Brinegar, Kenneth Harris, Neil Kohan, Jeremy Lantz
 * Course: CMSC 495
 * Project: vetclinic
 * Date: 10/02/2019
 * Platform: Windows 10
 * Compiler: Netbeans 82
 * Purpose: Creates GUI
 */

package vetclinic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VetClinic extends JFrame implements ActionListener{
    //Initiate the RunInput class
    RunInput RunInput = new RunInput();
    
    //Declare our card layout variables
    final static String search = "Search";
    final static String appointment = "Schedule Appointment";
    final static String client = "Client Card";
    final static String update = "Update";
    private JButton searchButton, appointmentButton, clientButton;
    private JPanel searchCard, appointmentCard, clientCard, updateCard;
    private JPanel cardPanel, buttonPanel;
  
    //Declare our global screen determining variables
    private String selected = "Search";
    private Integer animalID = 0;
    
    //declare thee search panel variables
    private JTextField petField, ownerField;
    private JButton searchPanelSearchButton;
    private JRadioButton r1, r2;
    
    //declare the appointment panel variables
    private JTextField appointmentPetField,appointmentFirstField,appointmentLastField,
            appointementDoctorField, dateField, timeField, durField;
    private JButton appointmentSchedule;
    
    //declare the client panel variables
    private JTextField petData, genderData, weightData, lastData, firstData;
    private JTable medTable, shotTable, apptTable;
    private JButton updateClientUpdateButton, detailClientDetailButton;
    private String preloadPet, preloadFirst, preloadLast, preloadWeight, preloadGender;
    public String[][] preloadShotList, preloadMedList, preloadApptList;
    
    //declare the update panel variables
    private JTextField petUpdateField, medField, shotField, dateUpdateField;
    private JTextArea medInfoField;
    private JButton addMed, addShot, back;
    private String preloadUpdatePet;
    
    public void VetClinic(Container pane) {
        
        //Create the panel and buttons to determine which card will be selected
        buttonPanel = new JPanel();
        searchButton = new JButton(search);
        appointmentButton = new JButton(appointment);
        clientButton = new JButton(client);
        
        //Add action listeners to the buttons then add them to the panel
        searchButton.addActionListener(this);
        appointmentButton.addActionListener(this);
        clientButton.addActionListener(this);
        buttonPanel.add(searchButton);
        buttonPanel.add(appointmentButton);
        buttonPanel.add(clientButton);
        
        //build the individual cards
        cardPanel = new JPanel(new CardLayout());
        cardPanel.setPreferredSize(new Dimension(600, 500));
        searchCard = searchPanel();
        appointmentCard = appointmentPanel();
        clientCard = clientPanel();
        updateCard = updatePanel();
        
        //Add the individual cards to the card layout
        cardPanel.add(searchCard, search);
        cardPanel.add(appointmentCard, appointment);
        cardPanel.add(clientCard, client);
        cardPanel.add(updateCard, update);
        
        //Addd the panels to the pane
        pane.add(buttonPanel, BorderLayout.PAGE_START);
        pane.add(cardPanel, BorderLayout.CENTER);
        
    }
      
    @Override
    public void actionPerformed(ActionEvent event){
        //Determine which card to load based on which button was pressed
        switch (event.getActionCommand()){
            case search:
                selected = "Search";
                break;
            case appointment:
                selected = "Appointment";
                break;
            case client:
                selected = "Client";
                break;
        }
        
        //Clear out the animal ID and load the correct card
        animalID = 0;
        CardLayout cl = (CardLayout)(cardPanel.getLayout());
        cl.show(cardPanel, event.getActionCommand());
        revalidate();
        repaint();
    }
    
    protected JPanel searchPanel(){
        //create the search panel and set the layout
        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(null);
        
        //Create the pet label
        JLabel petLabel = new JLabel( "Pet Name:" );
        petLabel.setBounds( 75, 150, 150, 25 );
        searchPanel.add( petLabel );
        
        //create the pet field
        petField = new JTextField();
        petField.setBounds( 200, 150, 150, 25 );
        searchPanel.add( petField );
        
        //create the and/or radio buttons then add to the button group
        r1 = new JRadioButton("And");
        r1.setSelected(true);
        r2 = new JRadioButton("Or");
        r1.setBounds( 200, 185, 50, 25 );
        r2.setBounds( 275, 185, 50, 25 );
        ButtonGroup bg = new ButtonGroup();
        bg.add( r1 );
        bg.add( r2 );
        
        //Add the radio buttons to the panel
        searchPanel.add( r1 );
        searchPanel.add( r2 );
        
        //create the owner label
        JLabel ownerLabel = new JLabel( "Owner Last Name:" );
        ownerLabel.setBounds( 75, 225, 150, 25 );
        searchPanel.add( ownerLabel );
        
        //create the owner field
        ownerField = new JTextField();
        ownerField.setBounds( 200, 225, 150, 25 );
        searchPanel.add( ownerField );
        
        //create the search button then add an action listener to handle a button click
        searchPanelSearchButton = new JButton( "Search" );
        searchPanelSearchButton.setBounds( 185, 275, 80, 30);
        searchPanel.add( searchPanelSearchButton );
        searchPanelSearchButton.addActionListener(e -> searchInput());
        
        //return the built searh panel to the caller
        return searchPanel;
    }
    
    public void searchInput(){
        //Retrieve the text from the screen
        String petName = petField.getText();
        String ownerName = ownerField.getText();
        
        //Handle the input based on the radio button selection
        if (r1.isSelected()){
            animalID = RunInput.checkSearch(petName, ownerName, "AND");
        } else {
            animalID = RunInput.checkSearch(petName, petName, "OR");
        }
        
        //If we got a successful hit on the animal ID then change the selected panel
        if(animalID > 0 ){
            selected = client;
            CardLayout cl = (CardLayout)(cardPanel.getLayout());
            cl.show(cardPanel, selected);
        }
    }
    
    protected JPanel appointmentPanel(){
        //create the appointment panel and set the layout
        JPanel appointmentPanel = new JPanel();
        appointmentPanel.setLayout(null);
        
        //create the pet label
        JLabel petLabel = new JLabel( "Pet Name:" );
        petLabel.setBounds( 10, 15, 150, 25 );
        appointmentPanel.add( petLabel );
        
        //create the appointment pet field
        appointmentPetField = new JTextField();
        appointmentPetField.setBounds( 135, 15, 150, 25 );
        appointmentPanel.add( appointmentPetField );
        
        //create the first label
        JLabel firstLabel = new JLabel( "Owner First Name:" );
        firstLabel.setBounds( 10, 50, 150, 25 );
        appointmentPanel.add( firstLabel );
        
        //create the first field
        appointmentFirstField = new JTextField();
        appointmentFirstField.setBounds( 135, 50, 150, 25 );
        appointmentPanel.add( appointmentFirstField );
        
        //create the last label
        JLabel lastLabel = new JLabel( "Owner Last Name:" );
        lastLabel.setBounds( 10, 85, 150, 25 );
        appointmentPanel.add( lastLabel );
        
        //create the last field
        appointmentLastField = new JTextField();
        appointmentLastField.setBounds( 135, 85, 150, 25 );
        appointmentPanel.add( appointmentLastField );
        
        //create the doctor label
        JLabel doctorLabel = new JLabel( "Doctor:" );
        doctorLabel.setBounds( 10, 120, 150, 25 );
        appointmentPanel.add( doctorLabel );
        
        //create the doctor field
        appointementDoctorField = new JTextField();
        appointementDoctorField.setBounds( 135, 120, 150, 25 );
        appointmentPanel.add( appointementDoctorField );
        
        //create the appointment date label
        JLabel dateLabel = new JLabel( "Date:" );
        dateLabel.setBounds( 10, 155, 150, 25 );
        appointmentPanel.add( dateLabel );
        
        //create the appointment date field
        dateField = new JTextField();
        dateField.setBounds( 135, 155, 150, 25 );
        appointmentPanel.add( dateField );
        
        //create the appointment time label
        JLabel timeLabel = new JLabel( "Time:" );
        timeLabel.setBounds( 10, 190, 150, 25 );
        appointmentPanel.add( timeLabel );
        
        //create the appointment time field
        timeField = new JTextField();
        timeField.setBounds( 135, 190, 150, 25 );
        appointmentPanel.add( timeField );
        
        //create the appointment duration label
        JLabel durLabel = new JLabel( "Duration:" );
        durLabel.setBounds( 10, 225, 150, 25 );
        appointmentPanel.add( durLabel );
        
        //create the appointment duration field
        durField = new JTextField();
        durField.setBounds( 135, 225, 150, 25 );
        appointmentPanel.add( durField );
        
        //create the appointment schedule button then add an action listener
        //to handle any button clicks
        appointmentSchedule = new JButton( "Schedule" );
        appointmentSchedule.setBounds( 185, 270, 150, 30);
        appointmentPanel.add( appointmentSchedule );
        appointmentSchedule.addActionListener(e -> appointmentInput());
        
        //return the built appointment panel
        return appointmentPanel;
        
    }
    
    public void appointmentInput(){
        //retrieve the values from the screen
        String petName = appointmentPetField.getText();
        String firstName = appointmentFirstField.getText();
        String lastName = appointmentLastField.getText();
        String doctor = appointementDoctorField.getText();
        String date = dateField.getText();
        String time = timeField.getText();
        String duration = durField.getText();
        
        //Pass the screen data to the input checker to attempt to add an appointment
        Boolean appointmentCheck = RunInput.checkNewAppt(petName, firstName, lastName, 
                doctor, date, time, duration);
        
        //If we were able to generate an appointment return them to the search panel
        if(appointmentCheck == true){
            selected = search;
            CardLayout cl = (CardLayout)(cardPanel.getLayout());
            cl.show(cardPanel, selected);
        }
    }
    
    protected JPanel clientPanel(){
        //preload the screen variables
        preloadClient();
        
        //create the client panel and set the layout
        JPanel clientPanel = new JPanel();
        clientPanel.setLayout(null);
        
        //create the pet label
        JLabel petLabel = new JLabel( "Pet Name:" );
        petLabel.setBounds( 10, 15, 150, 25 );
        clientPanel.add( petLabel );
        
        //create the pet data field
        petData = new JTextField();
        petData.setBounds( 80, 15, 150, 25 );
        petData.setOpaque(true);
        petData.setBackground(Color.LIGHT_GRAY);
        clientPanel.add( petData );
        
        //create the gender label
        JLabel genderLabel = new JLabel( "Gender:" );
        genderLabel.setBounds( 10, 50, 50, 25 );
        clientPanel.add( genderLabel );
        
        //create the gender field
        genderData = new JTextField();
        genderData.setBounds( 60, 50, 50, 25 );
        genderData.setOpaque(true);
        genderData.setBackground(Color.LIGHT_GRAY);
        clientPanel.add( genderData );
        
        //create the weight label
        JLabel weightLabel = new JLabel( "Weight:" );
        weightLabel.setBounds( 120, 50, 50, 25 );
        clientPanel.add( weightLabel );
        
        //create the weight field
        weightData = new JTextField();
        weightData.setBounds( 180, 50, 50, 25 );
        weightData.setOpaque(true);
        weightData.setBackground(Color.LIGHT_GRAY);
        clientPanel.add( weightData );
        
        //create the last label
        JLabel lastLabel = new JLabel( "Owner's Last Name:" );
        lastLabel.setBounds( 270, 15, 150, 25 );
        clientPanel.add( lastLabel );
        
        //create the last data field
        lastData = new JTextField();
        lastData.setBounds( 400, 15, 150, 25 );
        lastData.setOpaque(true);
        lastData.setBackground(Color.LIGHT_GRAY);
        clientPanel.add( lastData );
        
        //create the first label 
        JLabel firstLabel = new JLabel( "Owner's First Name:" );
        firstLabel.setBounds( 270, 50, 150, 25 );
        clientPanel.add( firstLabel );
        
        //create the first data field
        firstData = new JTextField();
        firstData.setBounds( 400, 50, 150, 25 );
        firstData.setOpaque(true);
        firstData.setBackground(Color.LIGHT_GRAY);
        clientPanel.add( firstData );
        
        //create the med label 
        JLabel medLabel = new JLabel( "Medication List:" );
        medLabel.setBounds( 10, 90, 150, 25 );
        clientPanel.add( medLabel );
       
        //Create the scroll pane for displaying the medication list
        JScrollPane medsp = new JScrollPane();
        medsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        medsp.setBounds( 10, 115, 250, 100 );
        clientPanel.add( medsp );
        
        //create the column headings then create the med table
        String[] medCol = { "Med Name", "Med Info" };
        medTable = new JTable(preloadMedList, medCol);
        medTable.setBackground(Color.LIGHT_GRAY);
        medsp.setViewportView( medTable );
        
        //create the shot label
        JLabel shotLabel = new JLabel( "Shot List:" );
        shotLabel.setBounds( 10, 225, 150, 25 );
        clientPanel.add( shotLabel );
        
        //create the scroll pane to display the shots
        JScrollPane shotsp = new JScrollPane();
        shotsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        shotsp.setBounds( 10, 250, 250, 100 );
        clientPanel.add( shotsp );
        
        //Create the column headings then the shot table
        String[] shotCol = { "Date", "Shot" };
        shotTable = new JTable(preloadShotList, shotCol);
        shotTable.setBackground(Color.LIGHT_GRAY);
        shotsp.setViewportView( shotTable );
        
        //create the appointments label
        JLabel apptLabel = new JLabel( "Appointments:" );
        apptLabel.setBounds( 300, 90, 150, 25 );
        clientPanel.add( apptLabel );
        
        //create the scroll pane to display the appointments
        JScrollPane apptsp = new JScrollPane();
        apptsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        apptsp.setBounds( 300, 115, 250, 240 );
        clientPanel.add( apptsp );
        
        //create the column headings then create the appointment table
        String[] apptCol = { "Date", "Time", "Duration" };
        apptTable = new JTable(preloadApptList, apptCol);
        apptTable.setBackground(Color.LIGHT_GRAY);
        apptsp.setViewportView( apptTable );
        
        //create the client update button then add an action listener to 
        //handle any button clicks
        updateClientUpdateButton = new JButton( "Update" );
        updateClientUpdateButton.setBounds( 450, 375, 115, 30);
        clientPanel.add( updateClientUpdateButton );
        updateClientUpdateButton.addActionListener(e -> clientInput());
        
        //create the details button then add an action listener to 
        //direct the user to the details panel
        detailClientDetailButton = new JButton("Details");
        detailClientDetailButton.setBounds(250, 375, 115, 30);
        clientPanel.add( detailClientDetailButton);
        detailClientDetailButton.addActionListener(e -> clientDetailButton());
        
        //load the remaining screen fields
        loadClient();
        
        //return the built client panel
        return clientPanel;
    }
    
    public void preloadClient(){
        
        //get all the animal data fields
        preloadPet = RunInput.getAnimalInfo(animalID, "animalName");
        preloadFirst = RunInput.getAnimalInfo(animalID, "firstName");
        preloadLast = RunInput.getAnimalInfo(animalID, "lastName");
        preloadWeight = RunInput.getAnimalInfo(animalID, "weight");
        preloadGender = RunInput.getAnimalInfo(animalID, "gender");
        
        //Retrieve the shot list
        preloadShotList = RunInput.getShotList(animalID);
        
        //Retrieve the medication list
        preloadMedList = RunInput.getMedList(animalID);
        
        //Retrieve the appointment list
        preloadApptList = RunInput.getApptList(animalID);
    }
    
    //preload all the screen fields
    public void loadClient(){
        petData.setText(preloadPet);
        lastData.setText(preloadLast);
        firstData.setText(preloadFirst);
        weightData.setText(preloadWeight);
        genderData.setText(preloadGender);
    }
    
    public void clientInput(){
        //Load the data from the screen
        String petName = petData.getText();
        String lastName = lastData.getText();
        String firstName = firstData.getText();
        String weight = weightData.getText();
        String gender = genderData.getText();
        
        //Pass the data to the client check method to add or update the pet data
        Integer refreshAnimalID = RunInput.checkClient(animalID, petName, gender, weight, lastName, firstName);
        
        //Load the returned animal ID and refresh the screen
        animalID = refreshAnimalID;
        revalidate();
        repaint();
        
        }
    
    public void clientDetailButton(){
        //call the details panel if the animalID is loaded otherwise we will try to 
        //create orphan data
        animalID = 1;
        if (animalID > 0){
            selected = "Update";
            CardLayout cl = (CardLayout)(cardPanel.getLayout());
            cl.show(cardPanel, update);
        } else {
            //display an error if no animal ID is known
            JOptionPane.showMessageDialog(null, "Animal ID Unknown please search for a specific animal prior to updating details", "Error",JOptionPane.WARNING_MESSAGE);
        }
    }
    
    protected JPanel updatePanel(){
        
        //preload the fields for the update panel
        preloadUpdate();
        
        //Create the detail panel and set the layout
        JPanel detailPanel = new JPanel();
        detailPanel.setLayout(null);
        
        //create the pet label
        JLabel petLabel = new JLabel( "Pet Name:" );
        petLabel.setBounds( 20, 15, 150, 25 );
        detailPanel.add( petLabel );
        
        //create the pet field
        petUpdateField = new JTextField();
        petUpdateField.setBounds( 145, 15, 150, 25 );
        petUpdateField.setBackground(Color.LIGHT_GRAY);
        petUpdateField.setEditable(false);
        detailPanel.add( petUpdateField );
        
        //Panel to either add to medication list (addMed button) or
        //Update client med list if petField is supplied (update button)
        JPanel medPanel = new JPanel();
        medPanel.setOpaque(true);
        medPanel.setBorder(
            BorderFactory.createTitledBorder("Medication List"));
        medPanel.setLayout(new GroupLayout(medPanel));
        medPanel.setBounds( 10, 50, 500, 125);
        
        //create the med label
        JLabel medLabel = new JLabel( "Medication:" );
        medLabel.setBounds( 10, 20, 150, 25 );
        medPanel.add( medLabel );
        
        //create the med field
        medField = new JTextField();
        medField.setBounds( 135, 20, 150, 25 );
        medPanel.add( medField );
        
        //create the med into label
        JLabel medInfoLabel = new JLabel( "Medication Info:" );
        medInfoLabel.setBounds( 10, 60, 150, 25 );
        medPanel.add( medInfoLabel );
        
        //create the med info text area
        medInfoField = new JTextArea();
        medInfoField.setLineWrap(true);
        medInfoField.setBounds( 135, 60, 200, 50 );
        medInfoField.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        medPanel.add( medInfoField );
        
        //create the add med button then add an action listener to handle any button
        //clicks
        addMed = new JButton("Add to List");
        addMed.setBounds( 375, 80, 100, 30 );
        medPanel.add( addMed );
        addMed.addActionListener(e -> medInput());
        
        //Panel to either add to shot list (addShot button) or
        //Update client shot list if petField is supplied (update button)
        JPanel shotPanel = new JPanel();
        shotPanel.setOpaque(true);
        shotPanel.setBorder(
            BorderFactory.createTitledBorder("Shot / Vaccine List"));
        shotPanel.setLayout(new GroupLayout(shotPanel));
        shotPanel.setBounds( 10, 190, 500, 110);
        
        //create the shot label
        JLabel shotLabel = new JLabel( "Shot:" );
        shotLabel.setBounds( 10, 20, 150, 25 );
        shotPanel.add( shotLabel );
        
        //create the shot field
        shotField = new JTextField();
        shotField.setBounds( 135, 20, 150, 25 );
        shotPanel.add( shotField );
        
        //create the add shot button then add an action listener to handle
        //any button clicks
        addShot = new JButton("Add to List");
        addShot.setBounds( 375, 20, 100, 30 );
        shotPanel.add( addShot );
        addShot.addActionListener(e -> shotInput());
        
        //create the shot date label
        JLabel dateLabel = new JLabel( "Shot Date:" );
        dateLabel.setBounds( 20, 265, 150, 25 );
        detailPanel.add( dateLabel );
        
        //create the shot date field
        dateUpdateField = new JTextField();
        dateUpdateField.setBounds( 145, 265, 150, 25 );
        detailPanel.add( dateUpdateField );
        
        //create the back button then add an action listener to handle any 
        //button clicks
        back = new JButton( "Back" );
        back.setBounds( 195, 315, 150, 30);
        detailPanel.add( back );
        back.addActionListener(e -> backButton());
        
        //add the med and shot panels to the detail panel
        detailPanel.add( medPanel,BorderLayout.CENTER );
        detailPanel.add( shotPanel,BorderLayout.CENTER );
        
        //Now preload the screen fields
        loadUpdate();
        
        //return the built detail panel
        return detailPanel;
        
    }
    
    public void preloadUpdate(){
        //preload the name
        preloadUpdatePet = RunInput.getAnimalInfo(animalID, "animalName");

    }
    
    public void loadUpdate(){
        //load the screen fields
        petUpdateField.setText(preloadUpdatePet);
    }
    
    //send the user back to the client panel
    public void backButton(){
        CardLayout cl = (CardLayout)(cardPanel.getLayout());
        cl.show(cardPanel, client);
    }
    
    
    public void medInput(){
        //Retrieve the screen values
        String med = medField.getText();
        String medInfo = medInfoField.getText();
        
        //Post the new medication to the database
        String[][] medList = RunInput.updateMedication(animalID, med, medInfo);
      
    }
    
    public void shotInput(){
        //Retrieve the screen values
        String shot = shotField.getText();
        String shotDate = dateUpdateField.getText();
        
        //post the new shot to the database
        String[][] shotList = RunInput.updateShots(animalID, shot, shotDate);
        
        
        }
    
    private static void createAndShowGUI() {
        //Make sure we have nice window decorations.
        JFrame.setDefaultLookAndFeelDecorated(true);

        //Create and set up the window.
        JFrame frame = new JFrame("Veterinary Clinic Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600,500);

        //Create and set up the content pane.
        VetClinic index = new VetClinic();
        index.VetClinic(frame.getContentPane());

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
    
    public static void main(String [] args) {
       
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
    
}
