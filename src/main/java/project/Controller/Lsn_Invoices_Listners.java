/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project.Controller;

import java.awt.Frame;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import project.Model.Inv_Header_Table;
import project.Model.Inv_Table_Line;
import project.Model.Inv_header;
import project.Model.Inv_line;
import project.View.Dialog_Inv_Head;
import project.View.Dialog_Inv_Line;
import project.View.Invoice_Interface;

/**
 *
 * @author Mahmoud.Adel
 */
public class Lsn_Invoices_Listners implements ActionListener {
    private Invoice_Interface frame;
    private Dialog_Inv_Head DialogHead;
    private Dialog_Inv_Line DialogLine;

    public Lsn_Invoices_Listners(Invoice_Interface frame) {
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        switch (e.getActionCommand()) {
            

            case "Save File":
                Files_Saving();
                break;
                
            case "Load File":
                Files_Loading();
                break;
            
            case "Create New Invoice":
                Creat_New_Invoice();
                break;

            

            case "New Line":
                Line_New();
                break;
                
            case "Delete Invoice":
                Invoices_Deleting();
                break;   

            case "Delete Line":
                Line_Delete();
                break;
                
            case "SaveNewInvoice":
                Dialog_Invoice_Display();
                break;
                
            
                
            case "SaveNewLine":
                Dialog_Line_Display();
                break;
                
            case "CancelNewInvoice":
                Dialog_Invoice_Hide();
                break;
                
            case "CancelNewLine":
                Dialog_Line_Hide();
                break;
                
        }
    }

