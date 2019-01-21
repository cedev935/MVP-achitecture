package com.example.ali.androidmvp.ui.activity.main.presenter;

import com.example.ali.androidmvp.data.network.model.Movie;
import com.example.ali.androidmvp.data.network.model.MovieResponse;
import com.example.ali.androidmvp.data.network.services.MovieService;
import com.example.ali.androidmvp.ui.activity.main.view.MainActivityView;
import com.example.ali.androidmvp.ui.activity.base.BasePresenter;

import java.lang.ref.WeakReference;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Ali Esa Assadi on 12/03/2018.
 */
public class MainActivityPresenter extends BasePresenter<MainActivityView> {

    private final MovieService mMovieService;

    public MainActivityPresenter(MainActivityView view, MovieService movieService) {
        super(view);
        mMovieService = movieService;
    }

    /**
     * Network
     **/
    public void getAllMovie() {
        Call<MovieResponse> userCall = mMovieService.getMovieApi().getAllMovie();
        userCall.enqueue(new MovieCallListener(view));
    }


    /**
     * Callback
     **/
    private static class MovieCallListener implements Callback<MovieResponse> {

        private WeakReference<MainActivityView> mView;

        private MovieCallListener(MainActivityView view) {
            mView = new WeakReference<>(view);
        }

        @Override
        public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
            if (mView.get() == null) return;

            List<Movie> movies = response.body() != null ? response.body().getMovies() : null;
            if (movies != null && !movies.isEmpty()) {
                mView.get().showMovies(movies);
            } else {
                mView.get().showThereIsNoMovies();
            }
        }

        @Override
        public void onFailure(Call<MovieResponse> call, Throwable t) {
            if (mView.get() == null) return;

            mView.get().showErrorMessage();
        }
    }
}
