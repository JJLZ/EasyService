package com.emprendesoft.easyservice.model;

import java.io.Serializable;
import java.util.LinkedList;

public class Table implements Serializable {

    private int number;
    private LinkedList orders;
    private float check;

    public Table(int number) {

        this.number = number;
        orders = new LinkedList();
    }

    public LinkedList getOrders() {
        return orders;
    }

    public Table setOrders(LinkedList orders) {
        this.orders = orders;
        return this;
    }

    public void addOrder(Food food) {

        this.orders.add(food);
    }

    public int getNumber() {
        return number;
    }

    public float getCheck() {

        float total = 0;

        for (int i = 0; i < orders.size(); i++) {

            Food food = (Food) orders.get(i);
            float price = food.getPrice();

            total = total + price;
        }

        return total;
    }

    @Override
    public String toString() {

        return  "Mesa No. " + Integer.toString(this.number + 1);
    }
}