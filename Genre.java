/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author vinay
 */
public class Genre {
    
    private String name;
			
    public Genre(String name) {
	this.name = name;
    }

    public String getgenrename() {
        return this.name;
    }

    public boolean hasname(String name) {
	return (this.name.equals(name));
    }

    @Override
    public String toString() {
	return this.name;
    }
}
