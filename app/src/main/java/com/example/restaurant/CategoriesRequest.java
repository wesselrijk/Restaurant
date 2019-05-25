package com.example.restaurant;
/**
 * The CategoriesRequest class for the app.
 * This is the class that handles a JsonObjectRequest coming from the MainActivity. This request
 * asks for a list of categories and returns it to the MainActivity if successful. If not
 * successful, error messages will be returned to the MainActivity.
 */

// List of imports.
import android.content.Context;

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

public class CategoriesRequest implements Response.Listener<JSONObject>, Response.ErrorListener  {

    private Context context;
    private Callback activity;

    // Constructor sets context.
    public CategoriesRequest(Context context) {
        this.context = context;
    }

    // Callback method for the MainActivity.
    public interface Callback {
        void gotCategories(ArrayList<String> categories);
        void gotCategoriesError(String message);
    }

    // Method called to execute a GET request from the server, also sets activity.
    public void getCategories(Callback activity) {
        this.activity = activity;

        // Create a request queue and sets a request for categories to the queue.
        RequestQueue queue = Volley.newRequestQueue(context);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                "https://resto.mprog.nl/categories", null, this,
                this);
        queue.add(jsonObjectRequest);
    }


    // Method called when there is a successful response from the server.
    @Override
    public void onResponse(JSONObject response) {

        // Creates an empty JSONArray and tries to set it to JSONArray from the response.
        JSONArray categories = null;
        try {
            categories = response.getJSONArray("categories");
        } catch (JSONException e) {

            // Returns error to the activity.
            e.printStackTrace();
            activity.gotCategoriesError(e.getMessage());
        }

        // Creates an empty ArrayList and tries to fill it with data from JSONArray categories.
        ArrayList<String> categoriesList = new ArrayList<>();
        for (int i = 0; i < categories.length(); i++) {
            try {
                categoriesList.add(categories.getString(i));
            } catch (JSONException e) {

                // Returns error to the activity.
                e.printStackTrace();
                activity.gotCategoriesError(e.getMessage());
            }
        }

        // Return categoriesList to the activity.
        activity.gotCategories(categoriesList);
    }

    // Method called when there is an unsuccessful response from the server.
    @Override
    public void onErrorResponse(VolleyError error) {

        // Returns error to the activity.
        activity.gotCategoriesError(error.toString());
    }
}
