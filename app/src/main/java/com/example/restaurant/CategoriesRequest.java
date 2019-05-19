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

public class CategoriesRequest implements Response.Listener<JSONObject>, Response.ErrorListener  {

    private Context context;
    private Callback activity;

    public CategoriesRequest(Context context) {
        this.context = context;
    }

    public interface Callback {
        void gotCategories(ArrayList<String> categories);
        void gotCategoriesError(String message);
    }

    public void getCategories(Callback activity) {
        this.activity = activity;
        RequestQueue queue = Volley.newRequestQueue(context);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                "https://resto.mprog.nl/categories", null, this,
                this);
        queue.add(jsonObjectRequest);
    }

    @Override
    public void onResponse(JSONObject response) {
        JSONArray categories = null;
        try {
            categories = response.getJSONArray("categories");
        } catch (JSONException e) {
            e.printStackTrace();
            Log.d("onResponse error message.",e.getMessage());
            activity.gotCategoriesError(e.getMessage());
        }

        ArrayList<String> categoriesList = new ArrayList<>();
        for (int i = 0; i < categories.length(); i++) {
            try {
                categoriesList.add(categories.getString(i));
            } catch (JSONException e) {
                e.printStackTrace();
                Log.d("onResponse error message.",e.getMessage());
                activity.gotCategoriesError(e.getMessage());
            }
        }
        activity.gotCategories(categoriesList);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        activity.gotCategoriesError(error.toString());
        Log.d("Volley error message.",error.toString());
    }
}
