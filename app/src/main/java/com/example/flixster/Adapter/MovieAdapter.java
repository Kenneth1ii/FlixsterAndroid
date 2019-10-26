package com.example.flixster.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.flixster.DetailActivity;
import com.example.flixster.R;
import com.example.flixster.models.Movie;

import org.parceler.Parcels;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.viewholder> {

    Context context;
    List<Movie> movies; // <Movie> is Class.
    // Constructor
    public MovieAdapter(Context context, List<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }

    // usually involves inflating layout from XML and returning holder.
    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d("MovieAdapter","onCreateViewHolder");
        View movieView = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false);
                // item_movie is new layout.xml , we created to inflate.
        return new viewholder(movieView);
    }

    // Involves populating data into the item through holder.
    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        Log.d("MovieAdapter","onBindViewHolder" + position);
        // Get the movie at the passed position
        Movie movie = movies.get(position);     // it knows position of list , .get() is array list method.
        // Bind movie data int viewHolder.
        holder.bind(movie); // Custom method created.
    }

    // Return total count of item in list.
    @Override
    public int getItemCount() {
        return movies.size();
    }

    // 1. Viewholder defined now , 2. Adapter
    public class viewholder extends RecyclerView.ViewHolder {

        RelativeLayout container;
        TextView tvTitle;
        TextView tvOverview;
        ImageView ivPoster;

        public viewholder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvOverview = itemView.findViewById(R.id.tvOverview);
            ivPoster = itemView.findViewById(R.id.ivPoster);
            container = itemView.findViewById(R.id.container);
        }

        public void bind(final Movie movie) {
            tvTitle.setText(movie.getTitle());  // movie class;
            tvOverview.setText(movie.getOverview());
            String imageURL;
            // if Phone is in landscape
            if (context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                imageURL = movie.getBackdropPath();
            }
            else {
                imageURL = movie.getPosterPath();
            }
            // then imageURL = backdrop image
            // else imageURL = poster image

            //1. register click listener whole row
            //2. Navigate to new activity on tap
            Glide.with(context).load(imageURL).into(ivPoster);

            container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(context, DetailActivity.class);
                   // i.putExtra("title", movie.getTitle()); not needed
                    i.putExtra("movie", Parcels.wrap(movie));
                    context.startActivity(i);
                }
            });
        }
    }
}
