package com.example.evaluation;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Response{

    @SerializedName("categories")
    private List<Categories> categories;

    public Response(List<Categories> strDescription) {
        this.strDescription = strDescription;
    }


    public List<Categories> getCategories(){
        return categories;
    }

    public String toString(){
        return
                "DataResponse{" +
                        "categories = '" + categories + '\'' +
                        "}";
    }
    @SerializedName("strDescription")
    private List<Categories> strDescription;


    public List<Categories> getSpecificPost(){
        return strDescription;
    }

}