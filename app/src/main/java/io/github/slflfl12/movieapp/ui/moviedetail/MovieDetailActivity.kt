package io.github.slflfl12.movieapp.ui.moviedetail

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import dagger.hilt.android.AndroidEntryPoint
import io.github.slflfl12.movieapp.R
import io.github.slflfl12.movieapp.databinding.ActivityMovieDetailBinding
import io.github.slflfl12.movieapp.databinding.LayoutMovieDetailBodyBinding
import io.github.slflfl12.movieapp.ui.base.BaseActivity
import io.github.slflfl12.presentation.movie.MovieDetailViewModel

@AndroidEntryPoint
class MovieDetailActivity :
    BaseActivity<ActivityMovieDetailBinding, MovieDetailViewModel>(R.layout.activity_movie_detail) {


    override val vm: MovieDetailViewModel by viewModels()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.activity = this@MovieDetailActivity
        intent?.getIntExtra(KEY_MOVIE_ID, 0)?.let {
            vm.idSubject.onNext(it)
        }
        initRecyclerView()
        initObserve()
    }

    private fun initRecyclerView() {
    }

    private fun initObserve() {
        vm.movie.observe(this, Observer {movie->
            binding.movie = movie
        })
    }

    companion object {
        const val KEY_MOVIE_ID = "key_movie_id"
    }

}