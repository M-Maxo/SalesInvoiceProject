/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project.Model;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import project.View.Invoice_Interface;

/**
 *
 * @author Mahmoud.Adel
 */
public class Inv_Header_Table extends AbstractTableModel{
    
    private ArrayList<Inv_header> Array_Invoices;
    private String[] columnsOfTable = {"Invoice Num","Invoice Date","Customer Name","Invoice Total"}; 

    public Inv_Header_Table(ArrayList<Inv_header> Array_Invoices) {
        this.Array_Invoices = Array_Invoices;
    }
    
    

    @Override
    public int getRowCount() {
        return Array_Invoices.size();
    }

    @Override
    public int getColumnCount() {
        return columnsOfTable.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Inv_header fatora = Array_Invoices.get(rowIndex);
        switch (columnIndex){
            case 0: return fatora.getNumber();
            case 1: return Invoice_Interface.Format_Of_Date.format(fatora.getDate_of_invoice());
            case 2: return fatora.getCust();
            case 3: return fatora.getTotalOfInvoice();
        }
        return "";
    }

    @Override
    public String getColumnName(int column) {
        return columnsOfTable[column];
    }
    
    
    
}
