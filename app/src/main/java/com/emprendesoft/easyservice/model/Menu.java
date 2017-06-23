package com.emprendesoft.easyservice.model;

import java.util.LinkedList;

public class Menu {

    private static Menu mInstance;
    private static LinkedList<Food> mFoods;

    public static Menu getInstance() {

        if (mInstance == null) {
            mInstance = new Menu();
            mFoods = new LinkedList<>();
        }

        return mInstance;
    }

    public LinkedList<Food> getFoods() {
        return mFoods;
    }

    public Food getFood(int index) {
        return mFoods.get(index);
    }

    public Menu setFoods(LinkedList<Food> foods) {
        mFoods = foods;
        return this;
    }
}
