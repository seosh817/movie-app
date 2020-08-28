package io.github.slflfl12.movieapp.ui.tvdetail

import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import io.github.slflfl12.movieapp.ui.base.BaseActivity
import io.github.slflfl12.presentation.tv.TvDetailViewModel

@AndroidEntryPoint
class TvDetailActivity : BaseActivity<TvDetailViewModel>(){

    override val vm: TvDetailViewModel by viewModels()



    companion object {
        const val KEY_TV = "key_tv"
    }
}