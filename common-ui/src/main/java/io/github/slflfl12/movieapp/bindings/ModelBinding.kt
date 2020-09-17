package io.github.slflfl12.movieapp.bindings

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.request.RequestOptions
import com.github.florent37.glidepalette.BitmapPalette
import com.github.florent37.glidepalette.GlidePalette
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import io.github.slflfl12.movieapp.R
import io.github.slflfl12.movieapp.di.GlideApp
import io.github.slflfl12.movieapp.extensions.gone
import io.github.slflfl12.movieapp.extensions.requestGlideListener
import io.github.slflfl12.movieapp.extensions.visible
import io.github.slflfl12.movieapp.util.PosterPath
import io.github.slflfl12.movieapp.util.checkNull
import io.github.slflfl12.movieapp.util.doIfNotNullOrEmpty
import io.github.slflfl12.presentation.model.KeywordPresentationModel
import io.github.slflfl12.presentation.model.MoviePresentationModel
import io.github.slflfl12.presentation.model.PersonPresentationModel
import io.github.slflfl12.presentation.model.TvPresentationModel
import java.time.LocalDate


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

@BindingAdapter("bindKnownAs")
fun bindKnownAs(chipGroup: ChipGroup, knownList: List<String>?) {
    knownList.doIfNotNullOrEmpty {
        chipGroup.visible()
        for (known in it) {
            val chip = Chip(chipGroup.context)
            chip.text = known
            chip.isCheckable = false
            chip.setTextAppearanceResource(R.style.ChipTextStyle)
            chip.setChipBackgroundColorResource(R.color.colorAccent)
            chipGroup.addView(chip)
        }
    }
}


@BindingAdapter("bindFavorite")
fun bindFavorite(imageView: ImageView, favorite: Boolean) {
    if (favorite) {
        imageView.setImageDrawable(
            ContextCompat.getDrawable(
                imageView.context,
                R.drawable.ic_heart
            )
        )
    } else {
        imageView.setImageDrawable(
            ContextCompat.getDrawable(
                imageView.context,
                R.drawable.ic_heart_border
            )
        )
    }
}

