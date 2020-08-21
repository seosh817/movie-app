package io.github.slflfl12.movieapp.util

import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.github.florent37.glidepalette.BitmapPalette
import com.github.florent37.glidepalette.GlidePalette
import io.github.slflfl12.movieapp.R
import io.github.slflfl12.movieapp.di.GlideApp
import io.github.slflfl12.movieapp.extension.requestGlideListener
import io.github.slflfl12.movieapp.extension.simpleToolbar
import io.github.slflfl12.presentation.model.MoviePresentationModel

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
                .crossfade(true))
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

private fun bindBackDrop(view: ImageView, backDropPath:String, posertPath:String) {
    backDropPath.checkEmptyAct({
        GlideApp.with(view.context)
            .load(PosterPath.getBackdropPath(it))
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