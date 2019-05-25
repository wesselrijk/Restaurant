package com.example.restaurant;
/**
 * The MenuItem class for the app.
 * This is the class that holds the information for any menu 9item. This includes the name,
 * the description, the price and category as well as the url referring to the image of the item.
 */

import java.io.Serializable;

public class MenuItem implements Serializable {

    private String name;
    private String description;
    private String imageUrl;
    private int price;
    private String category;

    // The constructor calls super and sets the variables.
    public MenuItem(String name, String description, String imageUrl, int price, String category) {
        super();
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.price = price;
        this.category = category;
    }

    // List of getters.
    public String getName() { return this.name; }
    public String getDescription() {return this.description; }
    public String getImageUrl() {return this.imageUrl; }
    public int getPrice() {return this.price; }
    public String getCategory() {return this.category; }
}
