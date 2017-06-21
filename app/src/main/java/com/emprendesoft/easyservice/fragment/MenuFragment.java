package com.emprendesoft.easyservice.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.emprendesoft.easyservice.R;
import com.emprendesoft.easyservice.activity.MenuActivity;
import com.emprendesoft.easyservice.adapter.MenuRecyclerViewAdapter;
import com.emprendesoft.easyservice.model.Food;
import com.emprendesoft.easyservice.model.Tables;

import java.util.LinkedList;


public class MenuFragment extends Fragment {

    public static final String ARG_TABLE_INDEX = "com.emprendesoft.easyservice.MenuFragment.ARG_TABLE_INDEX";

    private RecyclerView mRecyclerView;
    private View mView;
    private int tableIndex;
    Tables mTables = null;
    LinkedList<Food> menuFoods = new LinkedList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.fragment_menu, container, false);

        mRecyclerView = (RecyclerView) mView.findViewById(R.id.fragment_menu__recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mTables = Tables.getInstance();

        //-- Get Table from Argument --
        if (getArguments() != null) {
            tableIndex = getArguments().getInt(ARG_TABLE_INDEX);
        }
        //--

        //--now Fake Data --
        menuFoods.add(new Food("Enchiladas Suizas", "https://emprendesoft.com/wp-content/uploads/2017/06/enchiladas-suizas.jpg", 22, new String[]{"milk", "eggs"}));
        menuFoods.add(new Food("Pozole", "https://emprendesoft.com/wp-content/uploads/2017/06/pozole.jpg", 10, new String[]{"peanut"}));
        //--

        //-- Set adapter  --
        MenuRecyclerViewAdapter adapter = new MenuRecyclerViewAdapter(menuFoods);
        mRecyclerView.setAdapter(adapter);
        //--

        //-- OnClickListener Setup --
        adapter.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                int position = mRecyclerView.getChildAdapterPosition(v);
                Food food = menuFoods.get(position);
                mTables.getTable(tableIndex).addOrder(food);

                exitFromMenu();
            }
        });
        //--

        return mView;
    }

    private void exitFromMenu() {

        Intent intent = new Intent();
        intent.putExtra(MenuActivity.EXTRA_TABLE_INDEX, tableIndex);

        getActivity().setResult(Activity.RESULT_OK, intent);
        getActivity().finish();
    }
}






























