package io.github.slflfl12.movieapp.util

import android.annotation.SuppressLint
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
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.ms.square.android.expandabletextview.ExpandableTextView
import io.github.slflfl12.movieapp.R
import io.github.slflfl12.movieapp.di.GlideApp
import io.github.slflfl12.movieapp.extensions.requestGlideListener
import io.github.slflfl12.movieapp.extensions.simpleToolbar
import io.github.slflfl12.movieapp.extensions.visible
import io.github.slflfl12.movieapp.adapters.ReviewAdapter
import io.github.slflfl12.movieapp.adapters.VideoAdapter
import io.github.slflfl12.presentation.model.KeywordPresentationModel
import io.github.slflfl12.presentation.model.MoviePresentationModel
import io.github.slflfl12.presentation.model.ReviewPresentationModel
import io.github.slflfl12.presentation.model.VideoPresentationModel
import io.github.slflfl12.presentation.movie.MovieDetailViewModel



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

@BindingAdapter("bindVideoViewModel", "videos")
fun bindAdapterVideoList(recyclerView: RecyclerView, vm: MovieDetailViewModel, videos: List<VideoPresentationModel>?) {
    val adapter = VideoAdapter(vm)
    videos.doIfNotNullOrEmpty {
        adapter.setVideoList(it)
        recyclerView.adapter = adapter
        recyclerView.isNestedScrollingEnabled = false
        recyclerView.setHasFixedSize(true)
        recyclerView.visible()
    }
}

@BindingAdapter("bindReviewViewModel", "reviews")
fun bindAdapterReviewList(recyclerView: RecyclerView, vm: MovieDetailViewModel, reviews: List<ReviewPresentationModel>?) {
    val adapter = ReviewAdapter(vm)
    reviews.doIfNotNullOrEmpty {
        recyclerView.adapter = adapter
        adapter.setReviewList(it)
        recyclerView.isNestedScrollingEnabled = false
        recyclerView.setHasFixedSize(true)
        recyclerView.visible()
    }
}

@BindingAdapter("bindExpandableText")
fun bindExpandableText(expandableTextView: ExpandableTextView, content: String?) {
    expandableTextView.text = content
}


@BindingAdapter("bindChipKeywords")
fun bindChipKeywords(chipGroup: ChipGroup, keywords: List<KeywordPresentationModel>?) {
    keywords.doIfNotNullOrEmpty {
        chipGroup.visible()
        for(keyword in it) {
            val chip = Chip(chipGroup.context)
            chip.text = keyword.name
            chip.isCheckable = false
            chip.setTextAppearanceResource(R.style.ChipTextStyle)
            chip.setChipBackgroundColorResource(R.color.colorAccent)
            chipGroup.addView(chip)
        }
    }
}



private fun bindBackDrop(view: ImageView, backDropPath: String, posterPath: String) {
    backDropPath.checkEmptyAct({
        GlideApp.with(view.context)
            .load(PosterPath.getBackdropPath(it))
            .error(ContextCompat.getDrawable(view.context, R.drawable.not_found))
            .listener(view.requestGlideListener())
            .into(view)
    }, {
        GlideApp.with(view.context)
            .load(PosterPath.getPosterPath(posterPath))
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