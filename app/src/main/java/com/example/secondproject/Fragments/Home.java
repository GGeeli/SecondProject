package com.example.secondproject.Fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.secondproject.ApiInterface;
import com.example.secondproject.MovieResults;
import com.example.secondproject.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Home extends Fragment {

    public static String BASE_URL = "https://api.themoviedb.org";
    public static int PAGE = 1;
    public static String API_KEY = "adab93d5f9a15175eb1d06fbc401e965";
    public static String LANGUAGE = "en-US";
    public static String CATEGORY = "popular";

    public Home() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View FragmentUI = inflater.inflate(R.layout.fragment_home, container, false);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiInterface myInterface = retrofit.create(ApiInterface.class);

        Call<MovieResults> Call =  myInterface.getMovies(CATEGORY,API_KEY,LANGUAGE,PAGE);

        Call.enqueue(new Callback<MovieResults>() {
            @Override
            public void onResponse(retrofit2.Call<MovieResults> call, Response<MovieResults> response) {
                MovieResults getMovieDetails = response.body();
                List<MovieResults.ResultsBean> listOfMovies= getMovieDetails.getResults();
                MovieResults.ResultsBean firstMovie = listOfMovies.get(0);
            }

            @Override
            public void onFailure(retrofit2.Call<MovieResults> call, Throwable t) {
                t.printStackTrace();
            }
        });


        return FragmentUI;
    }

}
