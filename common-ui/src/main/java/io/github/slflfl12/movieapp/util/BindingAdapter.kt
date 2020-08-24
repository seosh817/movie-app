package io.github.slflfl12.movieapp.util

import android.annotation.SuppressLint
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.florent37.glidepalette.BitmapPalette
import com.github.florent37.glidepalette.GlidePalette
import io.github.slflfl12.movieapp.R
import io.github.slflfl12.movieapp.di.GlideApp
import io.github.slflfl12.movieapp.extension.requestGlideListener
import io.github.slflfl12.movieapp.extension.simpleToolbar
import io.github.slflfl12.movieapp.extension.visible
import io.github.slflfl12.movieapp.ui.moviedetail.VideoAdapter
import io.github.slflfl12.presentation.model.MoviePresentationModel
import io.github.slflfl12.presentation.model.VideoPresentationModel
import io.github.slflfl12.presentation.movie.MovieDetailViewModel
import io.github.slflfl12.remote.model.Video

@BindingAdapter("bind:bindImage", "palette")
fun ImageView.bindImage(path: String?, palette: View) {
    path?.let {
        GlideApp.with(this.context)
            .load(PosterPath.getPosterPath(it))
            .error(ContextCompat.getDrawable(context, R.drawable.not_found))
            .listener(
                GlidePalette.with(PosterPath.getPosterPath(it))
                    .use(BitmapPalette.Profile.VIBRANT)
                    .intoBackground(palette)
                    .crossfade(true)
            )
            .into(this)
    }
}

@BindingAdapter("bind:bindBackDrop")
fun ImageView.bindBackDrop(movie: MoviePresentationModel?) {
    movie?.chekNull({
        bindNullGlide(this)
    }, {
        bindBackDrop(this, movie.backdropPath, movie.posterPath)
    })

}

@BindingAdapter("bind:bindActivity", "bind:bindTitle")
fun bindToolbar(toolbar: Toolbar, activity: AppCompatActivity, _title: String?) {
    if (_title != null) {
        activity.simpleToolbar(toolbar, _title)
    }
}

@SuppressLint("SetTextI18n")
@BindingAdapter("bind:bindReleaseDate")
fun TextView.bindReleaseDate(_releaseDate: String?) {
    if (_releaseDate != null) {
        text = context.getString(R.string.release_date_text, _releaseDate)
    }
}

@BindingAdapter("bind:bindRating")
fun RatingBar.bindRating(_rating: Double?) {
    if (_rating != null) {
        rating = _rating.toFloat()
    }
}

@BindingAdapter("visibilityByModel")
fun visibilityByModel(view: View, anyList: List<Any>?) {
    anyList.doIfNotNullOrEmpty {
        view.visible()
    }
}

@BindingAdapter("bindVideo", "bind:palette")
fun bindVideo(imageView: ImageView, _path: String?, palette: View?) {
    _path?.let {
        if (!it.equals("")) {
            GlideApp.with(imageView.context)
                .load(PosterPath.getYoutubeThumbnailPath(it))
                .error(ContextCompat.getDrawable(imageView.context, R.drawable.not_found))
                .listener(
                    GlidePalette.with(PosterPath.getYoutubeThumbnailPath(it))
                        .use(BitmapPalette.Profile.VIBRANT)
                        .intoBackground(palette)
                        .crossfade(true)
                )
                .into(imageView)

        }

    }
}

@BindingAdapter("bindVideoList")
fun bindVideoList(recyclerView: RecyclerView, videos: List<VideoPresentationModel>?) {
    if (!videos.isNullOrEmpty()) {
        val adapter = VideoAdapter()
        adapter.setVideoList(videos)
        recyclerView.adapter = adapter
        recyclerView.isNestedScrollingEnabled = false
        recyclerView.setHasFixedSize(true)
        recyclerView.visible()
    }
}

private fun bindBackDrop(view: ImageView, backDropPath: String, posertPath: String) {
    backDropPath.checkEmptyAct({
        GlideApp.with(view.context)
            .load(PosterPath.getPosterPath(it))
            .error(ContextCompat.getDrawable(view.context, R.drawable.not_found))
            .listener(view.requestGlideListener())
            .into(view)
    }, {
        GlideApp.with(view.context)
            .load(PosterPath.getBackdropPath(it))
            .error(ContextCompat.getDrawable(view.context, R.drawable.not_found))
            .listener(view.requestGlideListener())
            .into(view)
    })
}

private fun bindNullGlide(view: ImageView) {
    GlideApp.with(view.context)
        .load(R.drawable.not_found)
        .into(view)
}