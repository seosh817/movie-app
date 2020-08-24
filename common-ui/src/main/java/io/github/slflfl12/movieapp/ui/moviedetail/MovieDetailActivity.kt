package io.github.slflfl12.movieapp.ui.moviedetail

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import dagger.hilt.android.AndroidEntryPoint
import io.github.slflfl12.movieapp.R
import io.github.slflfl12.movieapp.databinding.ActivityMovieDetailBinding
import io.github.slflfl12.movieapp.ui.base.BaseActivity
import io.github.slflfl12.movieapp.util.EventObserver
import io.github.slflfl12.movieapp.util.PosterPath
import io.github.slflfl12.presentation.model.VideoPresentationModel
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

        vm.videoItemClickEvent.observe(this, EventObserver(
            this@MovieDetailActivity::onVideoItemClick
        ))
    }

    private fun onVideoItemClick(videoPresentationModel: VideoPresentationModel) {
        Intent(Intent.ACTION_VIEW, Uri.parse(PosterPath.getYoutubeVideoPath(videoPresentationModel.key))).also {
            startActivity(it)
        }
    }

    companion object {
        const val KEY_MOVIE_ID = "key_movie_id"
    }

}