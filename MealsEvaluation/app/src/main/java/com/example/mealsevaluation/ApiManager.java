package com.example.mealsevaluation;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ApiManager {
    private static final String API_URL = "https://www.themealdb.com/api/json/v1/1/categories.php";

    public static void fetchMealCategories(Context context, final ApiCallback callback) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, API_URL, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        List<MealCategory> mealCategories = new ArrayList<>();
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject categoryObj = response.getJSONObject(i);
                                String idCategory = categoryObj.getString("idCategory");
                                String strCategory = categoryObj.getString("strCategory");
                                String strCategoryThumb = categoryObj.getString("strCategoryThumb");
                                String strCategoryDescription = categoryObj.getString("strCategoryDescription");

                                MealCategory mealCategory = new MealCategory(idCategory, strCategory, strCategoryThumb, strCategoryDescription);
                                mealCategories.add(mealCategory);
                            }

                            callback.onSuccess(mealCategories);
                        } catch (JSONException e) {
                            callback.onError(e.getMessage());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        callback.onError(error.getMessage());
                    }
                });

        requestQueue.add(request);
    }

    public interface ApiCallback {
        void onSuccess(List<MealCategory> mealCategories);

        void onError(String errorMessage);
    }
}
