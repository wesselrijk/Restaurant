package com.example.restaurant;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MenuItemAdapter extends ArrayAdapter<MenuItem> {

    private Context context;
    private ArrayList<MenuItem> menuItems;

    // constructor
    public MenuItemAdapter(Context context, int layout, ArrayList<MenuItem> objects) {
        super(context, layout, objects);
        this.context = context;
        this.menuItems = objects;
    }

    // an override method used to fill in the items of the GridView of the activity_main layout
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // if the convertView is empty, the convertView will be set to the grid_item layout
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.menu_item_row, parent,
                    false);
        }

        ImageView menuItemImageView = convertView.findViewById(R.id.menuItemImageView);
        TextView titleView = convertView.findViewById(R.id.titleView);
        TextView categoryView = convertView.findViewById(R.id.categoryView);
        TextView descriptionView = convertView.findViewById(R.id.descriptionView);
        TextView priceView = convertView.findViewById(R.id.priceView);

        MenuItem current = menuItems.get(position);

        Picasso.with(context).load(current.getImageUrl()).into(menuItemImageView);
        titleView.setText(current.getName());
        categoryView.setText(current.getCategory());
        descriptionView.setText(current.getDescription());
        priceView.setText("Price: €"+String.valueOf(current.getPrice())+",-");


        return convertView;
    }
}
