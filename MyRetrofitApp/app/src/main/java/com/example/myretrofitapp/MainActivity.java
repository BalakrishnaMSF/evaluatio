package com.example.myretrofitapp;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    static TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt = findViewById(R.id.login);

    }

    private static Retrofit retrofit = null;

    public static Retrofit getRetrofit(){

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);


        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        return retrofit;

        call.enqueue(new Callback<PostsItems>() {
            @Override
            public void onResponse(Call<PostsItems> call, Response<PostsItems> response) {
                if (response.code() != 200) {
                    txt.setText("Check the connection");
                    return;
                }

                String json = "";

                assert response.body() != null;
                json = "userId= " + response.body().getUserId();

                txt.append(json);
            }

            @Override
            public void onFailure(Call<PostsItems> call, Throwable t) {
                Toast.makeText(MainActivity.this, "success", Toast.LENGTH_SHORT).show();

            }
        });



    }

    static APIInterface apiInterface(){
        return  getRetrofit().create(APIInterface.class);
    }
    static Call<PostsItems> call = new APIInterface() {
        @Override
        public Call<PostsItems> getSpecificPost() {
            return null;
        }
    }.getSpecificPost();

}

