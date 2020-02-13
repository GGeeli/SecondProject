package com.example.secondproject.Fragments;

import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.secondproject.Adapter.MoviesAdapter;
import com.example.secondproject.Model.Movie;
import com.example.secondproject.R;
import com.example.secondproject.SQLite.FavoriteDbHelper;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Favorites extends Fragment {


    private RecyclerView recyclerView;
    private MoviesAdapter adapter;
    private List<Movie> movieList;
    private SwipeRefreshLayout swipeContainer;
    private FavoriteDbHelper favoriteDbHelper;



    public Favorites() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View FragmentUI = inflater.inflate(R.layout.fragment_favorites, container, false);


        initViews(FragmentUI);

        return FragmentUI;
    }

    private void initViews(View v){
        recyclerView = v.findViewById(R.id.recycler_view);
        movieList = new ArrayList<>();
        adapter = new MoviesAdapter(getContext(),movieList);

        if (getActivity().getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        }else
            recyclerView.setLayoutManager(new GridLayoutManager(getContext(),4));

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        favoriteDbHelper = new FavoriteDbHelper(getContext());

        getAllFavorite();

    }

    private void getAllFavorite(){
        new AsyncTask<Void, Void, Void>(){

            @Override
            protected void onPostExecute(Void aVoid) {

                super.onPostExecute(aVoid);
                adapter.notifyDataSetChanged();
            }

            @Override
            protected Void doInBackground(Void... voids) {
                movieList.clear();
                movieList.addAll(favoriteDbHelper.getAllFavorite());
                return null;
            }
        }.execute();
    }

}
