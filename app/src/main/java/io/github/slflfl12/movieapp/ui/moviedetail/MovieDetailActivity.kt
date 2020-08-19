package io.github.slflfl12.movieapp.ui.moviedetail

import android.os.Bundle
import androidx.activity.viewModels
import io.github.slflfl12.movieapp.R
import io.github.slflfl12.movieapp.databinding.ActivityMovieDetailBinding
import io.github.slflfl12.movieapp.ui.base.BaseActivity
import io.github.slflfl12.presentation.movie.MovieDetailViewModel

class MovieDetailActivity :
    BaseActivity<ActivityMovieDetailBinding, MovieDetailViewModel>(R.layout.activity_movie_detail) {


    override val vm: MovieDetailViewModel by viewModels()
    private var movieKey: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        intent?.getIntExtra(KEY_MOVIE_ID, 0)?.let {
            movieKey = it
        }
    }

    companion object {
        const val KEY_MOVIE_ID = "key_movie_id"
    }

}