package io.github.slflfl12.movieapp.bindings

import android.app.Activity
import android.app.ActivityOptions
import android.content.Intent
import android.net.Uri
import android.view.View
import androidx.core.content.ContextCompat.startActivity
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import io.github.slflfl12.movieapp.adapters.ReviewAdapter
import io.github.slflfl12.movieapp.adapters.VideoAdapter
import io.github.slflfl12.movieapp.extensions.visible
import io.github.slflfl12.movieapp.ui.peopledetail.PeopleDetailActivity
import io.github.slflfl12.movieapp.ui.tvdetail.TvDetailActivity
import io.github.slflfl12.movieapp.util.PosterPath
import io.github.slflfl12.movieapp.util.doIfNotNullOrEmpty
import io.github.slflfl12.presentation.base.BaseViewModel
import io.github.slflfl12.presentation.model.PersonPresentationModel
import io.github.slflfl12.presentation.model.ReviewPresentationModel
import io.github.slflfl12.presentation.model.VideoPresentationModel
import io.github.slflfl12.presentation.moviedetail.MovieDetailViewModel

@BindingAdapter("bindVideoAdapterList")
fun bindVideoAdapterList(recyclerView: RecyclerView, videos: List<VideoPresentationModel>?) {
    val adapter = VideoAdapter()
    videos.doIfNotNullOrEmpty {
        adapter.setVideoList(it)
        recyclerView.adapter = adapter
        recyclerView.isNestedScrollingEnabled = false
        recyclerView.setHasFixedSize(true)
        recyclerView.visible()
    }
}

@BindingAdapter("bindReviewAdapterList")
fun bindAdapterReviewList(
    recyclerView: RecyclerView,
    reviews: List<ReviewPresentationModel>?
) {
    val adapter = ReviewAdapter()
    reviews.doIfNotNullOrEmpty {
        recyclerView.adapter = adapter
        adapter.setReviewList(it)
        recyclerView.isNestedScrollingEnabled = false
        recyclerView.setHasFixedSize(true)
        recyclerView.visible()
    }
}

@BindingAdapter("onVideoClick")
fun onVideoClick(view: View, videoPresentationModel: VideoPresentationModel) {
    view.setOnClickListener {
        val playVideoIntent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse(PosterPath.getYoutubeVideoPath(videoPresentationModel.key))
        )
        view.context.startActivity(playVideoIntent)
    }
}

@BindingAdapter("onPersonClick")
fun onPersonClick(view: View, person: PersonPresentationModel) {
    view.setOnClickListener {
        val peopleDetailIntent = Intent(
            it.context, PeopleDetailActivity::class.java
        ).apply {
            putExtra(PeopleDetailActivity.KEY_PERSON_ID, person.id)
        }

        view.context.startActivity(peopleDetailIntent)
    }


}