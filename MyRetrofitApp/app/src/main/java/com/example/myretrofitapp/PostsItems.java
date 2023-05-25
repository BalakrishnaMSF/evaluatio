package com.example.myretrofitapp;

import com.google.gson.annotations.SerializedName;

public class PostsItems {
    @SerializedName("userId")
    int userId;


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
