package com.emprendesoft.easyservice.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.emprendesoft.easyservice.R;
import com.emprendesoft.easyservice.fragment.TableDetailFragment;
import com.emprendesoft.easyservice.fragment.TableListFragment;

public class TableListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_table_list);

        setSupportActionBar((Toolbar) findViewById(R.id.toolBar));

        FragmentManager fm = getSupportFragmentManager();

        // For phone and tablet
        if (findViewById(R.id.list_container) != null) {
            Fragment listFragment = fm.findFragmentById(R.id.list_container);
            if (listFragment == null) {
                listFragment = new TableListFragment();
                fm.beginTransaction().add(R.id.list_container, listFragment).commit();
            }
        }

        // For tablet landscape only
        if (findViewById(R.id.table_detail_container) != null) {
            Fragment tableDetailFragment = fm.findFragmentById(R.id.table_detail_container);
            if (tableDetailFragment == null) {
                tableDetailFragment = TableDetailFragment.newInstance(0);
                fm.beginTransaction().add(R.id.table_detail_container, tableDetailFragment, "DETAIL").commit();
            }
        }
    }
}
