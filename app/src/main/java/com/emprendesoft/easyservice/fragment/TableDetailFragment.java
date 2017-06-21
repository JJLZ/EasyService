package com.emprendesoft.easyservice.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.emprendesoft.easyservice.R;
import com.emprendesoft.easyservice.activity.MenuActivity;
import com.emprendesoft.easyservice.model.Food;
import com.emprendesoft.easyservice.model.Table;

public class TableDetailFragment extends Fragment {

    public static final String ARG_TABLE = "com.emprendesoft.easyservice.TableDetailFragment.ARG_TABLE";

    private ActionBar mActionBar = null;
    private Table mTable = null;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Enable back button
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View root = inflater.inflate(R.layout.fragment_table_detail, container, false);

        //-- Toolbar title with table number from arguments --
        mActionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        TextView txtTitle = (TextView) getActivity().findViewById(R.id.toolbar_title);

        mActionBar.setTitle("Platos");

        //-- Get Table from Argument --
        if (getArguments() != null) {
            mTable = (Table) getArguments().getSerializable(ARG_TABLE);

            txtTitle.setText(mTable.toString());
        }

        // Enable back button
        mActionBar.setDisplayHomeAsUpEnabled(true);

        // Color back button
        final Drawable upArrow = ContextCompat.getDrawable(getActivity(), R.drawable.abc_ic_ab_back_material);
        upArrow.setColorFilter(Color.parseColor("#FFFFFF"), PorterDuff.Mode.SRC_ATOP);
        mActionBar.setHomeAsUpIndicator(upArrow);
        //--

        ListView listView = (ListView) root.findViewById(R.id.list_table_detail);

        CustomAdapter customAdapter = new CustomAdapter();
        listView.setAdapter(customAdapter);

        //-- FloatingActionButton --
        FloatingActionButton fab = (FloatingActionButton) root.findViewById(R.id.fragment_table_detail__floating_button);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //-- Open Menu Activity --
                Intent intent = new Intent(getActivity(), MenuActivity.class);
                startActivity(intent);
                //--
            }
        });
        //--

        return root;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        boolean superValue = super.onOptionsItemSelected(item);

        // Enable back button
        if (item.getItemId() == android.R.id.home) {

            getActivity().finish();

            return true;
        }

        return superValue;
    }

    class CustomAdapter extends BaseAdapter {

        @Override
        public int getCount() {

            return mTable.getFood().size();
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

            convertView = getActivity().getLayoutInflater().inflate(R.layout.custom_layout_table_detail, null);


            ImageView imageView = (ImageView) convertView.findViewById(R.id.imageView);
            TextView textView_name = (TextView) convertView.findViewById(R.id.textView_name);
            TextView textView_description = (TextView) convertView.findViewById(R.id.textView_description);

            Food food = (Food) mTable.getFood().get(position);

//            imageView.setImageResource(IMAGES[position]);
            textView_name.setText(food.getName());
//            textView_description.setText(DESCRIPTIONS[position]);

            return convertView;
        }
    }
}






























