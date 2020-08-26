package io.github.slflfl12.movieapp.ui.moviedetail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.Window
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.transition.TransitionInflater
import dagger.hilt.android.AndroidEntryPoint
import io.github.slflfl12.movieapp.R
import io.github.slflfl12.movieapp.databinding.ActivityMovieDetailBinding
import io.github.slflfl12.movieapp.extensions.applyMaterialTransform
import io.github.slflfl12.movieapp.ui.base.BaseActivity
import io.github.slflfl12.movieapp.util.EventObserver
import io.github.slflfl12.movieapp.util.PosterPath
import io.github.slflfl12.presentation.model.MoviePresentationModel
import io.github.slflfl12.presentation.model.VideoPresentationModel
import io.github.slflfl12.presentation.movie.MovieDetailViewModel
import kotlinx.android.synthetic.main.activity_movie_detail.*

@AndroidEntryPoint
class MovieDetailActivity :
    BaseActivity<MovieDetailViewModel>() {


    override val vm: MovieDetailViewModel by viewModels()

    private val binding: ActivityMovieDetailBinding by binding(R.layout.activity_movie_detail)


    override fun onCreate(savedInstanceState: Bundle?) {
        applyMaterialTransform()
        super.onCreate(savedInstanceState)


        intent?.getParcelableExtra<MoviePresentationModel>(KEY_MOVIE)?.let {
            vm.idSubject.onNext(it.id)
        }

        with(binding) {
            lifecycleOwner = this@MovieDetailActivity
            activity = this@MovieDetailActivity
            vm = this@MovieDetailActivity.vm
        }





        initRecyclerView()
        initObserve()
    }

    private fun initRecyclerView() {
    }

    private fun initObserve() {
        vm.movie.observe(this, Observer { movie ->
            binding.movie = movie
        })

        vm.videoItemClickEvent.observe(
            this, EventObserver(
                this@MovieDetailActivity::onVideoItemClick
            )
        )
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) onBackPressed()
        return false
    }

    private fun onVideoItemClick(videoPresentationModel: VideoPresentationModel) {
        Intent(
            Intent.ACTION_VIEW,
            Uri.parse(PosterPath.getYoutubeVideoPath(videoPresentationModel.key))
        ).also {
            startActivity(it)
        }
    }

    companion object {
        const val KEY_MOVIE = "key_movie"
    }

}