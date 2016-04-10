package com.mycompany.moviedb.MovieFragment;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mycompany.moviedb.R;
import com.mycompany.moviedb.TvFragment.OnTheAirTvFragment;

/**
 * Created by Arjun Kumar on 08-04-2016.
 */
public class HomeFragment extends Fragment {


    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.home_fragment_layout, container, false);


        Fragment fragment = new NowPlayingMoviesFragment();
        getChildFragmentManager().beginTransaction().replace(R.id.movieFrame, fragment).commit();

        Fragment fragment1 = new OnTheAirTvFragment();
        getChildFragmentManager().beginTransaction().replace(R.id.tvFrame, fragment1).commit();
        return v;
    }
}
