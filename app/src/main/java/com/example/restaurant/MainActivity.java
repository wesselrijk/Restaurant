package com.example.restaurant;
/**
 * <h1>Restuarant</h1>
 * The MainActivity for the Journal app.
 * The Restaurant app is an app in which a user can see the menu for a restaurant and see the
 * description of the individual items in a more detailed manner.
 * In the MainActivity a list will be displayed of all the item categories in the restaurant's
 * online database. A category can be clicked on by the user which opens the MenuActivity.
 * In the MenuActivity, a list will be displayed containing all the menu items from a category.
 * In the list, the image, name, description and price of the item will be visible.
 * An item can be clicked on, which opens the item in the DetailActivity, here the same information
 * will be visible as in the MainActivity as well as the category to which the item belongs to.
 */

// List of imports.
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements CategoriesRequest.Callback {

    private ArrayAdapter adapter;
    private ListView listView;

    /*
     * The MainActivity for the app is created here, it makes use of a private ArrayAdapter and
     * ListView variable. In the onCreate, the activity_main layout is set as well as the ListView
     * variable and a CategoriesRequest is made in order to request the categories of menu items the
     * restaurant has in their online database.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);

        CategoriesRequest requestCategories = new CategoriesRequest(this);
        requestCategories.getCategories(this);
    }

    // Method that is called when the CategoriesRequest was done successfully.
    @Override
    public void gotCategories(ArrayList<String> categories) {

        /* Capitalize first letter of each string in categories,
        * used: https://stackoverflow.com/questions/26642860/
        * capitalize-the-first-letter-in-a-arraylist-of-names
        */
        for (int index = 0; index < categories.size(); index++) {
            String name = categories.get(index);
            String[] names = name.split("\\s+");
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < names.length; i++) {
                if (i != 0) {
                    sb.append(' ');
                }
                sb.append(Character.toUpperCase(names[i].charAt(0)));
                sb.append(names[i].substring(1).toLowerCase());
            }
            categories.set(index, sb.toString());
        }

        // Set an adapter that will display categories to the ListView.
        adapter = new ArrayAdapter(this, R.layout.category_row, categories);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new OnItemClickListener());
    }

    // Method that is called when the CategoriesRequest encountered an error.
    @Override
    public void gotCategoriesError(String message) {

        // Display error in a message on-screen.
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    // Method that activates the MenuActivity activity for the category that was clicked on.
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
