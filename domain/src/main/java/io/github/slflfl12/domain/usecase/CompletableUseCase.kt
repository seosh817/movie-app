package io.github.slflfl12.domain.usecase

import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

abstract class CompletableCase<in Params> {

    protected abstract fun buildUseCaseCompletable(params: Params): Completable

    operator fun invoke(params: Params): Completable = buildUseCaseCompletable(params)
        .subscribeOn(Schedulers.io())



}