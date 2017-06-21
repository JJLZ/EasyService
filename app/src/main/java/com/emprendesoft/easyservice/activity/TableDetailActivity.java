package com.emprendesoft.easyservice.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.emprendesoft.easyservice.fragment.TableDetailFragment;
import com.emprendesoft.easyservice.model.Table;

public class TableDetailActivity extends FragmentContainerActivity {

    public static final String EXTRA_TABLE = "com.emprendesoft.easyservice.TableDetailActivity.EXTRA_TABLE";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected Fragment createFragment() {

        TableDetailFragment fragment = new TableDetailFragment();

        //-- Sending arguments with selected table --
        Table table = null;

        // Get table number from the Intent
        Bundle bundleExtras = getIntent().getExtras();
        if (bundleExtras != null) {
            table = (Table) bundleExtras.getSerializable(EXTRA_TABLE);

            // Sent table as argument to the fragment
            Bundle bundleArg = new Bundle();
            bundleArg.putSerializable(TableDetailFragment.ARG_TABLE, table);
            fragment.setArguments(bundleArg);
            //--
        }

        return fragment;
    }
}
