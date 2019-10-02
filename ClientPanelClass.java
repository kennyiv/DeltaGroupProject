/*
 * File: ClientPanelClass.java
 * Author: Stephanie Brinegar, Kenneth Harris, Neil Kohan, Jeremy Lantz
 * Course: CMSC 495
 * Project: vetclinic
 * Date: 10/02/2019
 * Platform: Windows 10
 * Compiler: Netbeans 82
 * Purpose: Creates GUI - Client Card Tab
 */
package vetclinic;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.KeyEvent;
import java.awt.*;

/**
 *
 * @author Steph5
 */

public class ClientPanelClass extends JPanel implements ChangeListener {
    private TabPanel tp;

    public ClientPanelClass(TabPanel tp) {
        this.tp = tp;
        setLayout( null );
        
        JLabel petLabel = new JLabel( "Pet Name:" );
        petLabel.setBounds( 10, 15, 150, 25 );
        add( petLabel );
        
        JTextField petData = new JTextField();
        petData.setBounds( 80, 15, 150, 25 );
        petData.setOpaque(true);
        petData.setBackground(Color.LIGHT_GRAY);
        add( petData );
        
        JLabel genderLabel = new JLabel( "Gender:" );
        genderLabel.setBounds( 10, 50, 50, 25 );
        add( genderLabel );
        
        JTextField genderData = new JTextField();
        genderData.setBounds( 60, 50, 50, 25 );
        genderData.setOpaque(true);
        genderData.setBackground(Color.LIGHT_GRAY);
        add( genderData );
        
        JLabel weightLabel = new JLabel( "Weight:" );
        weightLabel.setBounds( 120, 50, 50, 25 );
        add( weightLabel );
        
        JTextField weightData = new JTextField();
        weightData.setBounds( 180, 50, 50, 25 );
        weightData.setOpaque(true);
        weightData.setBackground(Color.LIGHT_GRAY);
        add( weightData );
        
        JLabel lastLabel = new JLabel( "Owner's Last Name:" );
        lastLabel.setBounds( 270, 15, 150, 25 );
        add( lastLabel );
        
        JTextField lastData = new JTextField();
        lastData.setBounds( 400, 15, 150, 25 );
        lastData.setOpaque(true);
        lastData.setBackground(Color.LIGHT_GRAY);
        add( lastData );
        
        JLabel firstLabel = new JLabel( "Owner's First Name:" );
        firstLabel.setBounds( 270, 50, 150, 25 );
        add( firstLabel );
        
        JTextField firstData = new JTextField();
        firstData.setBounds( 400, 50, 150, 25 );
        firstData.setOpaque(true);
        firstData.setBackground(Color.LIGHT_GRAY);
        add( firstData );
        
        JLabel medLabel = new JLabel( "Medication List:" );
        medLabel.setBounds( 10, 90, 150, 25 );
        add( medLabel );
       
        JScrollPane medsp = new JScrollPane();
        medsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        medsp.setBounds( 10, 115, 250, 100 );
        add( medsp );
        
        //DATA TO BE REPLACED with Query
        String[][] medData = { 
            { "Heartgard", "Once a month to prevent heartworm" },
            { "Aspirin Powder", "Apple flavor" }
        }; 
        String[] medCol = { "Med Name", "Med Info" };
        
        JTable medTable = new JTable(medData, medCol);
        medTable.setBackground(Color.LIGHT_GRAY);
        medsp.setViewportView( medTable );
        
        JLabel shotLabel = new JLabel( "Shot List:" );
        shotLabel.setBounds( 10, 225, 150, 25 );
        add( shotLabel );
        
        JScrollPane shotsp = new JScrollPane();
        shotsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        shotsp.setBounds( 10, 250, 250, 100 );
        add( shotsp );
        
        //DATA TO BE REPLACED with Query
        String[][] shotData = { 
            { "9-3-18", "Rabies" },
            { "9-3-18", "Distemper" }
        }; 
        String[] shotCol = { "Date", "Shot" };
        
        JTable shotTable = new JTable(shotData, shotCol);
        shotTable.setBackground(Color.LIGHT_GRAY);
        shotsp.setViewportView( shotTable );
        
        JLabel apptLabel = new JLabel( "Appointments:" );
        apptLabel.setBounds( 300, 90, 150, 25 );
        add( apptLabel );
        
        JScrollPane apptsp = new JScrollPane();
        apptsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        apptsp.setBounds( 300, 115, 250, 240 );
        add( apptsp );
        
        //DATA TO BE REPLACED with Query
        //loadAppointment()
        String[][] apptData = { 
            { "9-3-19", "9:30am", "30" },
            { "9-12-19", "10:00am", "15" }
        }; 
        String[] apptCol = { "Date", "Time", "Duration" };
        
        JTable apptTable = new JTable(apptData, apptCol);
        apptTable.setBackground(Color.LIGHT_GRAY);
        apptsp.setViewportView( apptTable );
        setVisible(true);
        
        JButton update = new JButton( "Update" );
        update.setBounds( 450, 375, 115, 30);
        add( update );
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        JTabbedPane tp = (JTabbedPane) e.getSource();
        int selectedIndex = tp.getSelectedIndex();
        JOptionPane.showMessageDialog(null, "Selected Index: " + selectedIndex);
    }
    
}
