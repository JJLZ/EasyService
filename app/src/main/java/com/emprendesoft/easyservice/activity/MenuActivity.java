package com.emprendesoft.easyservice.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.emprendesoft.easyservice.fragment.MenuFragment;

public class MenuActivity extends FragmentContainerActivity {

    public static final String EXTRA_TABLE_INDEX = "com.emprendesoft.easyservice.MenuActivity.EXTRA_TABLE_INDEX";
    int tableIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected Fragment createFragment() {

        MenuFragment fragment = new MenuFragment();

        //-- Sending arguments with selected table --

        // Get table number from the Intent
        Bundle bundleExtras = getIntent().getExtras();
        if (bundleExtras != null) {
            tableIndex = bundleExtras.getInt(this.EXTRA_TABLE_INDEX, 0);

            // Sent table as argument to the fragment
            Bundle bundleArg = new Bundle();
            bundleArg.putInt(MenuFragment.ARG_TABLE_INDEX, tableIndex);
            fragment.setArguments(bundleArg);
            //--
        }
        //--

        return fragment;
    }
}
