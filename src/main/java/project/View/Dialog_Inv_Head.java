/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.View;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author DELL
 */
public class Dialog_Inv_Head extends JDialog {
    private JTextField custNameField;
    private JTextField invDateField;
    private JLabel custNameLbl;
    private JLabel invDateLbl;
    private JButton okBtn;
    private JButton cancelBtn;

    public Dialog_Inv_Head(Invoice_Interface frame) {
        
        custNameField = new JTextField(20);
        custNameLbl = new JLabel("Customer Name:");
        invDateField = new JTextField(20);
        invDateLbl = new JLabel("Invoice Date:");
        cancelBtn = new JButton("Cancel");
        okBtn = new JButton("OK");
        
        cancelBtn.setActionCommand("CancelNewInvoice");
        okBtn.setActionCommand("SaveNewInvoice");
        
        cancelBtn.addActionListener(frame.getActionListener());
        okBtn.addActionListener(frame.getActionListener());
        
        setLayout(new GridLayout(3, 2));
        add(cancelBtn);
        add(invDateLbl);
        add(invDateField);
        add(custNameLbl);
        add(custNameField);
        add(okBtn);
        add(cancelBtn);
        
        pack();
        
    }

    public JTextField getCustomerName() {
        return custNameField;
    }

    public JTextField getInvoiceDate() {
        return invDateField;
    }
    
}
