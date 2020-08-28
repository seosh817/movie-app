package io.github.slflfl12.movieapp.bindings

import android.view.View
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.github.florent37.glidepalette.BitmapPalette
import com.github.florent37.glidepalette.GlidePalette
import io.github.slflfl12.movieapp.R
import io.github.slflfl12.movieapp.di.GlideApp
import io.github.slflfl12.movieapp.util.PosterPath

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