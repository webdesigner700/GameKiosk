/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author vinay
 */

import java.util.ArrayList;
import java.util.List;

public class Customer {
    
    private int ID; 
    private String name; 
    private int balance;
    private List<Game> currentlyRented = new ArrayList<Game>(); 
    private List<Game> rentingHistory = new ArrayList<Game>();

    public Customer(int ID, String name, int balance) {
	this.ID = ID;
	this.name = name;
	this.balance = balance;
    }	

    public boolean hasID(int checkID) {
	return (this.ID == checkID);
    }

    public String getname() {
	return this.name;
    }

    public int getbalance() {
	return this.balance;
    }

    public void viewcustomer() {
	System.out.println("ID: " + this.ID);
	System.out.println("Name: " + this.name);
	System.out.println("Balance: $" + this.balance);
	System.out.println("Games currently rented by " + this.name + ":");
	for (Game game : currentlyRented) {
            System.out.println(game);
	}
	System.out.println(this.name + "'s renting history:");
	for (Game game : rentingHistory) {
            System.out.println(game);
	}
        System.out.print("\n");
    }

    public void viewfavgame() { 
    }

    public void topupaccount(int amount) {
	System.out.print("\n");
	int tempbalance = this.balance;
	this.balance = this.balance + amount;
	System.out.println("Transaction complete.");
	System.out.println(this.name + "'s balance was: $" + tempbalance);
	System.out.println(this.name + "'s current balance is: $" + this.balance);
	System.out.print("\n");
    }

    public void rentgame(Game game, int price) {
	this.balance = this.balance - price;
	currentlyRented.add(game);
	rentingHistory.add(game);
    }

    public void returngame(Game gamereturn) {
	currentlyRented.remove(gamereturn);
    }

    public void printrentedgames() {
	for (Game game : currentlyRented) {
            System.out.println(game);
	}
    }

    @Override
	public String toString() {
	return this.ID + "\t" + this.name + "\t" + "$ " + this.balance;
    }
}
