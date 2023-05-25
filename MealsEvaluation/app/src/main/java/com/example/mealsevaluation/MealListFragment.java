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

import java.util.List;

public class MealListFragment extends Fragment {
    private ListView listView;
    private MealCategoryAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_meal_list, container, false);
        listView = rootView.findViewById(R.id.listView);

        // Fetch meal categories from the API
        ApiManager.fetchMealCategories(requireContext(), new ApiManager.ApiCallback() {
            @Override
            public void onSuccess(List<MealCategory> mealCategories) {
                // Create and set the adapter
                adapter = new MealCategoryAdapter(requireContext(), mealCategories);
                listView.setAdapter(adapter);
            }

            @Override
            public void onError(String errorMessage) {
                Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show();
            }
        });

        // Set click listener for list items
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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

        Button addButton = bottomSheetView.findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Save the item data to local storage
                // Implement your logic here to save the data

                Toast.makeText(requireContext(), "Item added to cart", Toast.LENGTH_SHORT).show();
            }
        });

        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(requireContext());
        bottomSheetDialog.setContentView(bottomSheetView);
        bottomSheetDialog.show();
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
