package com.emprendesoft.easyservice.activity;

import android.support.v4.app.Fragment;

import com.emprendesoft.easyservice.fragment.TableListFragment;

public class TableListActivity extends FragmentContainerActivity {

    @Override
    protected Fragment createFragment() {

        TableListFragment fragment = new TableListFragment();

        return fragment;
    }
}
