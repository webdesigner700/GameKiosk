/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

/**
 *
 * @author vinay
 */

import java.util.ArrayList;
import java.util.List;

public class Kiosk {

    public static void main(String[] args) {
		Kiosk kiosk = new Kiosk();
		kiosk.usekiosk();
    }

    private Catalogue catalogue; 
    private List<Customer> customers = new ArrayList<Customer>();

    public Kiosk() {
	catalogue = new Catalogue(this);
	customers.add(new Customer(101, "Jaime", 10));
	customers.add(new Customer(102, "Luke", 10));
	customers.add(new Customer(103, "William", 1));
    }
	
	
    private char readKiosk() {
	System.out.println("Welcome to the Game Kiosk! Please make a selection from the menu:");
	System.out.println("1. Explore the catalogue.");
	System.out.println("2. View your customer record.");
	System.out.println("3. Show you favourite games.");
	System.out.println("4. Top up account.");
	System.out.println("5. Enter Admin Mode.");
	System.out.println("X. Exit the system.");
	System.out.print("Enter a choice: ");
	return In.nextChar();
    }

    private char readAdmin() {
	System.out.println("Welcome to the administration menu:");
	System.out.println("1. List all customers.");
	System.out.println("2. Add a customer.");
	System.out.println("3. Remove a customer.");
	System.out.println("4. List all games.");
	System.out.println("5. Add a game to the catalogue.");
	System.out.println("6. Remove a game from the catalogue.");
	System.out.println("R. Return to the previous menu.");
	System.out.print("Enter a choice: ");
	return In.nextChar();
    }

    private void usekiosk() {
	char choice;
	while ((choice = readKiosk()) != 'X') {
            switch (choice) {
		case '1' : catalogue.usecatalogue(); break;  
		case '2' : viewcustomer(); break;
		case '3' : viewfavgame(); break;
		case '4' : topupaccount(); break;
		case '5' : useadmin(); break;
		default : kioskhelp(); break;
            }
	}
	System.out.println("Thank you for using the Game Kiosk, do visit us again.");
    }

    private void useadmin() {
	char choice;
	boolean returnselected = false;
	while ((choice = readAdmin()) != 'X') {
            switch (choice) {
		case '1' : listcustomers(); break;
		case '2' : addcustomer(); break;
		case '3' : removecustomer(); break;
		case '4' : listgames(); break;
		case '5' : addgame(); break;
		case '6' : removegame(); break;
		case 'R' : return; 
            }
        }
    }

    private int readcustomerID() {
	System.out.print("\n" + "Enter a customer ID: ");
	return In.nextInt();
    }

    private int readID() {
	System.out.print("Enter a new ID: ");
	return In.nextInt();
    }

    private int readvalidID() {
	System.out.print("\n" + "Enter a valid customer ID: ");
	int ID = In.nextInt();
	for (Customer customer : customers) {
            if (customer.hasID(ID)) {
		return ID;
            }
	}
	return 0;
    }

    private String readname() {
	System.out.print("Enter the customer's name: ");
	return In.nextLine();
    }

    private int readbalance() {
	System.out.print("Enter the customer's initial balance: ");
	return In.nextInt();
    }

    private int readyear() {
	System.out.print("Enter the year: ");
	return In.nextInt();
    }

    private String readgenre() {
	System.out.print("Enter a genre: ");
	return In.nextLine();
    }

    private int readtopupamount() {
	System.out.print("Enter the top-up amount:");
	return In.nextInt();
    }

    private void viewcustomer() {      // START KIOSK METHODS
	int ID = readcustomerID();
	Customer customer = findcustomer(ID);
        
	if (customer != null) {
	customer.viewcustomer();
        }
	else {
            System.out.println("That customer does not exist." + "\n");
	}
    }

    private void viewfavgame() {
	int ID = readcustomerID();
	for (Customer customer : customers) {
            if (customer.hasID(ID)) {
		System.out.println(customer.getname() + "'s favourite games are:");
		customer.viewfavgame();
            }
	}
    } 

    private void topupaccount() {       // END KIOSK METHODS
	int ID = readcustomerID();
	int amount = readtopupamount();
	for (Customer customer : customers) {
            if (customer.hasID(ID)) {
		customer.topupaccount(amount);
            }
	}
    }		


    private void listcustomers() {      // START ADMIN METHODS
	System.out.println("\n" + "The Kiosk has the following customers:");     
	for (Customer customer : customers) {
            System.out.println(customer);
	}
	System.out.println("");
    }      

    private void addcustomer() {
	System.out.println("\n" + "Adding a new customer.");
	int ID = readID();
	Customer customer = findcustomer(ID);
	while (customer != null) {
            System.out.print("That customer already exists, please enter a new ID: ");
            ID = In.nextInt();
            customer = findcustomer(ID);
	}
	String name = readname();
	int balance = readbalance();
	customers.add(new Customer(ID, name, balance));
	System.out.println("Customer added." + "\n");
    }

    private void removecustomer() {
	System.out.print("\n" + "Removing a customer.");
	int ID = readcustomerID();
	Customer customer = findcustomer(ID);
	if (customer != null) {
            customers.remove(customer);
            System.out.println("Customer removed." + "\n");
	}
        else {
            System.out.println("That customer does not exist." +"\n");
	}
    }

    private void listgames() {
	catalogue.listgames();
    }
    
    private void addgame() {
	catalogue.addgame();
    }

    private void removegame() {
	catalogue.removegame();
    }                                  // END ADMIN METHODS    

    public void rentgame() {           //START CATALOGUE METHODS    
	int ID = readvalidID();
	for (Customer customer : customers) {
            int balance = customer.getbalance();
            if (customer.hasID(ID)) {
		Game game = catalogue.rentgame(balance);
		if (game != null) {
                    customer.rentgame(game, game.getprice()); 
                }	 
            }
	}
    }

    public void returngame() {
	int ID = readvalidID();
	for (Customer customer : customers) {
            if (customer.hasID(ID)) {
		System.out.println(customer.getname() + " has the following games:");
		System.out.println("Games currently rented by " + customer.getname() + ":");
		customer.printrentedgames();
		Game game =  catalogue.returngame();
		customer.returngame(game);
            }
	}
    }                                 // END CATALOGUE METHODS

    private Customer findcustomer(int ID) {
	for (Customer customer : customers) {
            if (customer.hasID(ID)) {
		return customer;
            }
	}
	return null;
    }

    private void kioskhelp() {
	System.out.println("Please enter a number between 1 and 5, or press X to exit.");
    }
}
