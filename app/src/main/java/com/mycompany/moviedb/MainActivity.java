package com.mycompany.moviedb;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.provider.SearchRecentSuggestions;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.MenuItemCompat;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import com.mycompany.moviedb.Model.Genre;
import com.mycompany.moviedb.Model.GenreJsonObject;
import com.mycompany.moviedb.MovieFragment.HomeFragment;
import com.mycompany.moviedb.MovieFragment.PopularFragment;
import com.mycompany.moviedb.MovieFragment.SearchFragment;
import com.mycompany.moviedb.MovieFragment.TopRatedFragment;
import com.mycompany.moviedb.MovieFragment.UpcomingFragment;
import com.mycompany.moviedb.Network.ApiClient;
import com.mycompany.moviedb.TvFragment.PopularTvFragment;
import com.mycompany.moviedb.TvFragment.TopRatedTvFragment;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    public static ArrayList<Genre> genresList;
    String searchText ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Fragment fragment = new HomeFragment();
        getFragmentManager().beginTransaction().replace(R.id.fragment_frame, fragment).commit();


        genresList = new ArrayList<>();
        Call<GenreJsonObject> genreJsonObjectCall = ApiClient.getInterface().getGenreObject();
        genreJsonObjectCall.enqueue(new Callback<GenreJsonObject>() {
            @Override
            public void onResponse(Call<GenreJsonObject> call, Response<GenreJsonObject> response) {
                GenreJsonObject genreJsonObject = response.body();

                for(int i=0; i<genreJsonObject.getGenres().size(); i++){
                    genresList.add(genreJsonObject.getGenres().get(i));
                }

                Log.i("genreobject", String.valueOf(genresList.size()));
            }

            @Override
            public void onFailure(Call<GenreJsonObject> call, Throwable t) {

            }
        });



    }

    @Override
    public void onBackPressed() {
        FragmentManager manager = getFragmentManager();
        if(manager.getBackStackEntryCount() > 0){
            manager.popBackStackImmediate();
            manager.beginTransaction().commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        android.support.v7.widget.SearchView searchView = (android.support.v7.widget.SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.serachAction));

        searchView.setOnQueryTextListener(new android.support.v7.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchText = query;
                Fragment fragment = new SearchFragment();
                Bundle bundle = new Bundle();
                bundle.putString("search text", searchText);
                fragment.setArguments(bundle);
                getFragmentManager().beginTransaction().replace(R.id.fragment_frame, fragment).addToBackStack("search").commit();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });



        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        Fragment fragment;
        int id = item.getItemId();

        if(id == R.id.home){
            fragment = new HomeFragment();
            getFragmentManager().beginTransaction().replace(R.id.fragment_frame, fragment).commit();
            setTitle("MovieDB");

        } else
        if (id == R.id.movieToprated) {
            fragment = new TopRatedFragment();
            getFragmentManager().beginTransaction().replace(R.id.fragment_frame, fragment).addToBackStack("TopRated").commit();
            setTitle("Top rated movies");
        } else if (id == R.id.movieUpcoming) {
            fragment = new UpcomingFragment();
            getFragmentManager().beginTransaction().replace(R.id.fragment_frame, fragment).addToBackStack("Upcoming").commit();
            setTitle("Upcoming movies");

        } else if (id == R.id.moviePopular) {
            fragment = new PopularFragment();
            getFragmentManager().beginTransaction().replace(R.id.fragment_frame, fragment).addToBackStack("Popular").commit();
            setTitle("Popular movies");

        } else if (id == R.id.tvTopRated) {
            fragment = new TopRatedTvFragment();
            getFragmentManager().beginTransaction().replace(R.id.fragment_frame, fragment).addToBackStack("tvTopRated").commit();
            setTitle("Top rated tv shows");

        } else if (id == R.id.tvPopular) {

            fragment = new PopularTvFragment();
            getFragmentManager().beginTransaction().replace(R.id.fragment_frame, fragment).addToBackStack("tvPopular").commit();
            setTitle("Popular tv shows");
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
