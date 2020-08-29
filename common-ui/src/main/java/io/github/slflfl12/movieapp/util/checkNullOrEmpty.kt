package io.github.slflfl12.movieapp.util


inline fun <T> List<T>?.doIfNotNullOrEmpty(
    doIfNotNull: (List<T>) -> Unit
) {
    this.doIfNotNullOrEmpty(
        doIfNotNull = { doIfNotNull(it) },
        doIfNull = {})
}

inline fun <T> List<T>?.doIfNotNullOrEmpty(
    doIfNotNull: (List<T>) -> Unit,
    doIfNull: () -> Unit
) {
    if (!this.isNullOrEmpty()) {
        doIfNotNull(this)
    } else {
        doIfNull()
    }
}



inline fun <T> T?.checkNull(
    isNull: (T?) -> Unit,
    notNull: (T) -> Unit
) {
    if (this != null) {
        notNull(this)
    } else {
        isNull(this)
    }
}