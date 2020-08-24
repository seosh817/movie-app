package io.github.slflfl12.movieapp.extension

import android.os.Build

fun checkIsMaterialVersion() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP
