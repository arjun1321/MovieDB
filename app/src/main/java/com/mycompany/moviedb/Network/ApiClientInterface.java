package com.mycompany.moviedb.Network;


import com.mycompany.moviedb.Model.GenreJsonObject;
import com.mycompany.moviedb.Model.MovieJsonObject;
import com.mycompany.moviedb.Model.MovieTrailerJsonObject;
import com.mycompany.moviedb.Model.TvJsonObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Arjun Kumar on 27-03-2016.
 */
public interface ApiClientInterface {

    @GET("movie/top_rated?api_key=52a1dc564a183650a3b560723582b6f6")
    Call<MovieJsonObject> getJsonObject();

    @GET("search/movie?api_key=52a1dc564a183650a3b560723582b6f6")
    Call<MovieJsonObject> searchMovie(@Query("query") String movieName);

    @GET("genre/movie/list?api_key=52a1dc564a183650a3b560723582b6f6")
    Call<GenreJsonObject> getGenreObject();

    @GET("movie/upcoming?api_key=52a1dc564a183650a3b560723582b6f6")
    Call<MovieJsonObject> getUpComingMovies();

    @GET("movie/popular?api_key=52a1dc564a183650a3b560723582b6f6")
    Call<MovieJsonObject> getPopularMovies();

    @GET("movie/{id}/videos?api_key=52a1dc564a183650a3b560723582b6f6")
    Call<MovieTrailerJsonObject> getTrailerJsonObject(@Path("id") int id);

    @GET("movie/now_playing?api_key=52a1dc564a183650a3b560723582b6f6")
    Call<MovieJsonObject> getNowPlayingMovies();

    @GET("tv/top_rated?api_key=52a1dc564a183650a3b560723582b6f6")
    Call<TvJsonObject> getTopRatedTv();

    @GET("tv/popular?api_key=52a1dc564a183650a3b560723582b6f6")
    Call<TvJsonObject> getPopularTv();

    @GET("tv/on_the_air?api_key=52a1dc564a183650a3b560723582b6f6")
    Call<TvJsonObject> getOntheAirTv();

    @GET("tv/{id}/videos?api_key=52a1dc564a183650a3b560723582b6f6")
    Call<MovieTrailerJsonObject> getTvTrailerJsonObject(@Path("id") int id);

}
