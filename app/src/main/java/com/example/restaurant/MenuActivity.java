package com.example.restaurant;
/**
 * The MenuActivity activity for the app.
 * In this activity a list will be displayed showing all the menu items that belong to the category
 * that has been selected in the MainActivity. The list is filled with data received from the
 * restaurant's online server. In the list, the image, name, description and price of the item will
 * be visible. If a menu item is clicked on the DetailActivity will start and showcase the
 * information of that menu item.
 */

// List of imports.
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity implements MenuItemsRequest.Callback {

    private MenuItemAdapter adapter;
    private ListView listView;


    /*
    * In the onCreate the activity_menu layout will be set. Data will be received from the intent
    * and a MenuItemsRequest will be done. The ListView in activity_menu will be set.
    */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Intent intent = getIntent();
        String categoryClicked = (String) intent.getSerializableExtra("clicked_category");

        MenuItemsRequest requestMenuItems = new MenuItemsRequest(this, categoryClicked);
        requestMenuItems.getMenuItems(this);

        listView = findViewById(R.id.listView);
        listView.setOnItemClickListener(new OnItemClickListener());
    }

    // Method that is called when the MenuItemsRequest was done successfully.
    @Override
    public void gotMenuItems(ArrayList<MenuItem> menuItems) {

        // Create a MenuItemAdapter and set it to the ListView.
        adapter = new MenuItemAdapter(this, R.layout.menu_item_row, menuItems);
        listView.setAdapter(adapter);
    }

    // Method that is called when the MenuItemsRequest encountered an error.
    @Override
    public void gotMenuItemsError(String message) {

        // Display error in a message on-screen.
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    // Method called when a menu item in the ListView is clicked on, opens MenuItemActivity.
    private class OnItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            MenuItem clickedItem = (MenuItem) parent.getItemAtPosition(position);

            // Set clicked item to the new intent and starts it.
            Intent intent = new Intent(MenuActivity.this, MenuItemActivity.class);
            intent.putExtra("clicked_item", clickedItem);
            startActivity(intent);
        }
    }
}
