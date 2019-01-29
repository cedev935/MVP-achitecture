package com.aliesaassadi.androidmvp.ui.activity.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.aliesaassadi.androidmvp.R;
import com.aliesaassadi.androidmvp.data.DataManager;
import com.aliesaassadi.androidmvp.data.movie.MoviesRepository;
import com.aliesaassadi.androidmvp.data.movie.network.MovieRemoteDataSource;
import com.aliesaassadi.androidmvp.data.movie.Movie;
import com.aliesaassadi.androidmvp.data.movie.network.services.MovieApi;
import com.aliesaassadi.androidmvp.ui.activity.base.BaseActivity;
import com.aliesaassadi.androidmvp.ui.activity.details.DetailsActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Ali Esa Assadi on 12/03/2018.
 */

public class MainActivity extends BaseActivity<MainPresenter> implements MainView, MovieAdapter.OnMovieAdapter {

    MovieAdapter movieAdapter;

    @BindView(R.id.recycler_view) RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        movieAdapter = new MovieAdapter(this);
        recyclerView.setAdapter(movieAdapter);
        presenter.getAllMovie();
    }

    @NonNull
    @Override
    protected MainPresenter createPresenter() {

        MovieApi movieApi = DataManager.getInstance().getMovieApi();

        MoviesRepository movieRepository = DataManager.getInstance()
                .getMovieRepository(MovieRemoteDataSource.getInstance(movieApi));

        return new MainPresenter(this, movieRepository);
    }


    @Override
    public void showMovies(List<Movie> movies) {
        movieAdapter.setItems(movies);
    }

    @Override
    public void showErrorMessage() {
        Toast.makeText(this, "Server error!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showThereIsNoMovies() {
        Toast.makeText(this, "There is no movies!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onMovieClicked(Movie movie) {
        Intent intent = new Intent(this, DetailsActivity.class);
        intent.putExtra("movie", movie);
        startActivity(intent);

    }
}
