package io.github.slflfl12.movieapp.util

inline fun <T> T.checkEmptyAct(
    isEmpty: (T) -> Unit,
    notEmpty: (T) -> Unit
) {
    if(this!!.equals("")) {
        isEmpty(this)
    } else {
        notEmpty(this)
    }
}

inline fun <T> T?.chekNull(
    isNull: (T?) -> Unit,
    notNull: (T) -> Unit
) {
    if(this != null) {
        notNull(this)
    } else {
        isNull(this)
    }
}