package io.github.slflfl12.movieapp.extensions

import android.os.Build

fun checkIsMaterialVersion() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP
