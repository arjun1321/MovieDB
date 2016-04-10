package com.mycompany.moviedb.TvFragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.mycompany.moviedb.Model.Tv;
import com.mycompany.moviedb.Model.TvJsonObject;
import com.mycompany.moviedb.Network.ApiClient;
import com.mycompany.moviedb.R;
import com.mycompany.moviedb.RecyclerViewTvAdapter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Arjun Kumar on 10-04-2016.
 */
public class OnTheAirTvFragment extends Fragment {
    ArrayList<Tv> TvList;
    RecyclerViewTvAdapter adapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.recycler_view_layout, container, false);
        RecyclerView recyclerView = (RecyclerView) v.findViewById(R.id.recyclerView);

        TvList = new ArrayList<>();

        adapter = new RecyclerViewTvAdapter(getActivity(), TvList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(layoutManager);
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


        Call<TvJsonObject> jsonObject = ApiClient.getInterface().getOntheAirTv();

        jsonObject.enqueue(new Callback<TvJsonObject>() {
            @Override
            public void onResponse(Call<TvJsonObject> call, Response<TvJsonObject> response) {
                TvJsonObject jsonObject1 = response.body();

                for (int i = 0; i < jsonObject1.getResults().size(); i++)
                    TvList.add(jsonObject1.getResults().get(i));

                Log.i("movie data", String.valueOf(TvList.size()));

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<TvJsonObject> call, Throwable t) {
                Toast.makeText(getActivity(), "Check your internet connection", Toast.LENGTH_LONG);

            }
        });

        return v;
    }
}
