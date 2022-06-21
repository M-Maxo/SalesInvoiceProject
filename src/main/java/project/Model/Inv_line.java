/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project.Model;

/**
 *
 * @author Mahmoud.Adel
 */
public class Inv_line {
    
    private String product;
    private double price;
    private int total;
    private Inv_header header;
    
    public Inv_line() {
    }

    public Inv_line(String product, double price, int total, Inv_header header) {
        this.product = product;
        this.price = price;
        this.total = total;
        this.header = header;
    }

   

    public void setHeader(Inv_header header) {
        this.header = header;
    }
    
     public Inv_header getHeader() {
        return header;
    }
     
    public void setProduct(String product) {
        this.product = product;
    }

    public String getProduct() {
        return product;
    }

    

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getTotal() {
        return total;
    }
    
    public double getTotalOfLine()
    {
        return price * total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
    
    

    @Override
    public String toString() {
        return header.getNumber() + "," + product + "," + price + "," + total;
    }
    
    
    
}
