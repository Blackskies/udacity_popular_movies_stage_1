package com.example.android.popularmovies;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MyViewHolder> {

    private Context mContext;
    private List<MovieBean> movies;

    public MoviesAdapter(Context context, List<MovieBean> movies){
        this.mContext = context;
        this.movies = movies;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        public TextView title, userrating;
        public ImageView thumbnail;

        public MyViewHolder(View view){
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            userrating = (TextView) view.findViewById(R.id.userrating);
            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);

            view.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION){
                        MovieBean clickedDataItem = movies.get(pos);
                        Intent intent = new Intent(mContext, DetailsActivity.class);
                        intent.putExtra("original_title",movies.get(pos).getOriginalTitle());
                        intent.putExtra("poster_path",movies.get(pos).getPosterPath());
                        intent.putExtra("overview",movies.get(pos).getOverview());
                        intent.putExtra("vote_average",Double.toString(movies.get(pos).getVoteAverage()));
                        intent.putExtra("release_date",movies.get(pos).getReleaseDate());
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        mContext.startActivity(intent);
                        Toast.makeText(v.getContext(),"You Clicked " + clickedDataItem.getOriginalTitle(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    @Override
    public MoviesAdapter.MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i){
        View view = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.movie_card, viewGroup,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder viewHolder, int i){
        viewHolder.title.setText(movies.get(i).getOriginalTitle());
        String vote = Double.toString(movies.get(i).getVoteAverage());
        viewHolder.userrating.setText(vote);

        Glide.with(mContext)
                .load(movies.get(i).getPosterPath())
                .into(viewHolder.thumbnail);
    }

    @Override
    public int getItemCount(){
        return movies.size();
    }

}