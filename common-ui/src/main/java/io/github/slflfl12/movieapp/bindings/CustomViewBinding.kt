package io.github.slflfl12.movieapp.bindings

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.BindingAdapter
import com.ms.square.android.expandabletextview.ExpandableTextView
import io.github.slflfl12.movieapp.extensions.simpleToolbar

@BindingAdapter("bindActivity", "bindTitle")
fun bindToolbar(toolbar: Toolbar, activity: AppCompatActivity, _title: String?) {
    if (_title != null) {
        activity.simpleToolbar(toolbar, _title)
    }
}

@BindingAdapter("bindExpandableText")
fun bindExpandableText(expandableTextView: ExpandableTextView, content: String?) {
    expandableTextView.text = content
}