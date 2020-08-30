package io.github.slflfl12.movieapp.bindings

import android.annotation.SuppressLint
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.github.florent37.glidepalette.BitmapPalette
import com.github.florent37.glidepalette.GlidePalette
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import io.github.slflfl12.movieapp.R
import io.github.slflfl12.movieapp.di.GlideApp
import io.github.slflfl12.movieapp.extensions.requestGlideListener
import io.github.slflfl12.movieapp.extensions.visible
import io.github.slflfl12.movieapp.util.PosterPath
import io.github.slflfl12.movieapp.util.checkNull
import io.github.slflfl12.movieapp.util.doIfNotNullOrEmpty
import io.github.slflfl12.presentation.model.KeywordPresentationModel
import io.github.slflfl12.presentation.model.MoviePresentationModel
import io.github.slflfl12.presentation.model.TvPresentationModel


@BindingAdapter("visibilityByModel")
fun visibilityByModel(view: View, anyList: List<Any>?) {
    anyList.doIfNotNullOrEmpty {
        view.visible()
    }
}

@BindingAdapter("bindVideo", "palette")
fun bindVideo(imageView: ImageView, _path: String?, palette: View?) {
    _path?.let {
        GlideApp.with(imageView.context)
            .load(
                PosterPath.getYoutubeThumbnailPath(
                    it
                )
            )
            .error(ContextCompat.getDrawable(imageView.context, R.drawable.not_found))
            .listener(
                GlidePalette.with(
                    PosterPath.getYoutubeThumbnailPath(
                        it
                    )
                )
                    .use(BitmapPalette.Profile.VIBRANT)
                    .intoBackground(palette)
                    .crossfade(true)
            )
            .into(imageView)
    }
}

@SuppressLint("SetTextI18n")
@BindingAdapter("bindReleaseDate")
fun TextView.bindReleaseDate(_releaseDate: String?) {
    if (_releaseDate != null) {
        text = context.getString(R.string.release_date_text, _releaseDate)
    }
}

@SuppressLint("SetTextI18n")
@BindingAdapter("bindAirDate")
fun TextView.bindAirDate(_airDate: String?) {
    if (_airDate != null) {
        text = context.getString(R.string.air_date_text, _airDate)
    }
}

@BindingAdapter("bindBackDrop")
fun ImageView.bindBackDrop(movie: MoviePresentationModel?) {
    movie?.checkNull({
        bindBackDrop(
            this,
            it.backdropPath,
            it.posterPath
        )
    }, {
        bindNullGlide(this)
    })
}

@BindingAdapter("bindBackDrop")
fun ImageView.bindBackDrop(tv: TvPresentationModel?) {
    Log.d("tv", "Tvtvtvtv")
    tv?.checkNull({
        bindBackDrop(
            this,
            it.backdropPath,
            it.posterPath
        )
    }, {
        bindNullGlide(this)
    })
}

@BindingAdapter("bindChipKeywords")
fun bindChipKeywords(chipGroup: ChipGroup, keywords: List<KeywordPresentationModel>?) {
    keywords.doIfNotNullOrEmpty {
        chipGroup.visible()
        for (keyword in it) {
            val chip = Chip(chipGroup.context)
            chip.text = keyword.name
            chip.isCheckable = false
            chip.setTextAppearanceResource(R.style.ChipTextStyle)
            chip.setChipBackgroundColorResource(R.color.colorAccent)
            chipGroup.addView(chip)
        }
    }
}


private fun bindBackDrop(view: ImageView, backDropPath: String?, posterPath: String?) {
    backDropPath.checkNull({
        GlideApp.with(view.context)
            .load(PosterPath.getBackdropPath(it))
            .error(ContextCompat.getDrawable(view.context, R.drawable.not_found))
            .listener(view.requestGlideListener())
            .into(view)
    }, {
        GlideApp.with(view.context)
            .load(
                PosterPath.getPosterPath(posterPath)
            )
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