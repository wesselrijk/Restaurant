package com.example.restaurant;

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

    public MenuItemsRequest(Context context, String categoryClicked) {
        this.context = context;
        this.categoryClicked = categoryClicked;
    }

    public interface Callback {
        void gotMenuItems(ArrayList<MenuItem> menuItems);
        void gotMenuItemsError(String message);
    }

    public void getMenuItems(Callback activity) {
        this.activity = activity;
        RequestQueue queue = Volley.newRequestQueue(context);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                "https://resto.mprog.nl/menu", null, this,
                this);
        queue.add(jsonObjectRequest);
    }

    @Override
    public void onResponse(JSONObject response) {
        JSONArray menuItems = null;
        try {
            menuItems = response.getJSONArray("items");
        } catch (JSONException e) {
            e.printStackTrace();
            Log.d("onResponse error message.",e.getMessage());
            activity.gotMenuItemsError(e.getMessage());
        }
        Log.d("Add to list.", String.valueOf(menuItems.length()));
        ArrayList<MenuItem> menuItemsList = new ArrayList<>();
        for (int i = 0; i < menuItems.length(); i++) {
            try {
                JSONObject menuItemJSON = menuItems.getJSONObject(i);

                String name = menuItemJSON.getString("name");
                String description = menuItemJSON.getString("description");
                String imageUrl = menuItemJSON.getString("image_url");
                int price = menuItemJSON.getInt("price");
                String category = menuItemJSON.getString("category");


                if (category.equals(categoryClicked)) {
                    MenuItem menuItem = new MenuItem(name, description, imageUrl, price, category);
                    menuItemsList.add(menuItem);
                }
            } catch (JSONException e) {
                e.printStackTrace();
                Log.d("onResponse error message.",e.getMessage());
                activity.gotMenuItemsError(e.getMessage());
            }
        }
        Log.d("menuItems", String.valueOf(menuItemsList.size()));
        activity.gotMenuItems(menuItemsList);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        activity.gotMenuItemsError(error.toString());
        Log.d("Volley error message.",error.toString());
    }
}
