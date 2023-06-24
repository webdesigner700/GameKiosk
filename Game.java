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

public class Game {
    
    private String title; 
    private int year; 
    private int price; 
    private Genre genre;

    public Game(String title, int year, int price, String genrename) {
	this.title = title;
	this.year = year;
	this.price = price;
	this.genre = new Genre(genrename);
    }	

    public boolean hasGame(String title, int year) {
	return ((this.title.equals(title)) && (this.year == year));
    }

    public boolean hastitle(String title) {
	return (this.title.equals(title));
    }

    public boolean hasyear(int year) {
	return (this.year == year);
    }

    public boolean hasgenre(String genrename) {
        return (this.genre.getgenrename().equals(genrename));
    }

    public int getprice() {
	return this.price;
    }

    @Override
	public String toString() {
	return this.year + "\t" + this.title + "\t" + this.genre.getgenrename() + "\t" + "$" + this.price;
    }
}
