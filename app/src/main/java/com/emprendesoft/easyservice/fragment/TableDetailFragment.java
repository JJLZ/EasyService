package com.emprendesoft.easyservice.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.emprendesoft.easyservice.R;

public class TableDetailFragment extends Fragment {

    int[] IMAGES = {R.drawable.enchiladas_suizas, R.drawable.pozole, R.drawable.mole_poblano};
    String[] NAMES = {"Enchiladas Suizas", "Pozole", "Mole Poblano" };
    String[] DESCRIPTIONS = {"Vamos a ver si aparece la imagen", "Mi comida favorita", "Muy rico"};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View root = inflater.inflate(R.layout.fragment_table_detail, container, false);

        ListView listView = (ListView) root.findViewById(R.id.list_table_detail);

        CustomAdapter customAdapter = new CustomAdapter();
        listView.setAdapter(customAdapter);

        return root;
    }

    class CustomAdapter extends BaseAdapter {

        @Override
        public int getCount() {

            return IMAGES.length;
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

            imageView.setImageResource(IMAGES[position]);
            textView_name.setText(NAMES[position]);
            textView_description.setText(DESCRIPTIONS[position]);

            return convertView;
        }
    }
}






























