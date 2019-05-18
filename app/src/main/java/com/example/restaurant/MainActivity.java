package com.example.restaurant;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements CategoriesRequest.Callback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CategoriesRequest requestCategories = new CategoriesRequest(this);
        requestCategories.getCategories(this);
        Toast.makeText(this,"Started", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void gotCategories(ArrayList<String> categories) {
        Log.d("got category", categories.get(0));
        Toast.makeText(this,categories.get(0), Toast.LENGTH_LONG).show();
    }

    @Override
    public void gotCategoriesError(String message) {
        Log.d("got error", message);
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
