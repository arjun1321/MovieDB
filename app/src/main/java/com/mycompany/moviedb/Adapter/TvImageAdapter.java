package com.mycompany.moviedb.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mycompany.moviedb.MainActivity;
import com.mycompany.moviedb.Model.Tv;
import com.mycompany.moviedb.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Arjun Kumar on 10-04-2016.
 */
public class TvImageAdapter extends BaseAdapter{
    String baseUrl = "http://image.tmdb.org/t/p/w342";

    Context context;
    ArrayList<Tv> movies;

    public TvImageAdapter(Context context, ArrayList<Tv> movies) {
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
        TextView genre;
        TextView starRate;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = View.inflate(context, R.layout.image_adapter_layout, null);

            ViewHolder vh = new ViewHolder();

            vh.genre = (TextView) convertView.findViewById(R.id.genre);
            vh.movieImage = (ImageView) convertView.findViewById(R.id.movieImage);
//            vh.starRate =(TextView) convertView.findViewById(R.id.starRate);

            convertView.setTag(vh);
        }

        ViewHolder vh = (ViewHolder) convertView.getTag();

        Tv movie = (Tv)getItem(position);
        String posterPath = movie.getPosterPath();

        String genres = "";
        ArrayList<Integer> genreId = movie.getGenreId();
        for (int i=0; i<genreId.size(); i++){
            String genre="";
            int id = genreId.get(i);
            for(int j = 0; j< MainActivity.genresList.size(); j++){
                if(MainActivity.genresList.get(j).getId() == id){
                    genre = MainActivity.genresList.get(j).getName();
                    break;
                }
            }
            genres = genres + genre +" ";
            Log.i("genre data", genre);
        }

        Log.i("genres list", genres);
//        vh.starRate.setText(String.valueOf(movie.getVoteAverage()));
        vh.genre.setText(genres);

        Picasso.with(context)
                .load(baseUrl+posterPath+"?api_key=52a1dc564a183650a3b560723582b6f6")
                .into(vh.movieImage);


        return convertView;
    }
}
