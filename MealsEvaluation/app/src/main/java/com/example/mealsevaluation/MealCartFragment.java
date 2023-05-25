package com.example.mealsevaluation;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MealCartFragment extends Fragment {
    private ListView cartListView;
    private MealCategoryAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_meal_cart, container, false);
        cartListView = rootView.findViewById(R.id.cartListView);

        // Load saved data from local storage
        List<MealCategory> savedCategories = loadDataFromLocalStorage(); // Implement your logic to load data

        // Create and set the adapter
        adapter = new MealCategoryAdapter(requireContext(), savedCategories);
        cartListView.setAdapter(adapter);

        // Set click listener for list items
        cartListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                MealCategory selectedCategory = adapter.getItem(position);
                showBottomSheet(selectedCategory);
            }
        });

        return rootView;
    }

    private void showBottomSheet(MealCategory mealCategory) {
        View bottomSheetView = getLayoutInflater().inflate(R.layout.bottom_sheet_layout, null);

        TextView descriptionTextView = bottomSheetView.findViewById(R.id.descriptionTextView);
        descriptionTextView.setText(mealCategory.getStrCategoryDescription());

        Button removeButton = bottomSheetView.findViewById(R.id.removeButton);
        removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Remove the item data from local storage
                // Implement your logic here to remove the data

                Toast.makeText(requireContext(), "Item removed from cart", Toast.LENGTH_SHORT).show();
            }
        });

        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(requireContext());
        bottomSheetDialog.setContentView(bottomSheetView);
        bottomSheetDialog.show();
    }

    private List<MealCategory> loadDataFromLocalStorage() {
        // Implement your logic to load data from local storage
        // Return the list of saved meal categories
        // Example code:
        List<MealCategory> savedCategories = new ArrayList<>();
        savedCategories.add(new MealCategory("1", "Beef", "https://www.themealdb.com/images/category/beef.png", "Beef is the culinary name for meat from cattle."));
        savedCategories.add(new MealCategory("2", "Chicken", "https://www.themealdb.com/images/category/chicken.png", "Chicken is the most common type of poultry."));
        return savedCategories;
    }

    private static class MealCategoryAdapter extends ArrayAdapter<MealCategory> {
        private Context context;

        public MealCategoryAdapter(Context context, List<MealCategory> mealCategories) {
            super(context, 0, mealCategories);
            this.context = context;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
            }

            MealCategory mealCategory = getItem(position);

            ImageView imageView = convertView.findViewById(R.id.imageView);
            TextView textView = convertView.findViewById(R.id.textView);

            // Load image using a library like Picasso or Glide
            Picasso.get().load(mealCategory.getStrCategoryThumb()).into(imageView);

            textView.setText(mealCategory.getStrCategory());

            return convertView;
        }
    }
}
