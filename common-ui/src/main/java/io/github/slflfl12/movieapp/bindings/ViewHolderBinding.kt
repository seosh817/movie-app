package io.github.slflfl12.movieapp.bindings

import android.os.Build
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.request.RequestOptions
import com.github.florent37.glidepalette.BitmapPalette
import com.github.florent37.glidepalette.GlidePalette
import com.google.android.material.chip.Chip
import io.github.slflfl12.movieapp.R
import io.github.slflfl12.movieapp.di.GlideApp
import io.github.slflfl12.movieapp.extensions.getURLForResource
import io.github.slflfl12.movieapp.extensions.gone
import io.github.slflfl12.movieapp.extensions.requestGlideListener
import io.github.slflfl12.movieapp.extensions.visible
import io.github.slflfl12.movieapp.util.PosterPath
import io.github.slflfl12.movieapp.util.checkNull
import io.github.slflfl12.presentation.model.MoviePresentationModel
import io.github.slflfl12.presentation.model.PersonPresentationModel
import io.github.slflfl12.presentation.model.TvPresentationModel
import java.time.LocalDate

@BindingAdapter("bindImage", "palette")
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

@BindingAdapter("bindProfile")
fun bindingPostUrl(imageView: ImageView, path: String?) {
    path?.let {
        GlideApp.with(imageView.context)
            .load(PosterPath.getPosterPath(it))
            .error(ContextCompat.getDrawable(imageView.context, R.drawable.not_found))
            .apply(RequestOptions().circleCrop())
            .into(imageView)
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

@BindingAdapter("bindPersonMovie", "palette")
fun bindPersonMovie(view: ImageView, movie: MoviePresentationModel?, palette: View?) {
    movie?.checkNull({
        bindBackDropPalette(
            view,
            it.backdropPath,
            it.posterPath,
            palette
        )
    }, {
        bindNullGlide(view)
    })
}

@BindingAdapter("bindPersonTv", "palette")
fun bindPersonMovie(view: ImageView, tv: TvPresentationModel?, palette: View?) {
    tv?.checkNull({
        bindBackDropPalette(
            view,
            it.backdropPath,
            it.posterPath,
            palette
        )
    }, {
        bindNullGlide(view, palette)
    })
}


@BindingAdapter("bindBackDrop")
fun bindBackDrop(view: ImageView, person: PersonPresentationModel?) {
    person?.profilePath?.let {
        GlideApp.with(view.context)
            .load(PosterPath.getBackdropPath(it))
            .apply(RequestOptions().circleCrop())
            .into(view)
    }
}



@BindingAdapter("visibilityByPopular")
fun visibilityByPopular(chip: Chip, average: Float?) {
    average?.let {
        if(it / 2 >= 3.5) {
            chip.visible()
        } else {
            chip.gone()
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@BindingAdapter("visibilityByReleaseDate")
fun visibilityByReleaseDate(chip: Chip, releaseDate: String?) {
    releaseDate?.let {
        val now = LocalDate.now()
        val releaseLocalDate = LocalDate.parse(it).plusMonths(1)
        if (releaseLocalDate.isAfter(now)) {
            chip.visible()
        } else {
            chip.gone()
        }
    }
}

private fun bindBackDropPalette(
    view: ImageView,
    backDropPath: String?,
    posterPath: String?,
    palette: View?
) {
    backDropPath.checkNull({
        GlideApp.with(view.context)
            .load(PosterPath.getBackdropPath(it))
            .error(ContextCompat.getDrawable(view.context, R.drawable.not_found))
            .listener(GlidePalette.with(
                PosterPath.getBackdropPath(it))
                .use(BitmapPalette.Profile.VIBRANT)
                .intoBackground(palette)
                .crossfade(true))
            .into(view)
    }, {
        GlideApp.with(view.context)
            .load(PosterPath.getPosterPath(posterPath))
            .error(ContextCompat.getDrawable(view.context, R.drawable.not_found))
            .listener(
                GlidePalette.with(
                    PosterPath.getPosterPath(posterPath)
                )
                    .use(BitmapPalette.Profile.VIBRANT)
                    .intoBackground(palette)
                    .crossfade(true)
            )
            .into(view)
    })
}

private fun bindNullGlide(view: ImageView) {
    GlideApp.with(view.context)
        .load(R.drawable.not_found)
        .into(view)
}

private fun bindNullGlide(view: ImageView, palette: View?) {
    GlideApp.with(view.context)
        .load(R.drawable.not_found)
        .listener(
            GlidePalette.with(
                getURLForResource(R.drawable.not_found)
            )
                .use(BitmapPalette.Profile.VIBRANT)
                .intoBackground(palette)
                .crossfade(true)
        )
        .into(view)
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
