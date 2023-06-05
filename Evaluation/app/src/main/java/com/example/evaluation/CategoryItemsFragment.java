package com.example.evaluation;

import androidx.fragment.app.Fragment;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.List;



public class CategoryItemsFragment extends Fragment implements BottomSheetClickListener {

    RecyclerView recyclerView;
    MyAdapter adapter;


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public CategoryItemsFragment() {
    }


//    @NonNull
//    public static CategoryItemsFragment newInstance(String param1, String param2) {
//        CategoryItemsFragment fragment = new CategoryItemsFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        List<Categories> items = new ArrayList<>();
        adapter = new MyAdapter(items, getContext(), this);
        recyclerView.setAdapter(adapter);
    }
    Categories categories;
    @Override
    public void onResume(){
        super.onResume();
        loadSavedItems();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_category_items, container, false);
    }

    private void loadSavedItems() {
        SQLiteDatabase database = DatabaseHelper.getInstance(getContext()).getReadableDatabase();
        String[] Data = {"name", "logo"};
        Cursor cursor = database.query("MEAL", Data, null, null, null, null, null);
        List<Categories> items = new ArrayList<>();
        while (cursor.moveToNext()) {
            int nameIndex = cursor.getColumnIndexOrThrow("name");
            int logoIndex = cursor.getColumnIndexOrThrow("logo");
            String itemName = cursor.getString(nameIndex);
            String itemLogo = cursor.getString(logoIndex);
            Categories item = new Categories(itemName, itemLogo);
            items.add(item);
        }
        cursor.close();
        adapter.setItems(items);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onItemclicked(Categories categories) {
        DatabaseHelper helper = new DatabaseHelper(getContext());
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getActivity());
        View bottomSheetView = LayoutInflater.from(getActivity()).inflate(R.layout.bottom_sheet2, null);
        TextView descriptionTextView = bottomSheetView.findViewById(R.id.description);
        descriptionTextView.setText(categories.getStrCategoryDescription());
        bottomSheetDialog.setContentView(bottomSheetView);
        Button remove = bottomSheetView.findViewById(R.id.remove);
        bottomSheetDialog.show();
        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Categories categories1= new Categories(categories.getStrCategory(),categories.getStrCategoryThumb());
                helper.remove(categories);
                Toast.makeText(getContext(), "Data removed", Toast.LENGTH_SHORT).show();
                bottomSheetDialog.dismiss();
            }
        });
    }

}
