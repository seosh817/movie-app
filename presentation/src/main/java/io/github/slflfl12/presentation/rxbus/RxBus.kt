package io.github.slflfl12.presentation.rxbus

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.ofType
import io.reactivex.subjects.BehaviorSubject

object RxBus {

    val movieBehaviorSubject: BehaviorSubject<RxBusEvent> = BehaviorSubject.create()

    val tvBehaviorSubject: BehaviorSubject<RxBusEvent> = BehaviorSubject.create()

    val compositeDisposable: CompositeDisposable = CompositeDisposable()

    fun movieOnNext(event: RxBusEvent) {
        movieBehaviorSubject.onNext(event)
    }

    fun tvOnNext(event: RxBusEvent) {
        tvBehaviorSubject.onNext(event)
    }


    inline fun <reified T : RxBusEvent> movieSubscribe(crossinline onNext: (T) -> Unit): Disposable =
        movieBehaviorSubject
            .ofType<T>()
            .subscribe {
                onNext(it)
            }

    inline fun <reified T : RxBusEvent> tvSubscribe(crossinline onNext: (T) -> Unit): Disposable =
        tvBehaviorSubject
            .ofType<T>()
            .subscribe {
                onNext(it)
            }
}
