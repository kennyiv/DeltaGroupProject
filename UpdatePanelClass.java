/*
 * File: UpdatePanelClass.java
 * Author: Stephanie Brinegar, Kenneth Harris, Neil Kohan, Jeremy Lantz
 * Course: CMSC 495
 * Project: vetclinic
 * Date: 10/02/2019
 * Platform: Windows 10
 * Compiler: Netbeans 82
 * Purpose: Creates GUI - Update Fields Tab
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

public class UpdatePanelClass extends JPanel implements ChangeListener {
    private TabPanel tp;

    public UpdatePanelClass(TabPanel tp) {
        this.tp = tp;
        setLayout( null );
        
        JLabel petLabel = new JLabel( "Pet Name:" );
        petLabel.setBounds( 20, 15, 150, 25 );
        add( petLabel );
        
        JTextField petField = new JTextField();
        petField.setBounds( 145, 15, 150, 25 );
        add( petField );
        
        //Panel to either add to medication list (addMed button) or
        //Update client med list if petField is supplied (update button)
        JPanel medPanel = new JPanel();
        medPanel.setOpaque(true);
        medPanel.setBorder(
            BorderFactory.createTitledBorder("Medication List"));
        medPanel.setLayout(new GroupLayout(medPanel));
        medPanel.setBounds( 10, 50, 500, 125);
        
        JLabel medLabel = new JLabel( "Medication:" );
        medLabel.setBounds( 10, 20, 150, 25 );
        medPanel.add( medLabel );
        
        JTextField medField = new JTextField();
        medField.setBounds( 135, 20, 150, 25 );
        medPanel.add( medField );
        
        JLabel medInfoLabel = new JLabel( "Medication Info:" );
        medInfoLabel.setBounds( 10, 60, 150, 25 );
        medPanel.add( medInfoLabel );
        
        JTextArea medInfoField = new JTextArea();
        medInfoField.setLineWrap(true);
        medInfoField.setBounds( 135, 60, 200, 50 );
        medInfoField.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        medPanel.add( medInfoField );
        
        JButton addMed = new JButton("Add to List");
        addMed.setBounds( 375, 80, 100, 30 );
        medPanel.add( addMed );
        
        //Panel to either add to shot list (addShot button) or
        //Update client shot list if petField is supplied (update button)
        JPanel shotPanel = new JPanel();
        shotPanel.setOpaque(true);
        shotPanel.setBorder(
            BorderFactory.createTitledBorder("Shot / Vaccine List"));
        shotPanel.setLayout(new GroupLayout(shotPanel));
        shotPanel.setBounds( 10, 190, 500, 60);
        
        JLabel shotLabel = new JLabel( "Shot:" );
        shotLabel.setBounds( 10, 20, 150, 25 );
        shotPanel.add( shotLabel );
        
        JTextField shotField = new JTextField();
        shotField.setBounds( 135, 20, 150, 25 );
        shotPanel.add( shotField );
        
        JButton addShot = new JButton("Add to List");
        addShot.setBounds( 375, 20, 100, 30 );
        shotPanel.add( addShot );
        
        JLabel dateLabel = new JLabel( "Shot Date:" );
        dateLabel.setBounds( 20, 265, 150, 25 );
        add( dateLabel );
        
        JTextField dateField = new JTextField();
        dateField.setBounds( 145, 265, 150, 25 );
        add( dateField );
        
        JButton search = new JButton( "Update Client" );
        search.setBounds( 195, 315, 150, 30);
        add( search );
        
        add( medPanel,BorderLayout.CENTER );
        add( shotPanel,BorderLayout.CENTER );
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        JTabbedPane tp = (JTabbedPane) e.getSource();
        int selectedIndex = tp.getSelectedIndex();
        JOptionPane.showMessageDialog(null, "Selected Index: " + selectedIndex);
    }
    
}
