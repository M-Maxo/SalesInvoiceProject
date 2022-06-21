/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project.Model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Mahmoud.Adel
 */
public class Inv_header {
    
    
    private Date date_of_invoice;
    private ArrayList<Inv_line> invoiceLines;
    private DateFormat Date_Format = new SimpleDateFormat("dd-MM-yyyy");
    private int number;
    private String cust;
    
    
    

    public Inv_header() {
    }

    

    public Date getDate_of_invoice() {
        return date_of_invoice;
    }

    public void setDate_of_invoice(Date date_of_invoice) {
        this.date_of_invoice = date_of_invoice;
    }
    
    public Inv_header(int number, String cust, Date date_of_invoice) {
        this.number = number;
        this.cust = cust;
        this.date_of_invoice = date_of_invoice;
    }
    
    public void setNumber(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    

    

    public void setCust(String cust) {
        this.cust = cust;
    }
    
    public String getCust() {
        return cust;
    }
    
    public void setInvoiceLines(ArrayList<Inv_line> invoiceLines) {
        this.invoiceLines = invoiceLines;
    }

    public ArrayList<Inv_line> getInvoiceLines() {
        if (invoiceLines == null){
            invoiceLines = new ArrayList<>();
        }
        return invoiceLines;
    }

    
    
    public double getTotalOfInvoice()
    {
        double invTotal=0.0;
        
        for(int i=0; i< getInvoiceLines().size(); i++)
        {
            invTotal+= invoiceLines.get(i).getTotalOfLine();
        }
        return invTotal;
    }

    @Override
    public String toString() {
        return "Inv_header{" + "number=" + number + ", cust=" + Date_Format.format(date_of_invoice) + "," + cust ;
    }
    
     
}
