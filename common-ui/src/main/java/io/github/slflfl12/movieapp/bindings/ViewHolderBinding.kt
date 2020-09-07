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
import io.github.slflfl12.movieapp.extensions.gone
import io.github.slflfl12.movieapp.extensions.visible
import io.github.slflfl12.movieapp.util.PosterPath
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