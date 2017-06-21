package com.emprendesoft.easyservice.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.emprendesoft.easyservice.R;
import com.emprendesoft.easyservice.model.Food;
import com.squareup.picasso.Picasso;

import java.lang.ref.WeakReference;
import java.util.LinkedList;

public class MenuRecyclerViewAdapter extends RecyclerView.Adapter<MenuRecyclerViewAdapter.ViewHolder> {

    private LinkedList<Food> mFoods;
    private View.OnClickListener mOnClickListener;

    public MenuRecyclerViewAdapter(LinkedList<Food> foods) {
        super();

        mFoods = foods;
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {

        mOnClickListener = onClickListener;
    }

    @Override
    public MenuRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        // Inflates the row layout from xml when needed
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_menu_row, parent, false);

        view.setOnClickListener(mOnClickListener);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MenuRecyclerViewAdapter.ViewHolder holder, int position) {

        // Binds the data
        holder.setFood(mFoods.get(position));
    }

    @Override
    public int getItemCount() {

        // total number of rows
        return mFoods.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView mTextViewName;
        private final TextView mTextViewPrice;
        private final TextView mTextViewAllergens;
        private final ImageView image;
        WeakReference<Context> context;

        public ViewHolder(View itemView) {
            super(itemView);

            this.context = new WeakReference<>(itemView.getContext());

            mTextViewName = (TextView) itemView.findViewById(R.id.recyler_view_menu_row__name);
            mTextViewPrice = (TextView) itemView.findViewById(R.id.recyler_view_menu_row__price);
            mTextViewAllergens = (TextView) itemView.findViewById(R.id.ecyler_view_menu_row__allergens);
            image = (ImageView) itemView.findViewById(R.id.recyler_view_menu_row__image);
        }

        public void setFood(Food food) {

            if (food == null) {
                return;
            }

            mTextViewName.setText(food.getName());
            mTextViewPrice.setText("Precio: " + String.format("$%.2f", food.getPrice()));

            // Imagen using Picasso
            String imagenURL = food.getImagenURL();
            Picasso.with(context.get()).
                    load(imagenURL).
                    placeholder(R.drawable.food_placeholder).
                    into(image);

            mTextViewAllergens.setText(getAllergensIconString(food.getAllergens()));
        }

        private String getAllergensIconString(String[] allergens) {

            if (allergens == null) {
                return "";
            }

            String strAllergens = "";

            // milk, egg, fish, crustaceans, peanut
            for (String item: allergens) {

                switch (item)
                {
                    case "milk":
                        strAllergens = strAllergens + "\uD83E\uDD5B";
                        break;
                    case "egg":
                        strAllergens = strAllergens + "\uD83E\uDD5A";
                        break;
                    case "fish":
                        strAllergens = strAllergens + "\uD83D\uDC1F";
                        break;
                    case "crustaceans":
                        strAllergens = strAllergens + "\uD83E\uDD90";
                        break;
                    case "peanut":
                        strAllergens = strAllergens + "\uD83E\uDD5C";
                        break;
                }

                strAllergens = strAllergens + "  ";

            }

            return strAllergens.trim();
        }
    }
}















