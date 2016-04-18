package com.mycompany.moviedb.TvFragment;

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

import com.mycompany.moviedb.Model.Tv;
import com.mycompany.moviedb.Model.TvJsonObject;
import com.mycompany.moviedb.Network.ApiClient;
import com.mycompany.moviedb.R;
import com.mycompany.moviedb.TvDetailActivity;
import com.mycompany.moviedb.Adapter.TvImageAdapter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Arjun Kumar on 10-04-2016.
 */
public class PopularTvFragment extends Fragment {
    ArrayList<Tv> TvList;
    TvImageAdapter adapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.toprated_fragment_layout, container, false);
        GridView gv = (GridView) v.findViewById(R.id.toprated_gridview);

        TvList = new ArrayList<>();

        adapter = new TvImageAdapter(getActivity(), TvList);
        gv.setAdapter(adapter);


        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent();
                intent.setClass(getActivity(), TvDetailActivity.class);
                intent.putExtra("movie object",TvList.get(position));
                startActivity(intent);
            }
        });


        Call<TvJsonObject> jsonObject = ApiClient.getInterface().getPopularTv();

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
