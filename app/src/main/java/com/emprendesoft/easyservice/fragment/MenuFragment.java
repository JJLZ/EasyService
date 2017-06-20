package com.emprendesoft.easyservice.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.emprendesoft.easyservice.R;
import com.emprendesoft.easyservice.adapter.MenuRecyclerViewAdapter;
import com.emprendesoft.easyservice.model.Food;

import java.util.LinkedList;


public class MenuFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private View mView;

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

        //--now Fake Data --
        LinkedList<Food> foods = new LinkedList<>();
        foods.add(new Food("Enchiladas Suizas", "https://emprendesoft.com/wp-content/uploads/2017/06/enchiladas-suizas.jpg", 22, new String[]{"milk", "eggs", "fish", "crustaceans", "peanut"}));
        //--

        //-- Set adapter  --
        MenuRecyclerViewAdapter adapter = new MenuRecyclerViewAdapter(foods);
        mRecyclerView.setAdapter(adapter);
        //--

        return mView;
    }

}
