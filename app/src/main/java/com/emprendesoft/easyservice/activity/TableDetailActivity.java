package com.emprendesoft.easyservice.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.emprendesoft.easyservice.fragment.TableDetailFragment;

public class TableDetailActivity extends FragmentContainerActivity {

    public static final String EXTRA_TABLE = "com.emprendesoft.easyservice.TableDetailActivity.EXTRA_TABLE";
    private String strMesaNumber = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected Fragment createFragment() {

        TableDetailFragment fragment = new TableDetailFragment();

        //-- Sending arguments with table number --

        // Get table number from the Intent
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            strMesaNumber = extras.getString(EXTRA_TABLE);
        }

        // Sent table number as argument to the fragment
        Bundle bundle = new Bundle();
        bundle.putString(TableDetailFragment.ARG_TABLE, strMesaNumber);
        fragment.setArguments(bundle);

        //--

        return fragment;
    }
}