    private void Files_Loading() {
        JFileChooser chooser_File = new JFileChooser();

        try {
            int result = chooser_File.showOpenDialog(frame);
            if (JFileChooser.APPROVE_OPTION == result) {
                File File_Header = chooser_File.getSelectedFile();
                Path Path_Header = Paths.get(File_Header.getAbsolutePath());
                List < String > Lines_Header = Files.readAllLines(Path_Header);
                //List<String> Lines_Header = Files.readAllLines(Path_Header);
                ArrayList < Inv_header > invoiceHeader = new ArrayList < > ();
                for (String Line_Header: Lines_Header) {
                    String[] arr = Line_Header.split(",");
                    String str1 = arr[0];
                    String str2 = arr[1];
                    String str3 = arr[2];
                    int code = Integer.parseInt(str1);
                    Date Date_Invoice = Invoice_Interface.Format_Of_Date.parse(str2);
                    Inv_header header = new Inv_header(code, str3, Date_Invoice);
                    invoiceHeader.add(header);
                }
                frame.setArray_Invoices(invoiceHeader);

                result = chooser_File.showOpenDialog(frame);
                if (JFileChooser.APPROVE_OPTION == result) {
                    File File_Line = chooser_File.getSelectedFile();
                    Path Path_Line = Paths.get(File_Line.getAbsolutePath());
                    List < String > Line_Lines = Files.readAllLines(Path_Line);
                    ArrayList < Inv_line > invoiceLines = new ArrayList < > ();
                    for (String line_of_Line: Line_Lines) {
                        String[] arr = line_of_Line.split(",");
                        String str1 = arr[0];   // number of invoice -- int
                        String str2 = arr[1];   // item name  -- string
                        String str3 = arr[2];   // price item   -- double
                        String str4 = arr[3];   // count    -- int
                        int codeInvoice = Integer.parseInt(str1);
                        double itemPrice = Double.parseDouble(str3);
                        int countItem = Integer.parseInt(str4);
                        Inv_header invoice = frame.getInvoiceObject(codeInvoice);
                        Inv_line line = new Inv_line(str2, itemPrice, countItem, invoice);
                        invoice.getInvoiceLines().add(line);
                    }
                    Inv_Header_Table Table_Header = new Inv_Header_Table(invoiceHeader);
                    frame.setHeader_Table(Table_Header);
                    frame.getInvoicesTable().setModel(Table_Header);
                    
                }

            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(frame, ex.getMessage(), "Wrong things is occured.", JOptionPane.ERROR_MESSAGE);
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(frame, ex.getMessage(), "Wrong things is occured.", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void Files_Saving() {
        ArrayList<Inv_header> ArrOfInvoices = frame.getArray_Invoices();
        JFileChooser File_Chooser = new JFileChooser();
        try{
            int Res = File_Chooser.showSaveDialog(frame);
            if(Res == JFileChooser.APPROVE_OPTION){
                File Header_File = File_Chooser.getSelectedFile();
                FileWriter fileWriter = new FileWriter(Header_File);
                String hds = "";
                String lns = "";
                for(Inv_header invs : ArrOfInvoices){
                    hds += invs.toString();
                    hds += "\n";
                    for(Inv_line lins : invs.getInvoiceLines()){
                        lns += lins.toString();
                        lns += "\n";
                    }
                }
                hds = hds.substring(0, hds.length()-1);
                lns = lns.substring(0, lns.length()-1);
                Res = File_Chooser.showSaveDialog(frame);
                File File_Ln = File_Chooser.getSelectedFile();
                FileWriter File_Wr = new FileWriter(File_Ln);
                fileWriter.write(hds);
                File_Wr.write(lns);
                fileWriter.close();
                File_Wr.close(); 
            }
            
        }catch (IOException ex){
            JOptionPane.showMessageDialog(frame,ex.getMessage(),"Wrong" , JOptionPane.ERROR_MESSAGE);
        }
    }

    

    private void Line_New() {
        DialogLine = new Dialog_Inv_Line(frame);
        DialogLine.setVisible(true);
    }
    
    private void Invoices_Deleting() {
        int InvoiceSelctedIndex = frame.getInvoicesTable().getSelectedRow();
        if(InvoiceSelctedIndex != -1){
            frame.getArray_Invoices().remove(InvoiceSelctedIndex);
            frame.getHeader_Table().fireTableDataChanged();
            frame.getInvoiceItems().setModel(new Inv_Table_Line(null));
            frame.setArray_Lines(null);
            frame.getCustomerName().setText("");
            frame.getInvoiceNum().setText("");
            frame.getInvoiceTotal().setText("");
            frame.getInvoiceDate().setText("");
        }
    }

    

    private void Dialog_Invoice_Display() {
        
            DialogHead.setVisible(false);
            
            String NameOfCustomer = DialogHead.getCustomerName().getText();
            String date = DialogHead.getInvoiceDate().getText();
            Date D = new Date();
            try {
            D = Invoice_Interface.Format_Of_Date.parse(date);
          }  catch (ParseException ex) {
            JOptionPane.showMessageDialog(frame, "Parse Date Can't , ressting to today.", "Wrong Date Format",JOptionPane.ERROR_MESSAGE);
        }
            int NumOfInvoice = 0;
            for(Inv_header invc : frame.getArray_Invoices()){
                if(invc.getNumber() > NumOfInvoice) NumOfInvoice = invc.getNumber();
                
                
            }
            NumOfInvoice++;
            Inv_header newInvoice = new Inv_header(NumOfInvoice, NameOfCustomer , D);
            frame.getArray_Invoices().add(newInvoice);
            frame.getHeader_Table().fireTableDataChanged();
            
            DialogHead.dispose();
            DialogHead = null;
         
    }
    
    private void Line_Delete() {
        int Line_Index = frame.getInvoiceItems().getSelectedRow();
        int Invoice_Index = frame.getInvoicesTable().getSelectedRow();
        if(Line_Index != -1){
            frame.getArray_Lines().remove(Line_Index);
            Inv_Table_Line tableOfLine = (Inv_Table_Line)frame.getInvoiceItems().getModel();
            tableOfLine.fireTableDataChanged();
            frame.getInvoiceTotal().setText("" + frame.getArray_Invoices().get(Invoice_Index).getTotalOfInvoice());
            frame.getHeader_Table().fireTableDataChanged();
            frame.getInvoicesTable().setRowSelectionInterval(Line_Index, Line_Index);
            
        }
    }

    

    private void Creat_New_Invoice() {
        DialogHead = new Dialog_Inv_Head(frame);
        DialogHead.setVisible(true);
    }
    
    private void Dialog_Invoice_Hide() {
        DialogHead.setVisible(false);
        DialogHead.dispose();
        DialogHead = null;
    }

    private void Dialog_Line_Display() {
        
        DialogLine.setVisible(false);
        
        String Line_Name = DialogLine.getItemNameField().getText();
        String Count = DialogLine.getItemCountField().getText();
        String Price = DialogLine.getItemPriceField().getText();
        int COUNT = 1;
        double PRICE = 1;
        try{
            PRICE = Double.parseDouble(Price);
        }catch(NumberFormatException ex){
            JOptionPane.showMessageDialog(frame,"Converting Price is wrong","You Have invalid Price format", JOptionPane.ERROR_MESSAGE);
        }
        try{
            COUNT = Integer.parseInt(Count);
        }catch(NumberFormatException ex){
            JOptionPane.showMessageDialog(frame,"Converting number is wrong","You Have invalid number format", JOptionPane.ERROR_MESSAGE);
        }
        int InvoiceSelctedHeader = frame.getInvoicesTable().getSelectedRow();
        if(InvoiceSelctedHeader != -1){
            Inv_header Header_Invoice = frame.getArray_Invoices().get(InvoiceSelctedHeader);
            Inv_line InvLine = new Inv_line(Line_Name, PRICE, COUNT, Header_Invoice);
            //Header_Invoice.getInvoiceLines().add(InvLine);
            frame.getArray_Lines().add(InvLine);
            Inv_Table_Line tableOfLine = (Inv_Table_Line)frame.getInvoiceItems().getModel();
            tableOfLine.fireTableDataChanged();
            frame.getHeader_Table().fireTableDataChanged();
        }
        frame.getInvoicesTable().setRowSelectionInterval(InvoiceSelctedHeader, InvoiceSelctedHeader);
        DialogLine.dispose();
        DialogLine = null;
        
        
    }

    private void Dialog_Line_Hide() {
        DialogLine.setVisible(false);
        DialogLine.dispose();
        DialogLine = null;
    }

}