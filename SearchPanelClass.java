/*
 * File: SearchPanelClass.java
 * Author: Stephanie Brinegar, Kenneth Harris, Neil Kohan, Jeremy Lantz
 * Course: CMSC 495
 * Project: vetclinic
 * Date: 10/02/2019
 * Platform: Windows 10
 * Compiler: Netbeans 82
 * Purpose: Creates GUI - Search Tab
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

public class SearchPanelClass extends JPanel implements ChangeListener {
    private TabPanel tp;

    //private JLabel

    public SearchPanelClass(TabPanel tp) {
        this.tp = tp;
        //JPanel p1 = new JPanel();
        setLayout( null );
        
        JLabel petLabel = new JLabel( "Pet Name:" );
        petLabel.setBounds( 75, 150, 150, 25 );
        add( petLabel );
        
        JTextField petField = new JTextField();
        petField.setBounds( 200, 150, 150, 25 );
        add( petField );
        
        JRadioButton r1 = new JRadioButton("And");
        JRadioButton r2 = new JRadioButton("Or");
        r1.setBounds( 200, 185, 50, 25 );
        r2.setBounds( 275, 185, 50, 25 );
        ButtonGroup bg = new ButtonGroup();
        bg.add( r1 );
        bg.add( r2 );
        add( r1 );
        add( r2 );
        
        JLabel ownerLabel = new JLabel( "Owner Last Name:" );
        ownerLabel.setBounds( 75, 225, 150, 25 );
        add( ownerLabel );
        
        JTextField ownerField = new JTextField();
        ownerField.setBounds( 200, 225, 150, 25 );
        add( ownerField );
        
        JButton search = new JButton( "Search" );
        search.setBounds( 185, 275, 80, 30);
        add( search );
        
        //return p1;
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        JTabbedPane tp = (JTabbedPane) e.getSource();
        int selectedIndex = tp.getSelectedIndex();
        JOptionPane.showMessageDialog(null, "Selected Index: " + selectedIndex);
    }
    
}
