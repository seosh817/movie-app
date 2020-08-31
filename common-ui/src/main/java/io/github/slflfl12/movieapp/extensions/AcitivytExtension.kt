package io.github.slflfl12.movieapp.extensions

import android.view.View
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import com.google.android.material.transition.MaterialArcMotion
import com.google.android.material.transition.MaterialContainerTransform
import com.google.android.material.transition.MaterialContainerTransformSharedElementCallback
import io.github.slflfl12.movieapp.R
import io.github.slflfl12.movieapp.ui.moviedetail.MovieDetailActivity
import io.github.slflfl12.movieapp.ui.tvdetail.TvDetailActivity
import io.github.slflfl12.presentation.model.MoviePresentationModel
import io.github.slflfl12.presentation.model.TvPresentationModel

fun AppCompatActivity.simpleToolbar(toolbar: Toolbar, _title: String = "") {
    setSupportActionBar(toolbar)
    supportActionBar?.run {
        setDisplayHomeAsUpEnabled(true)
        setHomeAsUpIndicator(R.drawable.ic_arrow_white)
        title = _title
    }
}

fun AppCompatActivity.getArcTransition(): MaterialContainerTransform? {
    return MaterialContainerTransform(this).apply {
        addTarget(android.R.id.content)
        duration = 400
        pathMotion = MaterialArcMotion()
    }
}


fun AppCompatActivity.applyMovieMaterialTransform() {
    window.requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS)
    window.sharedElementsUseOverlay = false
    ViewCompat.setTransitionName(findViewById<View>(android.R.id.content), intent.getParcelableExtra<MoviePresentationModel>(MovieDetailActivity.KEY_MOVIE)?.title)

    // set up shared element transition
    setEnterSharedElementCallback(MaterialContainerTransformSharedElementCallback())
    setEnterSharedElementCallback(MaterialContainerTransformSharedElementCallback())
    window.sharedElementEnterTransition = getArcTransition()
    window.sharedElementReturnTransition = getArcTransition()
}

fun AppCompatActivity.applyTvMaterialTransform() {
    window.requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS)
    window.sharedElementsUseOverlay = false
    ViewCompat.setTransitionName(findViewById<View>(android.R.id.content), intent.getParcelableExtra<TvPresentationModel>(TvDetailActivity.KEY_TV)?.name)

    // set up shared element transition
    setEnterSharedElementCallback(MaterialContainerTransformSharedElementCallback())
    setEnterSharedElementCallback(MaterialContainerTransformSharedElementCallback())
    window.sharedElementEnterTransition = getArcTransition()
    window.sharedElementReturnTransition = getArcTransition()
}
