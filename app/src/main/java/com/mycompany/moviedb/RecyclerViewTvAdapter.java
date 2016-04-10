package com.mycompany.moviedb;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mycompany.moviedb.Model.Movie;
import com.mycompany.moviedb.Model.Tv;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Arjun Kumar on 10-04-2016.
 */
public class RecyclerViewTvAdapter extends RecyclerView.Adapter<RecyclerViewTvAdapter.MyViewHolder> {

    String baseUrl = "http://image.tmdb.org/t/p/w342";
    Context context;

    ArrayList<Tv> movieList;

    public class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView movieImage;
        TextView genre;
        TextView starRate;
        public MyViewHolder(View view){
            super(view);
            movieImage = (ImageView)view.findViewById(R.id.movieImage);
            genre = (TextView) view.findViewById(R.id.genre);
            starRate = (TextView) view.findViewById(R.id.starRate);
        }


    }

    public RecyclerViewTvAdapter(Context context,ArrayList<Tv> movieList) {
        this.movieList = movieList;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_adapter_layout,parent, false);
        return  new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        Tv movie = movieList.get(position);
        String posterPath = movie.getPosterPath();

        String genres = "";
        ArrayList<Integer> genreId = movie.getGenreId();
        for (int i=0; i<genreId.size(); i++){
            String genre="";
            int id = genreId.get(i);
            for(int j=0; j<MainActivity.genresList.size(); j++){
                if(MainActivity.genresList.get(j).getId() == id){
                    genre = MainActivity.genresList.get(j).getName();
                    break;
                }
            }
            genres = genres + genre +" ";
            Log.i("genre data", genre);
        }

        Log.i("genres list", genres);
        holder.starRate.setText(String.valueOf(movie.getVoteAverage()));
        holder.genre.setText(genres);
        holder.genre.setMaxWidth(300);

        Picasso.with(context)
                .load(baseUrl+posterPath+"?api_key=52a1dc564a183650a3b560723582b6f6")
                .resize(250,300)
                .into(holder.movieImage);
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }
}
