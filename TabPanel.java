/*
 * File: TabPanel.java
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
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.KeyEvent;
import java.awt.*;

public class TabPanel extends JPanel implements ChangeListener {
    
    public SearchPanelClass searchPanel = new SearchPanelClass(this);
    public ApptPanelClass apptPanel = new ApptPanelClass(this);
    public ClientPanelClass clientPanel = new ClientPanelClass(this);
    public UpdatePanelClass updatePanel = new UpdatePanelClass(this);
    
    public TabPanel() {
        super(new GridLayout(1, 1));
        
        JTabbedPane tp = new JTabbedPane();
        
        tp.addTab("Search", searchPanel);
        tp.setMnemonicAt(0, KeyEvent.VK_1);
        
        tp.addTab("Schedule an Appointment", apptPanel);
        tp.setMnemonicAt(0, KeyEvent.VK_2);
        
        tp.addTab("Client Card", clientPanel);
        tp.setMnemonicAt(0, KeyEvent.VK_3);
        
        tp.addTab("Update Fields", updatePanel);
        tp.setMnemonicAt(0, KeyEvent.VK_4);
        
        add(tp);
        
        tp.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
    }
    
    private static void showFrame() {
        JFrame frame = new JFrame( "Veterinary Clinic Management System" );
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        frame.setSize(600, 500);
        frame.add(new TabPanel(), BorderLayout.CENTER);
        
        frame.setVisible(true);
    }
    
    public static void main(String [] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                showFrame();
            }
        });
    }
    
    public void stateChanged(ChangeEvent e) {
        JTabbedPane tp = (JTabbedPane) e.getSource();
        int selectedIndex = tp.getSelectedIndex();
        JOptionPane.showMessageDialog(null, "Selected Index: " + selectedIndex);
    }
    
}
