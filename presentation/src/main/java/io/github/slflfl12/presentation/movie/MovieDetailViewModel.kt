package io.github.slflfl12.presentation.movie

import androidx.hilt.lifecycle.ViewModelInject
import io.github.slflfl12.domain.usecase.GetMovieKeywordListUseCase
import io.github.slflfl12.domain.usecase.GetMovieReviewListUseCase
import io.github.slflfl12.domain.usecase.GetMovieVideoListUseCase
import io.github.slflfl12.presentation.base.BaseViewModel

class MovieDetailViewModel @ViewModelInject constructor(
    private val getMovieKeywordListUseCase: GetMovieKeywordListUseCase,
    private val getMovieReviewListUseCase: GetMovieReviewListUseCase,
    private val getMovieVideoListUseCase: GetMovieVideoListUseCase
): BaseViewModel() {




}