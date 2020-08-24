package io.github.slflfl12.movieapp.util

import androidx.lifecycle.Observer
import io.github.slflfl12.presentation.util.Event

class EventObserver<T>(private val onEventUnhandledContent: (T) -> Unit) : Observer<Event<T>> {
    override fun onChanged(event: Event<T>?) {
        event?.getContentIfNotHandled()?.let { value ->
            onEventUnhandledContent(value)
        }
    }
}