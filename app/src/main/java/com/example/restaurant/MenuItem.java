package com.example.restaurant;

import java.io.Serializable;

public class MenuItem implements Serializable {

    private String name;
    private String description;
    private String imageUrl;
    private int price;
    private String category;

    public MenuItem(String name, String description, String imageUrl, int price, String category) {
        super();
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.price = price;
        this.category = category;
    }

    // list of getters
    public String getName() { return this.name; }
    public String getDescription() {return this.description; }
    public String getImageUrl() {return this.imageUrl; }
    public int getPrice() {return this.price; }
    public String getCategory() {return this.category; }

    // list of setters
    public void setName(String name) {
        this.name = name;
    }
    public void setDescription(String description) { this.description = description; }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    public void setCategory(String category) {
        this.category = category;
    }
}
