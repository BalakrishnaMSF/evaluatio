package com.example.evaluation;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;


public class CategoriesFragment extends Fragment implements BottomSheetClickListener {
    private MyAdapter myAdapter;

    RecyclerView recyclerView;
    private List<Categories> categoriesList;
//
    ItemDao itemDao;



    public CategoriesFragment() {
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_categories, container, false);
        categoriesList = new ArrayList<>();
        return view;
//        database= Room.databaseBuilder(getActivity(),AppDatabase.class,"app_database")
//                .allowMainThreadQueries().fallbackToDestructiveMigration().build();
//        itemDao = database.itemDao();
//        fetchDataFromApi();

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
    public void onItemclicked(Categories categories) {
        DatabaseHelper helper = new DatabaseHelper(getContext());
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getActivity());
        View bottomSheetView = LayoutInflater.from(getActivity()).inflate(R.layout.bottom_sheet, null);
        bottomSheetDialog.setContentView(bottomSheetView);
        TextView descriptionTextView = bottomSheetView.findViewById(R.id.text);
        Button add = bottomSheetView.findViewById(R.id.add);
        descriptionTextView.setText(categories.getStrCategoryDescription());
        bottomSheetDialog.show();
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                insertItemToDatabase(categories);
//
//                Toast.makeText(getActivity(), "Items added successfully", Toast.LENGTH_SHORT).show();
//
//                bottomSheetDialog.dismiss();

                helper.insert(categories);
                bottomSheetDialog.dismiss();
            }
        });
    }

    private void insertItemToDatabase(Categories category) {
        ItemEntity item = new ItemEntity(category.getStrCategory(), category.getStrCategoryThumb(), category.getStrCategoryDescription());
        itemDao.insertItem(item);
    }

//    @Override
//    public void onItemClicked(Category category) {
//        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getActivity());
//        View bottomSheetView = LayoutInflater.from(getActivity()).inflate(R.layout.bottom_sheet, null);
//        bottomSheetDialog.setContentView(bottomSheetView);
//
//        TextView descriptionTextView = bottomSheetView.findViewById(R.id.desc);
//        descriptionTextView.setText(category.getStrCategoryDescription());
//
//        Button addButton = bottomSheetView.findViewById(R.id.add);
//
//        addButton.setOnClickListener(v -> {
//            insertItemToDatabase(category); // pass remoteList.get(position)
//
//            Toast.makeText(getActivity(), "Items added successfully", Toast.LENGTH_SHORT).show();
//
//            bottomSheetDialog.dismiss();
//        });
//
//        bottomSheetDialog.show();
//    }

//    @Override
//    public void onItemClicked1(String description, String logo){
//        DatabaseHelper helper = new DatabaseHelper(getContext());
//        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getActivity());
//        View bottomSheetView = LayoutInflater.from(getActivity()).inflate(R.layout.bottom_sheet, null);
//        bottomSheetDialog.setContentView(bottomSheetView);
//        TextView descriptionTextView = bottomSheetView.findViewById(R.id.text);
//        Button add = bottomSheetView.findViewById(R.id.add);
//        descriptionTextView.setText(description);
//        bottomSheetDialog.show();
//        add.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                helper.insert(new PojoClass(description,logo));
//                List<PojoClass> contacts = helper.getAllContacts();
//                for (PojoClass cn : contacts) {
//                    String log = "ItemName: " + cn.getStrCategory() + " ,Logo: " + cn.getStrCategoryThumb() ;
//                    Log.d("Name: ", log);
//                }
//            }
//        });
//    }
}





