package com.emprendesoft.easyservice.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.emprendesoft.easyservice.R;
import com.emprendesoft.easyservice.activity.TableDetailActivity;
import com.emprendesoft.easyservice.model.Tables;

public class TableListFragment extends Fragment {

    public Tables mTables = null;
    private ListView listView = null;
    private CustomAdapter customAdapter = null;
    private ActionBar mActionBar = null;
    private TextView mTextViewTitle = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        //-- Toolbar Setup --
        mActionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        mActionBar.setTitle("");
        mTextViewTitle = (TextView) getActivity().findViewById(R.id.toolbar_title);

        // Title
        if (getActivity().findViewById(R.id.table_detail_container) != null) {  // Tablet
            mTextViewTitle.setText("");
        } else {    // Phone
            mTextViewTitle.setText("Seleccione Mesa");
        }
        // --

        // Create table listView
        mTables = Tables.getInstance();

        // inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_table_list, container, false);

        // create ListView
        listView = (ListView) root.findViewById(R.id.list_tables);

        customAdapter = new CustomAdapter();
        listView.setAdapter(customAdapter);

        // Set listener to detected items selected
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (getActivity().findViewById(R.id.table_detail_container) != null) {

                    FragmentManager fm = getFragmentManager();
                    Fragment fragment = fm.findFragmentById(R.id.table_detail_container);
                    String className = fragment.getClass().getSimpleName();

                    if (className.equals(TableDetailFragment.class.getSimpleName())) {

                        TableDetailFragment tableDetailFragment = (TableDetailFragment) fm.findFragmentById(R.id.table_detail_container);
                        tableDetailFragment.moveToTable(position);

                    } else if (className.equals(MenuFragment.class.getSimpleName())) {

                        Fragment tableDetailFragment = TableDetailFragment.newInstance(position);
                        fm.beginTransaction().replace(R.id.table_detail_container, tableDetailFragment, "DETAIL").commit();
                    }
                } else {

                    Intent intent = new Intent(getActivity(), TableDetailActivity.class);
                    intent.putExtra(TableDetailActivity.EXTRA_TABLE_INDEX, position);

                    startActivity(intent);
                }
            }
        });

        return root;
    }

//    @Override
//    public void onResume() {
//        super.onResume();
//
//        customAdapter.notifyDataSetChanged();
//    }

    class CustomAdapter extends BaseAdapter {

        @Override
        public int getCount() {

            return mTables.getTables().size();
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

            convertView = getActivity().getLayoutInflater().inflate(R.layout.custom_layout_table_list_row, null);

            TextView table = (TextView) convertView.findViewById(R.id.custom_layout_table_list_row__table_number);
            table.setText(mTables.getTable(position).toString());

            return convertView;
        }
    }
}






























