package com.example.mealpicker;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MyAPICall {
    @GET("api/json/v1/1/categories.php")
    Call<List<DataModel>> getData();

}
