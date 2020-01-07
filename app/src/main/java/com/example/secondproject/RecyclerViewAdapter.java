package com.example.secondproject;

import com.bumptech.glide.Glide;
import com.example.secondproject.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private Context mContext;
    private List<MovieResults> mData;

    public RecyclerViewAdapter(Context mContext, List<MovieResults> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.simple_movie,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.movie_name.setText(mData.get(position).getResults().get(position).getTitle());
        holder.movie_category.setText(mData.get(position).getResults().get(position).getRelease_date());
        holder.movie_description.setText(mData.get(position).getResults().get(position).getOverview());

        Glide.with(mContext).load(mData.get(position).getResults().get(position).getPoster_path());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView movie_name;
        TextView movie_category;
        TextView movie_description;
        ImageView img_thumbnail;



        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            movie_name = itemView.findViewById(R.id.movie_name);
            movie_category = itemView.findViewById(R.id.movie_category);
            movie_description = itemView.findViewById(R.id.movie_description);
            img_thumbnail = itemView.findViewById(R.id.thumbnail);

        }
    }

}
