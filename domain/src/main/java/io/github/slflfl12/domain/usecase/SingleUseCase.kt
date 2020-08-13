package io.github.slflfl12.domain.usecase

import io.reactivex.Single
import io.reactivex.schedulers.Schedulers


abstract class SingleUseCase<T, in Params> {

    protected abstract fun buildUseCaseSingle(params: Params): Single<T>

    operator fun invoke(params: Params): Single<T> = buildUseCaseSingle(params)
        .subscribeOn(Schedulers.io())



}