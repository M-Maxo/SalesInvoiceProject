/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project.Model;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Mahmoud.Adel
 */
public class Inv_Table_Line extends AbstractTableModel{
    
    private ArrayList<Inv_line> Array_Lines;
    private String[] columnsOfTable = {"Item Name","Item Price","Count","Line Total"};

    public Inv_Table_Line(ArrayList<Inv_line> Array_Lines) {
        this.Array_Lines = Array_Lines;
    }
    

    @Override
    public int getRowCount() {
        return Array_Lines == null ? 0 : Array_Lines.size();
    }

    @Override
    public int getColumnCount() {
        return columnsOfTable.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if(Array_Lines == null){return "";}
        else{
        Inv_line ln = Array_Lines.get(rowIndex);
        switch (columnIndex){
            case 0: return ln.getProduct();
            case 1: return ln.getPrice();
            case 2: return ln.getTotal();
            case 3: return ln.getTotalOfLine();
            default: return "";
        }}
    }

    @Override
    public String getColumnName(int column) {
        return columnsOfTable[column];
    }
    
    
    
}
