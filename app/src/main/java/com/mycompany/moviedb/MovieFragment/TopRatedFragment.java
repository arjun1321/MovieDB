package com.mycompany.moviedb.MovieFragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.mycompany.moviedb.GridImageAdapter;
import com.mycompany.moviedb.Model.Movie;
import com.mycompany.moviedb.Model.MovieJsonObject;
import com.mycompany.moviedb.Network.ApiClient;
import com.mycompany.moviedb.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Arjun Kumar on 05-04-2016.
 */
public class TopRatedFragment extends Fragment {

    ArrayList<Movie> movieList;
    GridImageAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.toprated_fragment_layout, container, false);
        GridView gv = (GridView) v.findViewById(R.id.toprated_gridview);

        movieList = new ArrayList<>();

        adapter = new GridImageAdapter(getActivity(), movieList);
        gv.setAdapter(adapter);


        Call<MovieJsonObject> jsonObject = ApiClient.getInterface().getJsonObject();

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

            }
        });


        return v;
    }
}
