package com.example.restaurant;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class MenuItemActivity extends AppCompatActivity {

    private Context context;

    // constructor
    public MenuItemActivity() {
    }

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

        Picasso.with(context).load(clickedItem.getImageUrl()).into(image);
        title.setText(clickedItem.getName());
        category.setText(" - "+clickedItem.getCategory()+" -");
        description.setText(clickedItem.getDescription());
        price.setText("Price: €"+String.valueOf(clickedItem.getPrice())+",-");


    }
}
