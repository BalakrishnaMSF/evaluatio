package com.example.evaluation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private List<Categories> Categorieslist;
    private Context context;
    private OnClickListener OnClickListener;

    public MyAdapter(List<Categories> categorieslist) {
        this.context = context;
        Categorieslist = categorieslist;

    }
    static class MyViewHolder extends RecyclerView.ViewHolder{
        public final View mView;

        TextView itemName;
        private ImageView imageView;

        MyViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            itemName = mView.findViewById(R.id.itemname);
            imageView = mView.findViewById(R.id.imageview);

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
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        System.out.println("TEST ADAPTER "+Categorieslist.get(position).getStrCategory());

        holder.itemName.setText(Categorieslist.get(position).getStrCategory());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }

        });
        Picasso.get()
                .load(Categorieslist.get(position).getStrCategoryThumb())
                .into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return Categorieslist.size();
    }
    public void setOnClickListener(OnClickListener onClickListener) {
        this.OnClickListener = onClickListener;
    }
    public interface OnClickListener {
        void onClick(int position, Categories model);
    }
}