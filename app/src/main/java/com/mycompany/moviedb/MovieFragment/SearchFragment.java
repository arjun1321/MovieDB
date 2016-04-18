package com.mycompany.moviedb.MovieFragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.mycompany.moviedb.Adapter.GridImageAdapter;
import com.mycompany.moviedb.Model.Movie;
import com.mycompany.moviedb.Model.MovieJsonObject;
import com.mycompany.moviedb.MovieDetailActivity;
import com.mycompany.moviedb.Network.ApiClient;
import com.mycompany.moviedb.R;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Arjun Kumar on 06-04-2016.
 */
public class SearchFragment extends Fragment {

    ArrayList<Movie> movieList;
    GridImageAdapter adapter;

    String searchText;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.toprated_fragment_layout, container, false);
        GridView gv = (GridView) v.findViewById(R.id.toprated_gridview);

        Bundle bundle = getArguments();
        searchText = bundle.getString("search text");

        String encodeString;
        try {
            encodeString = URLEncoder.encode(searchText, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            encodeString = searchText;
        }


        movieList = new ArrayList<>();

        adapter = new GridImageAdapter(getActivity(), movieList);
        gv.setAdapter(adapter);


        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent();
                intent.setClass(getActivity(), MovieDetailActivity.class);
                intent.putExtra("movie object",movieList.get(position));
                startActivity(intent);
            }
        });


        Call<MovieJsonObject> jsonObject = ApiClient.getInterface().searchMovie(encodeString);

        jsonObject.enqueue(new Callback<MovieJsonObject>() {
            @Override
            public void onResponse(Call<MovieJsonObject> call, Response<MovieJsonObject> response) {
                MovieJsonObject jsonObject1 = response.body();

                for (int i = 0; i < jsonObject1.getMovieList().size(); i++)
                    movieList.add(jsonObject1.getMovieList().get(i));

                Log.i("movie data", String.valueOf(movieList.size()));

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<MovieJsonObject> call, Throwable t) {
                Toast.makeText(getActivity(), "Check your internet connection", Toast.LENGTH_LONG);
            }
        });

        return v;
    }
}
