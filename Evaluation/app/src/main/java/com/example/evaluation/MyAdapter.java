package com.example.evaluation;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;
import java.util.List;



public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

     BottomSheetClickListener bottomListener;

     List<Categories> Categories_list;
     private Context context;


     public MyAdapter(List<Categories> categories_list , Context ctx ,BottomSheetClickListener listener) {
        this.context = ctx;
        Categories_list = categories_list;
        this.bottomListener = listener;

    }
    static class MyViewHolder extends RecyclerView.ViewHolder{



        public final View mView;
        TextView itemName;
        Button add;
        TextView strDescription;
        RelativeLayout layout;
        View seperator;

        private final ImageView imageView;

        MyViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            itemName = mView.findViewById(R.id.itemname);
            layout = mView.findViewById(R.id.relative_layout);
            seperator = mView.findViewById(R.id.seperator);
            imageView = mView.findViewById(R.id.imageview);
            strDescription = mView.findViewById(R.id.text);
            add = mView.findViewById(R.id.add);

        }



    }
    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.design, parent, false);
        return new MyViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressWarnings("RecyclerView") int position) {

        holder.itemName.setText(Categories_list.get(position).getStrCategory());

        Picasso.get()
                .load(Categories_list.get(position).getStrCategoryThumb())
                .into(holder.imageView);

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                  bottomListener.onItemclicked(Categories_list.get(position));
            }
        });

    }


    @Override
    public int getItemCount() {
        return Categories_list.size();
    }
    public void setOnClickListener(OnClickListener onClickListener) {
    }
    public interface OnClickListener {
        void onClick(int position, Categories model);
    }

//    public static void fetchBottomSheet() {
//        RetrofitApi retrofitApi = RetrofitClient.getRetrofitInstance().create(RetrofitApi.class);
//        Call<Response> call = retrofitApi.getSpecificPost();
//        call.enqueue(new Callback<Response>() {
//            @Override
//            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
//                assert response.body() != null;
//                categoriesList1.addAll(response.body().getSpecificPost());
//                myAdapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onFailure(Call<Response> call, Throwable t) {
//                System.out.println("Response Failure" + t.getMessage());
//            }
//        });
//
//    }
}