package com.example.evaluation;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitApi {

    @GET("v1/1/categories.php")
    Call<Response> getCategories() ;

    @GET("v1/1/categories.php/{strCategoryDescription}")
    Call<Response> getSpecificPost();


}
