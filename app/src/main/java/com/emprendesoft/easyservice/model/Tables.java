package com.emprendesoft.easyservice.model;

import java.util.LinkedList;

public class Tables {

    private static Tables mInstance;
    private LinkedList<Table> mTables;

    public static Tables getInstance() {

        if (mInstance == null) {
            mInstance = new Tables();
        }

        return mInstance;
    }

    public LinkedList<Table> getTables() {
        return mTables;
    }

    public Tables() {

        mTables = new LinkedList<>();

        int numberOfTables = 10;

        for (int i = 0; i < numberOfTables; i++) {

            mTables.add(new Table(i));
        }
    }

    public Table getTable(int index) {

        return mTables.get(index);
    }

    public int getCount() {

        return mTables.size();
    }
}
