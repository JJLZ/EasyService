package com.emprendesoft.easyservice.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.emprendesoft.easyservice.R;
import com.emprendesoft.easyservice.activity.TableDetailActivity;
import com.emprendesoft.easyservice.model.Table;
import com.emprendesoft.easyservice.model.Tables;

public class TableListFragment extends Fragment {

    public Tables mTables = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        // Create table list
        mTables = Tables.getInstance();

        // inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_table_list, container, false);

        // create ListView
        final ListView list = (ListView) root.findViewById(R.id.list_tables);

        // Adapter with table list
        ArrayAdapter<Table> adapter = new ArrayAdapter<Table>(

                getActivity(),
                android.R.layout.simple_list_item_1,
                mTables.getTables()
        );

        // set the adapter to the list
        list.setAdapter(adapter);

        // Set listener to detected items selected
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                FragmentManager fm = getFragmentManager();
                TableDetailFragment tableDetailFragment = (TableDetailFragment) fm.findFragmentById(R.id.table_detail_container);

                if (tableDetailFragment != null) {

                    tableDetailFragment.moveToTable(position);
                } else {

                    Intent intent = new Intent(getActivity(), TableDetailActivity.class);
                    intent.putExtra(TableDetailActivity.EXTRA_TABLE_INDEX, position);

                    startActivity(intent);
                }
            }
        });

        return root;
    }
}






























