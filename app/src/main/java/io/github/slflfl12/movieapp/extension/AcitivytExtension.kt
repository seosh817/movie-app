package io.github.slflfl12.movieapp.extension

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import io.github.slflfl12.movieapp.R

fun AppCompatActivity.simpleToolbar(toolbar: Toolbar, _title: String = "") {
    setSupportActionBar(toolbar)
    supportActionBar?.run {
        setDisplayHomeAsUpEnabled(true)
        setHomeAsUpIndicator(R.drawable.ic_arrow_back)
        title= _title
    }
}