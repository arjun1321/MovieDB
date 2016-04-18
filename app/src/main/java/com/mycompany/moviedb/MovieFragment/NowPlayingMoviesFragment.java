package com.mycompany.moviedb.MovieFragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.mycompany.moviedb.Model.Movie;
import com.mycompany.moviedb.Model.MovieJsonObject;
import com.mycompany.moviedb.Network.ApiClient;
import com.mycompany.moviedb.R;
import com.mycompany.moviedb.Adapter.RecyclerViewMovieAdapter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.support.v7.widget.LinearLayoutManager.HORIZONTAL;

/**
 * Created by Arjun Kumar on 10-04-2016.
 */
public class NowPlayingMoviesFragment extends Fragment{

    RecyclerViewMovieAdapter adapter;
    ArrayList<Movie> movieList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.recycler_view_layout, container, false);
        RecyclerView recyclerView = (RecyclerView) v.findViewById(R.id.recyclerView);

        movieList = new ArrayList<>();

        adapter = new RecyclerViewMovieAdapter(getActivity(), movieList);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), 1, HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setClipToPadding(true);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);


        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {

//                Intent intent = new Intent();
//                intent.setClass(getActivity(), MovieDetailActivity.class);
//                intent.putExtra("movie object", movieList.get(position));
//                startActivity(intent);
            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });


        Call<MovieJsonObject> jsonObject = ApiClient.getInterface().getNowPlayingMovies();

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
