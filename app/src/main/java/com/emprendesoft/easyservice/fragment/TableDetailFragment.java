package com.emprendesoft.easyservice.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.emprendesoft.easyservice.R;
import com.emprendesoft.easyservice.activity.MenuActivity;
import com.emprendesoft.easyservice.model.Food;
import com.emprendesoft.easyservice.model.Table;
import com.emprendesoft.easyservice.model.Tables;
import com.squareup.picasso.Picasso;

import java.util.LinkedList;

public class TableDetailFragment extends Fragment {

    public static final String ARG_TABLE_INDEX = "com.emprendesoft.easyservice.TableDetailFragment.ARG_TABLE_INDEX";
    private static int mInitialTableIndex = 0;

    ListView listView = null;
    CustomAdapter customAdapter = null;
    Tables mTables = null;
    private int tableIndex = 0;
    private Table mTable = null;
    private ActionBar mActionBar = null;
    private TextView mTextViewTitle = null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Enable back button
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        getActivity().getMenuInflater().inflate(R.menu.menu_orders, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.action_clear_table:

                mTable.setOrders(new LinkedList());
                customAdapter.notifyDataSetChanged();

                return true;

            case android.R.id.home:

                getActivity().finish();
                return true;

            default:
                break;
        }

        return false;
    }

    public static TableDetailFragment newInstance(int initialTable) {

        TableDetailFragment fragment = new TableDetailFragment();

        Bundle arguments = new Bundle();
        arguments.putInt(ARG_TABLE_INDEX, initialTable);

        fragment.setArguments(arguments);

        return fragment;
    }

    public void moveToTable(int index) {

        mTable = mTables.getTable(index);
        customAdapter.notifyDataSetChanged();
        mTextViewTitle.setText(mTable.toString());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View root = inflater.inflate(R.layout.fragment_table_detail, container, false);

        //-- Toolbar Setup --
        mActionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        mActionBar.setTitle("");
        mTextViewTitle = (TextView) getActivity().findViewById(R.id.toolbar_title);
        // --

        // Back button
        if (getActivity().findViewById(R.id.table_detail_container) != null) {  // Tablet
            mActionBar.setDisplayHomeAsUpEnabled(false);
        } else {    // Phone
            mActionBar.setDisplayHomeAsUpEnabled(true);
        }

        //-- Get table index from Argument --
        if (getArguments() != null) {

            tableIndex = getArguments().getInt(ARG_TABLE_INDEX, mInitialTableIndex);
            mTables = Tables.getInstance();
            mTable = mTables.getTable(tableIndex);

            mTextViewTitle.setText(mTable.toString());
        }

        listView = (ListView) root.findViewById(R.id.list_table_detail);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

                if (position != mTable.getOrders().size()) {

                    askForTheNote(position);
                }
            }
        });

        customAdapter = new CustomAdapter();
        listView.setAdapter(customAdapter);

        //-- FloatingActionButton --
        FloatingActionButton fab = (FloatingActionButton) root.findViewById(R.id.fragment_table_detail__floating_button);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Open Menu Screen

                // We are using a table in landscape
                if (getActivity().findViewById(R.id.table_detail_container) != null)
                {
                    //-- Replace table detail fragment with menu fragment --
                    FragmentManager fm = getActivity().getSupportFragmentManager();
                    Fragment menuFragment = MenuFragment.newInstance(tableIndex);
                    fm.beginTransaction().replace(R.id.table_detail_container, menuFragment, "MENU").commit();
                    //--
                }
                else  // We are using a phone
                {
                    //-- Open Menu Activity --
                    Intent intent = new Intent(getActivity(), MenuActivity.class);
                    intent.putExtra(MenuActivity.EXTRA_TABLE_INDEX, tableIndex);
                    startActivityForResult(intent, 1);
                    //--
                }
            }
        });
        //--

        return root;
    }

    private void askForTheNote(final int position) {

        final Food food = (Food) mTable.getOrders().get(position);

        AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
        alert.setTitle(food.getName());
        alert.setMessage("Agregar nota:");
        final EditText input = new EditText(getActivity());
        alert.setView(input);
        alert.setPositiveButton("Listo", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int whichButton) {

                food.setNote(input.getText().toString());
                customAdapter.notifyDataSetChanged();
            }

        });
        alert.setNegativeButton("Cancelar", null);
        alert.show();
    }

    @Override
    public void onResume() {
        super.onResume();

        customAdapter.notifyDataSetChanged();
    }


    class CustomAdapter extends BaseAdapter {

        @Override
        public int getCount() {

            return mTable.getOrders().size() + 1;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if (position == mTable.getOrders().size()) {

                convertView = getActivity().getLayoutInflater().inflate(R.layout.custom_total_row, null);

                TextView total = (TextView) convertView.findViewById(R.id.custom_total_row__total);

                float totalCheck = mTable.getCheck();

                if (totalCheck == 0f) {
                    total.setText("No hay órdenes para esta mesa");
                } else {
                    total.setText("Cuenta Total: " + String.format("$%.2f", totalCheck));
                }

            } else {
                convertView = getActivity().getLayoutInflater().inflate(R.layout.custom_layout_table_detail, null);

                ImageView image = (ImageView) convertView.findViewById(R.id.custom_layout_table_detail__imageView);
                TextView name = (TextView) convertView.findViewById(R.id.custom_layout_table_detail__name);
                TextView note = (TextView) convertView.findViewById(R.id.custom_layout_table_detail__note);
                TextView price = (TextView) convertView.findViewById(R.id.custom_layout_table_detail__price);

                Food food = (Food) mTable.getOrders().get(position);

                // Imagen using Picasso
                String imagenURL = food.getImagenURL();
                Picasso.with(getActivity()).
                        load(imagenURL).
                        placeholder(R.drawable.food_placeholder).
                        into(image);

                name.setText(food.getName());
                price.setText(String.format("$%.2f", food.getPrice()));

                if (food.getNote() != null) {
                    note.setText(food.getNote());
                } else {
                    note.setText("Tap para agregar nota");
                }
            }

            return convertView;
        }
    }
}






























