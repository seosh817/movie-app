package io.github.slflfl12.presentation.util

import io.github.slflfl12.domain.model.KeywordModel
import io.github.slflfl12.domain.model.Model
import io.github.slflfl12.domain.model.ReviewModel
import io.github.slflfl12.domain.model.VideoModel
import io.github.slflfl12.presentation.mapper.KeywordPresentationMapper
import io.github.slflfl12.presentation.mapper.PresentationMapper
import io.github.slflfl12.presentation.model.KeywordPresentationModel
import io.github.slflfl12.presentation.model.ReviewPresentationModel
import io.github.slflfl12.presentation.model.VideoPresentationModel


inline fun <reified T> handleMovieDetail(
    list: List<T>,
    crossinline onKeyword: (List<KeywordModel>) -> Unit,
    crossinline onReview: (List<T>) -> Unit,
    crossinline onVideo: (List<T>) -> Unit
) {
    when (T::class) {
        KeywordModel::class -> {
            onKeyword.invoke(list as List<KeywordModel>)
        }
        ReviewModel::class -> onReview.invoke(list)
        VideoModel::class -> onVideo.invoke(list)
    }
}


