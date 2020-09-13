package io.github.slflfl12.movieapp.extensions

import android.R
import android.net.Uri
import android.os.Build


fun checkIsMaterialVersion() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP

fun getURLForResource(resourceId: Int): String? {
    return Uri.parse("android.resource://" + R::class.java.getPackage().name + "/" + resourceId)
        .toString()
}