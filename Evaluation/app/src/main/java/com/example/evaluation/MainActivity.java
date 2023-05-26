package com.example.evaluation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.tabs.TabLayout;


public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        TabLayout tabLayout = findViewById(R.id.tab_layout);
        ViewPager viewPager = findViewById(R.id.view_pager);


        tabLayout.setupWithViewPager(viewPager);
        Adapter adapter=new Adapter(getSupportFragmentManager(),FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);

        adapter.addFragment(new CategoriesFragment(),"Meal List");
        adapter.addFragment(new CategoryItemsFragment(),"Meal Cart");
        viewPager.setAdapter(adapter);

//      CategoriesFragment fetchBottomSheet = new CategoriesFragment();
//      fetchBottomSheet.fetchBottomSheet();




    }

    BottomSheetDialog dialog;
    private View view;


    private void createDialog(){
        View view = getLayoutInflater().inflate(R.layout.bottom_sheet,null,false);
        TextView itemName = view.findViewById(R.id.text);
        Button button = view.findViewById(R.id.add);
        itemName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
            }
        });

    }

}