package com.example.restaurant;
/**
 * The EntryAdapter adapter for the app.
 * The adapter is set to a ListView in the activity_main. Then the adapter will fill in the items
 * in the list of the ListView with information it is being fed from the database.
 */

// List of imports.
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

    // Constructor calls super and sets variables.
    public MenuItemAdapter(Context context, int layout, ArrayList<MenuItem> objects) {
        super(context, layout, objects);

        this.context = context;
        this.menuItems = objects;
    }

    // The visual representation for the ListView in the activity_menu is set.
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // If the convertView is empty, the convertView will be set to the menu_item_row layout.
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.menu_item_row, parent,
                    false);
        }

        ImageView menuImageView = convertView.findViewById(R.id.menuImageView);
        TextView titleView = convertView.findViewById(R.id.titleView);
        TextView descriptionView = convertView.findViewById(R.id.descriptionView);
        TextView priceView = convertView.findViewById(R.id.priceView);

        MenuItem current = menuItems.get(position);

        // Use Picasso to set the menuImageView to the iamge's url.
        Picasso.with(context).load(current.getImageUrl()).into(menuImageView);

        titleView.setText(current.getName());
        descriptionView.setText(current.getDescription());
        priceView.setText("Price: €"+String.valueOf(current.getPrice())+",-");

        return convertView;
    }
}
