package com.emprendesoft.easyservice.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.emprendesoft.easyservice.R;
import com.emprendesoft.easyservice.model.Table;

import java.util.LinkedList;

public class TableListFragment extends Fragment {

    protected static LinkedList<Table> mTables;
    private static final int numberOfTables = 10;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        // Create table list
        mTables = new LinkedList<>();

        for (int i = 0; i < numberOfTables; i++) {

            mTables.add(new Table(i + 1));
        }

        // inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_table_list, container, false);

        // create ListView
        ListView list = (ListView) root.findViewById(R.id.list_tables);

        // Adapter with table list
        ArrayAdapter<Table> adapter = new ArrayAdapter<Table>(

                getActivity(),
                android.R.layout.simple_list_item_1,
                mTables
        );

        // set the adapter to the list
        list.setAdapter(adapter);

        // Set listener to detected items selected
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

//                // Avisamos al listener, si lo tenemos, que el usuario ha seleccionado una ciudad
//                if (mOnCitySelectedListener != null) {
//
//                    City selectedCity = mCities.get(position);
//                    // Aviso al listener
//                    mOnCitySelectedListener.onCitySelected(selectedCity, position);
//                }
            }
        });

        return root;
    }
}
