package com.example.restaurant;
/**
 * The MenuItemActivity activity for the app.
 * In this activity the user can view the menu item and its details. The image of the menu item
 * will be displayed large in the top of the screen. Furthermore, the name, category, description
 * and price will be shown.
 */

// List of imports.
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class MenuItemActivity extends AppCompatActivity {

    // Constructor can be left empty.
    public MenuItemActivity() {
    }

    /*
    * In the onCreate, super will be called and the layout will be set to activity_menu_item.
    * Using the intent and picasso, all information of the clicked_item will be set to the
    * corresponding Views of the activity_menu_item_layout.
    * */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_item);

        Intent intent = getIntent();
        MenuItem clickedItem = (MenuItem) intent.getSerializableExtra("clicked_item");

        ImageView image = findViewById(R.id.menuItemImageView);
        TextView title = findViewById(R.id.titleItemView);
        TextView category = findViewById(R.id.categoryItemView);
        TextView description = findViewById(R.id.descriptionItemView);
        TextView price = findViewById(R.id.priceItemView);

        Picasso.with(this).load(clickedItem.getImageUrl()).into(image);

        title.setText(clickedItem.getName());
        category.setText(" - "+clickedItem.getCategory()+" -");
        description.setText(clickedItem.getDescription());
        price.setText("Price: €"+String.valueOf(clickedItem.getPrice())+",-");
    }
}
