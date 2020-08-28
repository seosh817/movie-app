package io.github.slflfl12.movieapp.ui.tv

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import io.github.slflfl12.movieapp.R
import io.github.slflfl12.movieapp.adapters.TvAdapter
import io.github.slflfl12.movieapp.databinding.FragmentTvBinding
import io.github.slflfl12.movieapp.ui.base.BaseFragment
import io.github.slflfl12.movieapp.ui.moviedetail.MovieDetailActivity
import io.github.slflfl12.movieapp.ui.tvdetail.TvDetailActivity
import io.github.slflfl12.movieapp.util.EventObserver
import io.github.slflfl12.presentation.model.MoviePresentationModel
import io.github.slflfl12.presentation.model.TvPresentationModel
import io.github.slflfl12.presentation.tv.TvViewModel

@AndroidEntryPoint
class TvFragment: BaseFragment<FragmentTvBinding, TvViewModel>(R.layout.fragment_tv) {

    override val vm: TvViewModel by viewModels()

    private val tvAdapter: TvAdapter by lazy {
        TvAdapter(vm)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        initObserve()
    }


    private fun initRecyclerView() {
        binding.rvTv.apply {
            adapter = tvAdapter
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
        vm.tvList.observe(viewLifecycleOwner, Observer {
            if(it[0].page == 1) {
                tvAdapter.setItems(it)
            } else {
                tvAdapter.addItems(it)
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
            this@TvFragment::onNavigateToDetail
        )
        )
    }

    private fun onNavigateToDetail(pair: Pair<TvPresentationModel, View>) {
        val tv = pair.first
        val view = pair.second
        Intent(context, MovieDetailActivity::class.java).apply {
            putExtra(TvDetailActivity.KEY_TV, tv)
        }.also {
            val options = ActivityOptions.makeSceneTransitionAnimation(activity, view, tv.name)
            startActivity(it, options.toBundle())
        }
    }

    override fun onDestroy() {
        vm.unBindPageDisposable()
        super.onDestroy()
    }
}