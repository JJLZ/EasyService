package com.emprendesoft.easyservice.model;

import java.util.LinkedList;

public class Table {

    private int number;
    private LinkedList Food;
    private float check;

    public Table(int number, LinkedList food) {
        this.number = number;
        Food = food;
    }

    public Table(int number) {

        this.number = number;
    }

    @Override
    public String toString() {

        return  "Mesa No. " + Integer.toString(this.number);
    }
}
