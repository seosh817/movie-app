package io.github.slflfl12.movieapp.ui.search

import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import io.github.slflfl12.movieapp.ui.base.BaseActivity
import io.github.slflfl12.presentation.search.SearchViewModel

@AndroidEntryPoint
class SearchActivity : BaseActivity<SearchViewModel>(){

    override val vm: SearchViewModel by viewModels()
}