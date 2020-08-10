package io.github.slflfl12.movieapp.ui.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import io.github.slflfl12.movieapp.ui.movie.MovieFragment
import io.github.slflfl12.movieapp.ui.tv.TvFragment

class MainPagerAdapter(fm: FragmentManager): FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        return when(position) {
            0 -> MovieFragment()
            1 -> TvFragment()
            else -> StarFragment()
        }
    }

    override fun getCount(): Int =3
}