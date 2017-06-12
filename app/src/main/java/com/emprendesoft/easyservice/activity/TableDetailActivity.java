package com.emprendesoft.easyservice.activity;

import android.support.v4.app.Fragment;

import com.emprendesoft.easyservice.fragment.TableDetailFragment;

public class TableDetailActivity extends FragmentContainerActivity {


    @Override
    protected Fragment createFragment() {

        TableDetailFragment fragment = new TableDetailFragment();

        return fragment;
    }
}
