package com.example.demo;

public class Transaction {

    private String size;
    private String color;

    public Transaction(String size, String color){

        this.size = size;
        this.color = color;
    }

    public double calculateTaxes(){
        return 0.00;
    }

    public void swipeCard(double total){
        System.out.println("Credit card was swiped");
    }

    public double getPrice(){

        double price = 19.99;
        if(size.equals("L")){
            price = 22.99;
        }

        return price;
    }


    
    public void executeTransaction() throws Exception{

        double taxes = this.calculateTaxes();
        double price = this.getPrice();

        if(taxes == 0.00) {
            throw new Exception("Invalid tax rate for chosen item");
        }

        double total = price * (1 + taxes);
        this.swipeCard(total);

        return;

    }
    
}
