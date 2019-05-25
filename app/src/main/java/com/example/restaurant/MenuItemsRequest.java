package com.example.restaurant;
/**
 * The MenuItemsRequest class for the app.
 * This is the class that handles a JsonObjectRequest coming from the MenuActivity. This request
 * asks for a list of items belonging to a specific category and returns it to the MenuActivity if
 * successful. If not successful, error messages will be returned to the MainActivity.
 */

// List of imports.
import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MenuItemsRequest implements Response.Listener<JSONObject>, Response.ErrorListener  {

    private String categoryClicked;
    private Context context;
    private Callback activity;

    // Constructor sets variables.
    public MenuItemsRequest(Context context, String categoryClicked) {
        this.context = context;
        this.categoryClicked = categoryClicked;
    }

    // Callback method for the MenuActivity.
    public interface Callback {
        void gotMenuItems(ArrayList<MenuItem> menuItems);
        void gotMenuItemsError(String message);
    }

    // Method called to execute a GET request from the server, also sets activity.
    public void getMenuItems(Callback activity) {
        this.activity = activity;

        // Create a request queue and sets a request for menu items to the queue.
        RequestQueue queue = Volley.newRequestQueue(context);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                "https://resto.mprog.nl/menu", null, this,
                this);
        queue.add(jsonObjectRequest);
    }

    // Method called when there is a successful response from the server.
    @Override
    public void onResponse(JSONObject response) {

        // Creates an empty JSONArray and tries to set it to JSONArray from the response.
        JSONArray menuItems = null;
        try {
            menuItems = response.getJSONArray("items");
        } catch (JSONException e) {

            // Returns error to the activity.
            e.printStackTrace();
            activity.gotMenuItemsError(e.getMessage());
        }

        // Creates an empty ArrayList and tries to fill it with data from JSONArray categories.
        ArrayList<MenuItem> menuItemsList = new ArrayList<>();
        for (int i = 0; i < menuItems.length(); i++) {
            try {
                JSONObject menuItemJSON = menuItems.getJSONObject(i);

                String name = menuItemJSON.getString("name");
                String description = menuItemJSON.getString("description");
                String imageUrl = menuItemJSON.getString("image_url");
                int price = menuItemJSON.getInt("price");
                String category = menuItemJSON.getString("category");


                // Capitalize category name again.
                StringBuilder sb = new StringBuilder();
                sb.append(Character.toUpperCase(category.charAt(0)));
                sb.append(category.substring(1).toLowerCase());
                category = sb.toString();

                // Add objects to menuItemsList if they are of the category that the user requested.
                if (category.equals(categoryClicked)) {
                    MenuItem menuItem = new MenuItem(name, description, imageUrl, price, category);
                    menuItemsList.add(menuItem);
                }
            } catch (JSONException e) {

                // Returns error to the activity.
                e.printStackTrace();
                activity.gotMenuItemsError(e.getMessage());
            }
        }

        // Return menuItemsList to the activity.
        activity.gotMenuItems(menuItemsList);
    }

    // Method called when there is an unsuccessful response from the server.
    @Override
    public void onErrorResponse(VolleyError error) {

        // Returns error to the activity.
        activity.gotMenuItemsError(error.toString());
    }
}
