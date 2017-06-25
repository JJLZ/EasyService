package com.emprendesoft.easyservice.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.emprendesoft.easyservice.R;
import com.emprendesoft.easyservice.activity.MenuActivity;
import com.emprendesoft.easyservice.adapter.MenuRecyclerViewAdapter;
import com.emprendesoft.easyservice.model.Food;
import com.emprendesoft.easyservice.model.Menu;
import com.emprendesoft.easyservice.model.Tables;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedList;


public class MenuFragment extends Fragment {

    public static final String ARG_TABLE_INDEX = "com.emprendesoft.easyservice.MenuFragment.ARG_TABLE_INDEX";

    private RecyclerView mRecyclerView;
    private ActionBar mActionBar = null;
    private View mView;
    private int tableIndex;
    private Tables mTables = null;
    private Menu mMenu = null;
    private ProgressBar mProgressBar = null;

    public static MenuFragment newInstance(int tableIndex) {

        MenuFragment fragment = new MenuFragment();

        Bundle arguments = new Bundle();
        arguments.putInt(ARG_TABLE_INDEX, tableIndex);

        fragment.setArguments(arguments);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Enable back button
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.fragment_menu, container, false);

        //-- ProgressBar --
        mProgressBar = (ProgressBar) mView.findViewById(R.id.fragment_menu__progress_bar);
        //--

        //-- Toolbar setup --
        mActionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        mActionBar.setTitle("Menu");
        mActionBar.setDisplayHomeAsUpEnabled(true);
        //--

        mRecyclerView = (RecyclerView) mView.findViewById(R.id.fragment_menu__recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mTables = Tables.getInstance();

        //-- Get Table from Argument --
        if (getArguments() != null) {
            tableIndex = getArguments().getInt(ARG_TABLE_INDEX);
        }
        //--

        //-- Get Menu --
        mMenu = Menu.getInstance();
        if (mMenu.getFoods().size() == 0) {

            //-- Download data from server --
            String strURL = getString(R.string.menu_url);
            new DownloadMenuTask().execute(strURL);
            //--
        } else {

            recyclerViewSetup();
        }
        //--

        return mView;
    }

    private void recyclerViewSetup() {

        //-- Set adapter  --
        MenuRecyclerViewAdapter adapter = new MenuRecyclerViewAdapter(mMenu.getFoods());
        mRecyclerView.setAdapter(adapter);
        //--

        //-- OnClickListener Setup --
        adapter.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                int position = mRecyclerView.getChildAdapterPosition(v);
                Food food = mMenu.getFoods().get(position);
                mTables.getTable(tableIndex).addOrder(food);

                exitFromMenu();
            }
        });
        //--
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case android.R.id.home:

                getActivity().finish();
                return true;

            default:
                break;
        }

        return false;
    }

    private void exitFromMenu() {

        if (getActivity().findViewById(R.id.table_detail_container) != null) {  // table landscape

            FragmentManager fm = getActivity().getSupportFragmentManager();
            Fragment tableDetailFragment = TableDetailFragment.newInstance(tableIndex);
            fm.beginTransaction().replace(R.id.table_detail_container, tableDetailFragment).commit();
        }
        else {  // phone

            Intent intent = new Intent();
            intent.putExtra(MenuActivity.EXTRA_TABLE_INDEX, tableIndex);

            getActivity().setResult(Activity.RESULT_OK, intent);
            getActivity().finish();
        }
    }

    private LinkedList<Food> downloadMenu(String strURL) {

        URL url;
        InputStream input;

        try {
            url = new URL(strURL);

            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.connect();
            byte data[] = new byte[1024];
            int downloadedBytes;
            input = con.getInputStream();

            StringBuilder sb = new StringBuilder();
            while ((downloadedBytes = input.read(data)) != -1) {
                sb.append(new String(data, 0, downloadedBytes));
            }

            // Analizamos los datos para convertirlos de JSON a algo que podamos manejar en código
            JSONObject jsonRoot = new JSONObject(sb.toString());
            JSONArray list = jsonRoot.getJSONArray("foods");

            // Nos descargamos todos los días de la predicción
            LinkedList<Food> foods = new LinkedList<>();

            for (int i = 0; i < list.length(); i++) {

                JSONObject item = list.getJSONObject(i);

                String name = item.getString("name");
                String urlImage = item.getString("image");
                String[] allergens = item.getString("allergens").split("\\s*,\\s*");
                float price = (float) item.getDouble("price");

                Food food = new Food(name, urlImage, price, allergens);
                foods.add(food);
            }

            //-- How do a delay --
            Thread.sleep(2000);
            //--

            return foods;

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    private class DownloadMenuTask extends AsyncTask<String, Integer, LinkedList<Food>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            //-- Progressbar --
            mProgressBar.setVisibility(View.VISIBLE);
            //--
        }

        @Override
        protected LinkedList<Food> doInBackground(String... params) {

            return downloadMenu(params[0]);
        }

        @Override
        protected void onPostExecute(LinkedList<Food> foods) {
            super.onPostExecute(foods);

            mMenu.setFoods(foods);
            recyclerViewSetup();
            //-- Progressbar --
            mProgressBar.setVisibility(View.GONE);
            //--
        }
    }
}






























