package com.emprendesoft.easyservice.model;

public class Food {

    private String name;
    private int imageId;
    private float price;
    private String[] allergens;
    private String note;

    public Food(String name, int imageId, float price, String[] allergens) {
        this.name = name;
        this.imageId = imageId;
        this.price = price;
        this.allergens = allergens;
    }

    public String getName() {
        return name;
    }

    public int getImageId() {
        return imageId;
    }

    public float getPrice() {
        return price;
    }

    public String[] getAllergens() {
        return allergens;
    }
}
