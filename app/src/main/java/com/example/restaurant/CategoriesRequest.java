package com.example.restaurant;

import android.content.Context;
import java.util.ArrayList;

public class CategoriesRequest  {

    private Context context;

    public CategoriesRequest(Context context) {
        this.context = context;
    }

    public interface Callback {
        void gotCategories(ArrayList<String> categories);
        void gotCategoriesError(String message);
    }

    public void getCategories(Callback activity) {

    }
}
