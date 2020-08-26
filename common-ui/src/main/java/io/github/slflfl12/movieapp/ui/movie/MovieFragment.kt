package io.github.slflfl12.movieapp.ui.movie

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.transition.TransitionInflater
import dagger.hilt.android.AndroidEntryPoint
import io.github.slflfl12.movieapp.R
import io.github.slflfl12.movieapp.databinding.FragmentMovieBinding
import io.github.slflfl12.movieapp.ui.base.BaseFragment
import io.github.slflfl12.movieapp.ui.main.MainActivity
import io.github.slflfl12.movieapp.ui.moviedetail.MovieDetailActivity
import io.github.slflfl12.movieapp.util.EventObserver
import io.github.slflfl12.presentation.model.MoviePresentationModel
import io.github.slflfl12.presentation.movie.MovieViewModel
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class MovieFragment: BaseFragment<FragmentMovieBinding, MovieViewModel>(R.layout.fragment_movie) {

    override val vm: MovieViewModel by viewModels()

    private val movieAdapter: MovieAdapter by lazy {
        MovieAdapter(vm)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        initView()
        initObserve()
    }

    private fun initView() {
    }

    private fun initRecyclerView() {
        binding.rvMovie.apply {
            adapter = movieAdapter
            addOnScrollListener(object: RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    var lastVisibleItemPosition = 0
                    var lastVisibleItemPositions = intArrayOf()
                    val mLayoutManager = recyclerView.layoutManager

                    if(mLayoutManager is StaggeredGridLayoutManager) {
                        lastVisibleItemPositions = mLayoutManager.findLastCompletelyVisibleItemPositions(null)
                    } else if (mLayoutManager is GridLayoutManager) {
                        lastVisibleItemPosition = mLayoutManager.findLastCompletelyVisibleItemPosition()
                    } else if (mLayoutManager is LinearLayoutManager) {
                        lastVisibleItemPosition = mLayoutManager.findLastCompletelyVisibleItemPosition()
                    }

                    if(dy > 0) {
                        if(mLayoutManager is LinearLayoutManager || mLayoutManager is GridLayoutManager)
                            if(lastVisibleItemPosition == recyclerView.adapter?.itemCount?.minus(1)) {
                                vm.plusPage()
                            }
                    }

                }
            })
        }
    }



    private fun initObserve() {

        vm.movieList.observe(viewLifecycleOwner, Observer {
            if(it[0].page == 1) {
                movieAdapter.setItems(it)
            } else {
                movieAdapter.addItems(it)
            }

        })

        vm.isLoading.observe(viewLifecycleOwner, Observer {
            if(it) {
                binding.pbLoading.visibility = View.VISIBLE
            } else {
                binding.pbLoading.visibility = View.GONE
            }
        })

        vm.networkErrorResponse.observe(viewLifecycleOwner, Observer {
            Toast.makeText(context, R.string.network_error_message, Toast.LENGTH_SHORT).show()
        })

        vm.lastPagingThrowable.observe(viewLifecycleOwner, Observer {
            Toast.makeText(context, R.string.last_paging_message, Toast.LENGTH_SHORT).show()
        })

        vm.toDetailEvent.observe(viewLifecycleOwner, EventObserver(
            this@MovieFragment::onNavigateToDetail
        ))
    }


    private fun onNavigateToDetail(pair: Pair<MoviePresentationModel, View>) {
        val movie = pair.first
        val view = pair.second
        Intent(context, MovieDetailActivity::class.java).apply {
            putExtra(MovieDetailActivity.KEY_MOVIE, movie)
        }.also {
            val options = ActivityOptions.makeSceneTransitionAnimation(activity, view, movie.title)
            startActivity(it, options.toBundle())
        }
    }

    override fun onDestroy() {
        vm.unBindPageDisposable()
        super.onDestroy()
    }
}