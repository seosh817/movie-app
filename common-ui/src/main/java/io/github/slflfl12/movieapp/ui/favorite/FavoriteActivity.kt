package io.github.slflfl12.movieapp.ui.favorite

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import io.github.slflfl12.movieapp.R
import io.github.slflfl12.movieapp.adapters.MovieDetailAdapter
import io.github.slflfl12.movieapp.adapters.TvDetailAdapter
import io.github.slflfl12.movieapp.databinding.ActivityFavoriteBinding
import io.github.slflfl12.movieapp.extensions.gone
import io.github.slflfl12.movieapp.extensions.visible
import io.github.slflfl12.movieapp.ui.base.BaseActivity
import io.github.slflfl12.presentation.favorite.FavoriteViewModel
import io.github.slflfl12.presentation.model.MoviePresentationModel
import io.github.slflfl12.presentation.model.TvPresentationModel
import io.github.slflfl12.presentation.rxbus.RxBus
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo

@AndroidEntryPoint
class FavoriteActivity : BaseActivity<FavoriteViewModel>() {

    override val vm: FavoriteViewModel by viewModels()

    private val binding: ActivityFavoriteBinding by binding(R.layout.activity_favorite)

    private val compositeDisposable = CompositeDisposable()

    private val movieAdapter: MovieDetailAdapter by lazy {
        MovieDetailAdapter()
    }

    private val tvAdapter: TvDetailAdapter by lazy {
        TvDetailAdapter()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        with(binding) {
            lifecycleOwner = this@FavoriteActivity
            vm = this@FavoriteActivity.vm
        }

        initRecyclerView()
        initObserve()
        initBackGroundDisposables()
    }

    private fun initRecyclerView() {
        with(binding.rvFavoriteMovie) {
            adapter = movieAdapter
        }

        with(binding.rvFavoriteTv) {
            adapter = tvAdapter
        }
    }

    private fun initObserve() {
        vm.movieList.observe(this, { list ->
            if (!list.isNullOrEmpty()) {
                movieAdapter.setMovieList(list.sortedByDescending { it.releaseDate })
                binding.rvFavoriteMovie.visible()
            } else {
                movieAdapter.setMovieList(list)
                binding.rvFavoriteMovie.gone()
            }
        })

        vm.tvList.observe(this, { list ->
            if (!list.isNullOrEmpty()) {
                tvAdapter.setTvList(list.sortedByDescending { it.firstAirDate })
                binding.rvFavoriteTv.visible()
            } else {
                tvAdapter.setTvList(list)
                binding.rvFavoriteTv.gone()
            }
        })

    }

    private fun initBackGroundDisposables() {
        RxBus.movieSubscribe<MoviePresentationModel> { movie ->
            if (movie.favorite) {
                movieAdapter.addMovie(movie)
            } else {
                movieAdapter.removeMovie(movie)
            }
        }.addTo(compositeDisposable)

        RxBus.tvSubscribe<TvPresentationModel> { tv ->
            if (tv.favorite) {
                tvAdapter.addTv(tv)
            } else {
                tvAdapter.removeTv(tv)
            }
        }.addTo(compositeDisposable)
    }


    override fun onDestroy() {
        compositeDisposable.clear()
        super.onDestroy()
    }

}