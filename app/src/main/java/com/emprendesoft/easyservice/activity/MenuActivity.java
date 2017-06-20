package com.emprendesoft.easyservice.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.emprendesoft.easyservice.fragment.MenuFragment;

public class MenuActivity extends FragmentContainerActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected Fragment createFragment() {

        MenuFragment fragment = new MenuFragment();

        return fragment;
    }
}
