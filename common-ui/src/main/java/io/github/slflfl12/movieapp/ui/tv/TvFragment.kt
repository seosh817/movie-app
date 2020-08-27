package io.github.slflfl12.movieapp.ui.tv

import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import io.github.slflfl12.movieapp.R
import io.github.slflfl12.movieapp.databinding.FragmentTvBinding
import io.github.slflfl12.movieapp.ui.base.BaseFragment
import io.github.slflfl12.presentation.tv.TvViewModel

@AndroidEntryPoint
class TvFragment: BaseFragment<FragmentTvBinding, TvViewModel>(R.layout.fragment_movie) {

    override val vm: TvViewModel
        get() = TODO("Not yet implemented")


}