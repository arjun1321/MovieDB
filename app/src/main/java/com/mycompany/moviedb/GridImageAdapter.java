package com.mycompany.moviedb;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mycompany.moviedb.Model.Movie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Arjun Kumar on 05-04-2016.
 */
public class GridImageAdapter extends BaseAdapter {

    String baseUrl = "http://image.tmdb.org/t/p/w342";

    Context context;
    ArrayList<Movie> movies;

    public GridImageAdapter(Context context, ArrayList<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }

    @Override
    public int getCount() {
        return movies.size();
    }

    @Override
    public Object getItem(int position) {
        return movies.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public class ViewHolder {
        ImageView movieImage;
        TextView movieName;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = View.inflate(context, R.layout.image_adapter_layout, null);

            ViewHolder vh = new ViewHolder();

            vh.movieName = (TextView) convertView.findViewById(R.id.movieName);
            vh.movieImage = (ImageView) convertView.findViewById(R.id.movieImage);

            convertView.setTag(vh);
        }

        ViewHolder vh = (ViewHolder) convertView.getTag();

        Movie movie = (Movie)getItem(position);
        String posterPath = movie.getPosterPath();

        vh.movieName.setText(movie.getTitle());

        Picasso.with(context)
        .load(baseUrl+posterPath+"?api_key=52a1dc564a183650a3b560723582b6f6")
        .into(vh.movieImage);


        return convertView;
    }
}
