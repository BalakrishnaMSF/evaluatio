package com.example.evaluation;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.widget.Toast;


public class CategoriesFragment extends Fragment implements BottomSheetClickListener {
    private MyAdapter myAdapter;

    RecyclerView recyclerView;
    private List<Categories> categoriesList;



    public CategoriesFragment() {
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_categories, container, false);
        categoriesList = new ArrayList<>();
        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fetchDataFromApi();
        recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        myAdapter = new MyAdapter(categoriesList, getContext(), this);
        //System.out.println("TEST ===> " + categoriesList.size());
        recyclerView.setAdapter(myAdapter);
    }

    private void fetchDataFromApi() {
        RetrofitApi retrofitApi = RetrofitClient.getRetrofitInstance().create(RetrofitApi.class);
        Call<Response> call = retrofitApi.getCategories();
        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                categoriesList.addAll(response.body().getCategories());
                //System.out.println("TEST " + response.body().getCategories().size());
                myAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                System.out.println("Response Failure" + t.getMessage());
            }
        });
    }

    @Override
    public void onItemclicked(String description) {
        DatabaseHelper helper = new DatabaseHelper(getContext());
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getActivity());
        View bottomSheetView = LayoutInflater.from(getActivity()).inflate(R.layout.bottom_sheet, null);
        bottomSheetDialog.setContentView(bottomSheetView);
        TextView descriptionTextView = bottomSheetView.findViewById(R.id.text);
        Button add = bottomSheetView.findViewById(R.id.add);
        descriptionTextView.setText(description);
        bottomSheetDialog.show();
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("DAta",description);
                helper.insert(description);
                helper.getData(description);

            }
        });
    }

}
