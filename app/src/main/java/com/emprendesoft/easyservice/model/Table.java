package com.emprendesoft.easyservice.model;

import java.io.Serializable;
import java.util.LinkedList;

public class Table implements Serializable {

    private int number;
    private LinkedList Food;
    private float check;

    public Table(int number) {

        this.number = number;
        Food = new LinkedList();
    }

    public LinkedList getFood() {
        return Food;
    }

    public Table setFood(LinkedList food) {
        Food = food;
        return this;
    }

    @Override
    public String toString() {

        return  "Mesa No. " + Integer.toString(this.number);
    }
}
