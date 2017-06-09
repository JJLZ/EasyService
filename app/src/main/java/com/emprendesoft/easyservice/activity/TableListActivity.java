package com.emprendesoft.easyservice.activity;

import android.support.v4.app.Fragment;

import com.emprendesoft.easyservice.fragment.TableListFragment;

public class TableListActivity extends FragmentContainerActivity {

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_table_list);
//    }

    @Override
    protected Fragment createFragment() {

        TableListFragment fragment = new TableListFragment();

        return fragment;
    }
}
