package io.github.slflfl12.movieapp.ui.favorite

import android.os.Bundle
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import io.github.slflfl12.movieapp.R
import io.github.slflfl12.movieapp.databinding.ActivityFavoriteBinding
import io.github.slflfl12.movieapp.ui.base.BaseActivity
import io.github.slflfl12.presentation.favorite.FavoriteViewModel

@AndroidEntryPoint
class FavoriteActivity : BaseActivity<FavoriteViewModel>() {

    override val vm: FavoriteViewModel by viewModels()

    private val binding: ActivityFavoriteBinding by binding(R.layout.activity_favorite)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        with(binding) {
            lifecycleOwner = this@FavoriteActivity
            vm = this@FavoriteActivity.vm
        }
    }

}