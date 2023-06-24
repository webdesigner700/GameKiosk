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

public class Catalogue {
    
    private Kiosk kiosk;
    private List<Game> gamesAvailable = new ArrayList<Game>();
    private List<Game> gamesRented = new ArrayList<Game>();
    private List<Genre> genres = new ArrayList<Genre>();

    public Catalogue(Kiosk kiosk) {
	this.kiosk = kiosk;
	gamesAvailable.add(new Game("Robinson Crusoe", 2012, 3, "Action Queue"));
	gamesAvailable.add(new Game("Talisman", 2007, 4, "Role Playing"));
	gamesAvailable.add(new Game("Three Kingdoms Redux", 2014, 3, "Hand Management"));
	gamesAvailable.add(new Game("Dungeons & Dragons", 2010, 4, "Modular Board"));
	gamesAvailable.add(new Game("Elder Sign", 2011, 3, "Modular Board"));
	genres.add(new Genre("Action Queue"));
	genres.add(new Genre("Role Playing"));
	genres.add(new Genre("Hand Management"));
	genres.add(new Genre("Modular Board"));
    }

    private char readCatalogue() {

	System.out.println("Welcome to the Catalogue! Please make a selection from the menu:");
	System.out.println("1. Display all games.");
	System.out.println("2. Display all available games.");
	System.out.println("3. Display all genres.");
	System.out.println("4. Display games in a genre.");
	System.out.println("5. Display all games by year.");
	System.out.println("6. Rent a game.");
	System.out.println("7. Return a game.");
	System.out.println("R. Return to previous menu.");
	System.out.print("Enter a choice: ");
	return In.nextChar();
    }

    public void usecatalogue() {
	char choice;
	while ((choice = readCatalogue()) != 'X') {
            switch (choice) {
		case '1' : displaygames(); break;
		case '2' : availablegames(); break;
		case '3' : displaygenres(); break;
		case '4' : gamesingenre(); break;
		case '5' : gamesbyyear(); break;
		case '6' : kiosk.rentgame(); break;
		case '7' : kiosk.returngame(); break;
		case 'R' : ; return;
		default : cataloguehelp(); break;
            }
	}
    }


    private String readtitle() {
	System.out.print("Enter the title of the game: ");
	return In.nextLine();
    }

    private int readyear() {        
	System.out.print("Enter the year: ");
	return In.nextInt();
    }

    private String readgenre1() {
        System.out.print("Enter the genre: ");
	return In.nextLine();
    }

    private String readgenre2() {
	System.out.print("Enter a genre: ");
	return In.nextLine();
    }

    private int readprice() {
	System.out.print("Enter price: ");
	return In.nextInt();
    }

    private String readrentgame() {
	System.out.print("Enter the title of the game you wish to rent: ");
	return In.nextLine();
    }

    private String readreturngame() {
	System.out.print("Enter the title of the game you wish to return: ");
	return In.nextLine();
    }

    private int readvalidID() {
	System.out.print("\n" + "Enter a valid customer ID: ");
	return In.nextInt();
    }

    public void listgames() {
	System.out.println("\n" + "The Kiosk has the following games:");
	for (Game game : gamesAvailable) {
            System.out.println(game);
	}
	System.out.print("\n");
    }

    public void addgame() {       // START ADMIN METHODS
	System.out.println("\n" + "Adding a new game.");
	String title = readtitle();
	int year = readyear();
	String genrename = readgenre1();
	int price = readprice();
	Game game = findgame(title, year);
	if (game != null) {
            System.out.println("The game is already in the catalogue.");
	}
	else {
            gamesAvailable.add(new Game (title, year, price, genrename));
            System.out.println("Added " + title + " to catalogue." + "\n");
	}
	Genre genre = findgenre(genrename);
	if (genre == null) {
            genres.add(new Genre(genrename));
	}
    }

    public void removegame() { 
	System.out.println("\n" + "Removing a game.");
	String title = readtitle();
	int year = readyear();
	Game game = findgame(title, year);
	if (game != null) {
            System.out.println(game + " removed from catalogue." + "\n");
            gamesAvailable.remove(game);
	}
	else {
            System.out.println("No such game found." + "\n");
	}
    }                                      // END ADMIN METHODS
    
	
    private void displaygames() {}       // START CATALOGUE METHODS
	
	
    public void availablegames() {        
        System.out.print("\n");
	System.out.println("The following games are available:");
	for (Game game : gamesAvailable) {
            System.out.println(game);
	}
        System.out.print("\n");
    }

    public void displaygenres() {
	System.out.print("\n");
        System.out.println("The Kiosk has games in the following genres:");
	for (Genre genre : genres) {
            System.out.println(genre);
	}
	System.out.print("\n");
    }

    public void gamesingenre() {
	System.out.print("\n");
	String genrename = readgenre2();
	System.out.println("The kiosk has the following games in that genre:");
	for (Game game : gamesAvailable) {
            if (game.hasgenre(genrename)) {
		System.out.println(game);
            }
        }
	System.out.print("\n");
    }

    public void gamesbyyear() {
	System.out.print("\n");
	int year = readyear();
	System.out.println("The kiosk has the following games by that year:");
	for (Game game : gamesAvailable) {
            if (game.hasyear(year)) {
		System.out.println(game);
            }
	}
	System.out.print("\n");
    }

    public Game rentgame(int balance) {
	String name = readrentgame();
	Game game = findgametitle(name);
	if (game != null) {
            if (game.getprice() > balance) {
		System.out.println("You don't have sufficient funds to rent this game." + "\n");
            }
            else {
                gamesRented.add(game);
                gamesAvailable.remove(game);
                System.out.println("Game rented." + "\n");
                return game;
            }			
	}
	else {
            System.out.println("That game is not available or doesn't exist." + "\n");
	}
	return null;
    }
	
    public Game returngame() {
	String name = readreturngame();
	for (Game game : gamesRented) {
            if (game.hastitle(name)) {
		gamesAvailable.add(game);
		gamesRented.remove(game);
		System.out.println(name + " has been returned." + "\n");
		return game;
            }
	}
	return null;
    }              


    private Game findgame(String title, int year) {
	for (Game game : gamesAvailable) {
            if (game.hasGame(title, year)) {
                return game;
            }
	}
	return null;
    }

    private Genre findgenre(String genrename) {
	for (Genre genre : genres) {
            if (genre.hasname(genrename)) {
		return genre;
            }
	}
	return null;
    }

    private Game findgametitle(String title) {
	for (Game game : gamesAvailable) {
            if (game.hastitle(title)) {
		return game;
            }
	}
	return null;
    }

    private void cataloguehelp() {
	System.out.println("Please enter a number between 1 and 7 or press R to return to the previous menu.");
    }
}
