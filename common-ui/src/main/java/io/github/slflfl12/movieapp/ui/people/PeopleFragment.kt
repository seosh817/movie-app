package io.github.slflfl12.movieapp.ui.people

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityOptionsCompat
import androidx.core.view.ViewCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import io.github.slflfl12.movieapp.R
import io.github.slflfl12.movieapp.adapters.PersonAdapter
import io.github.slflfl12.movieapp.databinding.FragmentPeopleBinding
import io.github.slflfl12.movieapp.extensions.checkIsMaterialVersion
import io.github.slflfl12.movieapp.ui.base.BaseFragment
import io.github.slflfl12.movieapp.ui.peopledetail.PeopleDetailActivity
import io.github.slflfl12.movieapp.util.EventObserver
import io.github.slflfl12.presentation.model.PersonPresentationModel
import io.github.slflfl12.presentation.people.PeopleViewModel

@AndroidEntryPoint
class PeopleFragment :
    BaseFragment<FragmentPeopleBinding, PeopleViewModel>(R.layout.fragment_people),
    PersonAdapter.Delegates {

    override val vm: PeopleViewModel by viewModels()

    private val personAdapter: PersonAdapter by lazy {
        PersonAdapter().apply {
            delegates = this@PeopleFragment
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        initObserve()
    }


    private fun initRecyclerView() {
        binding.rvPeople.apply {
            adapter = personAdapter
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    var lastVisibleItemPosition = 0
                    var lastVisibleItemPositions = intArrayOf()
                    val mLayoutManager = recyclerView.layoutManager

                    if (mLayoutManager is StaggeredGridLayoutManager) {
                        lastVisibleItemPositions =
                            mLayoutManager.findLastCompletelyVisibleItemPositions(null)
                    } else if (mLayoutManager is GridLayoutManager) {
                        lastVisibleItemPosition =
                            mLayoutManager.findLastCompletelyVisibleItemPosition()
                    } else if (mLayoutManager is LinearLayoutManager) {
                        lastVisibleItemPosition =
                            mLayoutManager.findLastCompletelyVisibleItemPosition()
                    }

                    if (dy > 0) {
                        if (mLayoutManager is LinearLayoutManager || mLayoutManager is GridLayoutManager)
                            if (lastVisibleItemPosition == recyclerView.adapter?.itemCount?.minus(1)) {
                                vm.plusPage()
                            }
                    }

                }
            })
        }
    }

    private fun initObserve() {

        vm.peopleList.observe(viewLifecycleOwner, Observer {
            if (it[0].page == 1) {
                personAdapter.setItems(it)
            } else {
                personAdapter.addItems(it)
            }

        })

        vm.isLoading.observe(viewLifecycleOwner, Observer {
            if (it) {
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

    }

    private fun onPersonClick(view: View, person: PersonPresentationModel) {
        Intent(context, PeopleDetailActivity::class.java).apply {
            putExtra(PeopleDetailActivity.KEY_PERSON, person)
        }.also { intent ->
            if (checkIsMaterialVersion()) {
                ViewCompat.getTransitionName(view)?.let {
                    val options =
                        ActivityOptionsCompat.makeSceneTransitionAnimation(
                            requireActivity(),
                            view,
                            it
                        )
                    startActivity(intent, options.toBundle())
                }
            } else {
                startActivity(intent)
            }
        }
    }

    override fun onItemClick(view: View, person: PersonPresentationModel) =
        onPersonClick(view, person)

}