/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project.Controller;

import java.util.ArrayList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import project.Model.Inv_Table_Line;
import project.Model.Inv_header;
import project.Model.Inv_line;
import project.View.Invoice_Interface;

/**
 *
 * @author Mahmoud.Adel
 */
public class Lsn_Select_Table implements ListSelectionListener{
    
    private Invoice_Interface frame;

    public Lsn_Select_Table(Invoice_Interface frame) {
        this.frame = frame;
    }
    

    @Override
    public void valueChanged(ListSelectionEvent e) {
        int InvSelected_Index = frame.getInvoicesTable().getSelectedRow();
        System.out.println("Invoice selected" + frame.getInvoicesTable().getSelectedRow());
        if(InvSelected_Index != -1){
        Inv_header Invoice_Selection = frame.getArray_Invoices().get(InvSelected_Index);
        ArrayList<Inv_line> Inv_Details = Invoice_Selection.getInvoiceLines();
        Inv_Table_Line tableLine = new Inv_Table_Line(Inv_Details);
        frame.setArray_Lines(Inv_Details);
        frame.getInvoiceItems().setModel(tableLine);
        frame.getCustomerName().setText(Invoice_Selection.getCust());
        frame.getInvoiceNum().setText(""+Invoice_Selection.getNumber());
        frame.getInvoiceTotal().setText(""+Invoice_Selection.getTotalOfInvoice());
        frame.getInvoiceDate().setText(Invoice_Interface.Format_Of_Date.format(Invoice_Selection.getDate_of_invoice()));
        }
    }
    
}
