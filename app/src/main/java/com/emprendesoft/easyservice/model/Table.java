package com.emprendesoft.easyservice.model;

public class Table {

    private int number;

    public Table(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public String toString() {

        return  "Mesa No. " + Integer.toString(this.number);
    }
}
