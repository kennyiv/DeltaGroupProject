/*
 * File: ApptPanelClass.java
 * Author: Stephanie Brinegar, Kenneth Harris, Neil Kohan, Jeremy Lantz
 * Course: CMSC 495
 * Project: vetclinic
 * Date: 10/02/2019
 * Platform: Windows 10
 * Compiler: Netbeans 82
 * Purpose: Creates GUI - Schedule Appt Tab
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

public class ApptPanelClass extends JPanel implements ChangeListener {
    private TabPanel tp;

    public ApptPanelClass(TabPanel tp) {
        this.tp = tp;
        setLayout( null );
        
        JLabel petLabel = new JLabel( "Pet Name:" );
        petLabel.setBounds( 10, 15, 150, 25 );
        add( petLabel );
        
        JTextField petField = new JTextField();
        petField.setBounds( 135, 15, 150, 25 );
        add( petField );
        
        JLabel firstLabel = new JLabel( "Owner First Name:" );
        firstLabel.setBounds( 10, 50, 150, 25 );
        add( firstLabel );
        
        JTextField firstField = new JTextField();
        firstField.setBounds( 135, 50, 150, 25 );
        add( firstField );
        
        JLabel lastLabel = new JLabel( "Owner Last Name:" );
        lastLabel.setBounds( 10, 85, 150, 25 );
        add( lastLabel );
        
        JTextField lastField = new JTextField();
        lastField.setBounds( 135, 85, 150, 25 );
        add( lastField );
        
        JLabel doctorLabel = new JLabel( "Doctor:" );
        doctorLabel.setBounds( 10, 120, 150, 25 );
        add( doctorLabel );
        
        JTextField doctorField = new JTextField();
        doctorField.setBounds( 135, 120, 150, 25 );
        add( doctorField );
        
        JLabel dateLabel = new JLabel( "Date:" );
        dateLabel.setBounds( 10, 155, 150, 25 );
        add( dateLabel );
        
        JTextField dateField = new JTextField();
        dateField.setBounds( 135, 155, 150, 25 );
        add( dateField );
        
        JLabel timeLabel = new JLabel( "Time:" );
        timeLabel.setBounds( 10, 190, 150, 25 );
        add( timeLabel );
        
        JTextField timeField = new JTextField();
        timeField.setBounds( 135, 190, 150, 25 );
        add( timeField );
        
        JLabel durLabel = new JLabel( "Duration:" );
        durLabel.setBounds( 10, 225, 150, 25 );
        add( durLabel );
        
        JTextField durField = new JTextField();
        durField.setBounds( 135, 225, 150, 25 );
        add( durField );
        
        JButton search = new JButton( "Schedule" );
        search.setBounds( 185, 270, 150, 30);
        add( search );
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        JTabbedPane tp = (JTabbedPane) e.getSource();
        int selectedIndex = tp.getSelectedIndex();
        JOptionPane.showMessageDialog(null, "Selected Index: " + selectedIndex);
    }
    
}
