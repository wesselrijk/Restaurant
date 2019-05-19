package com.example.restaurant;

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

    @Override
    public void gotMenuItems(ArrayList<MenuItem> menuItems) {

        adapter = new MenuItemAdapter(this, R.layout.menu_item_row, menuItems);
        listView.setAdapter(adapter);

    }

    @Override
    public void gotMenuItemsError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    private class OnItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            MenuItem clickedItem = (MenuItem) parent.getItemAtPosition(position);
            Intent intent = new Intent(MenuActivity.this, MenuItemActivity.class);
            intent.putExtra("clicked_item", clickedItem);
            startActivity(intent);
        }
    }
}
