package io.github.slflfl12.movieapp.bindings

import android.content.Intent
import android.net.Uri
import android.view.View
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import io.github.slflfl12.movieapp.adapters.MovieDetailAdapter
import io.github.slflfl12.movieapp.adapters.TvDetailAdapter
import io.github.slflfl12.movieapp.adapters.ReviewAdapter
import io.github.slflfl12.movieapp.adapters.VideoAdapter
import io.github.slflfl12.movieapp.extensions.visible
import io.github.slflfl12.movieapp.ui.moviedetail.MovieDetailActivity
import io.github.slflfl12.movieapp.ui.peopledetail.PeopleDetailActivity
import io.github.slflfl12.movieapp.ui.tvdetail.TvDetailActivity
import io.github.slflfl12.movieapp.util.PosterPath
import io.github.slflfl12.movieapp.util.doIfNotNullOrEmpty
import io.github.slflfl12.presentation.model.*

@BindingAdapter("bindVideoAdapterList")
fun bindVideoAdapterList(recyclerView: RecyclerView, videos: List<VideoPresentationModel>?) {
    val adapter = VideoAdapter()
    videos.doIfNotNullOrEmpty {
        recyclerView.adapter = adapter
        adapter.setVideoList(it)
        recyclerView.visible()
    }
}

@BindingAdapter("bindMovieDetailAdapterList")
fun bindMovieDetailAdapterList(
    recyclerView: RecyclerView,
    movies: List<MoviePresentationModel>?
) {
    val adapter = MovieDetailAdapter()
    movies.doIfNotNullOrEmpty { list ->
        recyclerView.adapter = adapter
        adapter.setMovieList(list.sortedByDescending { it.releaseDate })
        recyclerView.visible()
    }
}

@BindingAdapter("bindTvDetailAdapterList")
fun bindTvDetailAdapterList(
    recyclerView: RecyclerView,
    tvs: List<TvPresentationModel>?
) {
    val adapter = TvDetailAdapter()
    tvs.doIfNotNullOrEmpty { list ->
        recyclerView.adapter = adapter
        adapter.setTvList(list.sortedByDescending { it.firstAirDate })
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
            putExtra(PeopleDetailActivity.KEY_PERSON, person)
        }
        view.context.startActivity(peopleDetailIntent)
    }
}

@BindingAdapter("onMovieClick")
fun onMovieClick(view: View, movie: MoviePresentationModel) {
    view.setOnClickListener {
        val movieDetailIntent = Intent(
            it.context, MovieDetailActivity::class.java
        ).apply {
            putExtra(MovieDetailActivity.KEY_MOVIE, movie)
        }
        view.context.startActivity(movieDetailIntent)
    }
}

@BindingAdapter("onTvClick")
fun onTvClick(view: View, tv: TvPresentationModel) {
    view.setOnClickListener {
        val tvDetailIntent = Intent(
            it.context, TvDetailActivity::class.java
        ).apply {
            putExtra(TvDetailActivity.KEY_TV, tv)
        }
        view.context.startActivity(tvDetailIntent)
    }
}