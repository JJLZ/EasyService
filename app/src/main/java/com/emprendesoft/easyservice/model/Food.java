package com.emprendesoft.easyservice.model;

import java.io.Serializable;

public class Food implements Serializable {

    private String name;
    private String imagenURL;
    private float price;
    // milk, egg, fish, crustaceans, nuts, peanut, wheat
    private String[] allergens;
    private String note;

    public Food(String name, String imagenURL, float price, String[] allergens) {
        this.name = name;
        this.imagenURL = imagenURL;
        this.price = price;
        this.allergens = allergens;
    }

    public String getNote() {
        return note;
    }

    public Food setName(String name) {
        this.name = name;
        return this;
    }

    public Food setImagenURL(String imagenURL) {
        this.imagenURL = imagenURL;
        return this;
    }

    public Food setPrice(float price) {
        this.price = price;
        return this;
    }

    public Food setAllergens(String[] allergens) {
        this.allergens = allergens;
        return this;
    }

    public Food setNote(String note) {
        this.note = note;
        return this;
    }

    public String getName() {
        return name;
    }

    public String getImagenURL() {
        return imagenURL;
    }

    public float getPrice() {
        return price;
    }

    public String[] getAllergens() {
        return allergens;
    }
}
