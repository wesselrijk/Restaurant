package com.example.restaurant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements CategoriesRequest.Callback {

    private ArrayAdapter adapter;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CategoriesRequest requestCategories = new CategoriesRequest(this);
        requestCategories.getCategories(this);
        Toast.makeText(this,"Started", Toast.LENGTH_SHORT).show();
        listView = findViewById(R.id.listView);
    }

    @Override
    public void gotCategories(ArrayList<String> categories) {
        Toast.makeText(this,categories.get(0), Toast.LENGTH_LONG).show();

//        /* capitalize first letter of each string in categories
//        * used: https://stackoverflow.com/questions/26642860/
//        * capitalize-the-first-letter-in-a-arraylist-of-names*/
//        for (int index = 0; index < categories.size(); index++) {
//            String name = categories.get(index);
//            String[] names = name.split("\\s+");
//            StringBuilder sb = new StringBuilder();
//            for (int i = 0; i < names.length; i++) {
//                if (i != 0) {
//                    sb.append(' ');
//                }
//                sb.append(Character.toUpperCase(names[i].charAt(0)));
//                sb.append(names[i].substring(1).toLowerCase());
//            }
//            categories.set(index, sb.toString());
//        }
        adapter = new ArrayAdapter(this, R.layout.category_row, categories);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new OnItemClickListener());
    }

    @Override
    public void gotCategoriesError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }


    private class OnItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            String clickedCategory = parent.getItemAtPosition(position).toString();
            Intent intent = new Intent(MainActivity.this, MenuActivity.class);
            intent.putExtra("clicked_category", clickedCategory);
            startActivity(intent);
        }
    }
}
