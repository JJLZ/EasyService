package com.emprendesoft.easyservice.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Menu;
import android.view.MenuItem;

import com.emprendesoft.easyservice.R;
import com.emprendesoft.easyservice.fragment.TableDetailFragment;

public class TableDetailActivity extends FragmentContainerActivity {

    public static final String EXTRA_TABLE_INDEX = "com.emprendesoft.easyservice.TableDetailActivity.EXTRA_TABLE_INDEX";
    int tableIndex;

    @Override
    protected Fragment createFragment() {

        TableDetailFragment fragment = new TableDetailFragment();

        // Get table number from the Intent
        Bundle bundleExtras = getIntent().getExtras();
        if (bundleExtras != null) {
            this.tableIndex = getIntent().getIntExtra(this.EXTRA_TABLE_INDEX, 0);

            // Sent table as argument to the fragment
            Bundle bundleArg = new Bundle();
            bundleArg.putInt(TableDetailFragment.ARG_TABLE_INDEX, this.tableIndex);
            fragment.setArguments(bundleArg);
            //--
        }

        return fragment;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // TODO: ver por qué no esta funcionado esto
        if (requestCode == 1) {

            if (resultCode == RESULT_OK) {

                this.tableIndex = data.getIntExtra(MenuActivity.EXTRA_TABLE_INDEX, 0);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        getMenuInflater().inflate(R.menu.menu_orders, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}






























