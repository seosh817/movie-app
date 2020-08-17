package io.github.slflfl12.movieapp.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import dagger.hilt.android.AndroidEntryPoint
import io.github.slflfl12.movieapp.R
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        with(view_pager) {
            adapter = MainPagerAdapter(supportFragmentManager)
            offscreenPageLimit = 3
            addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
                override fun onPageScrollStateChanged(state: Int) = Unit

                override fun onPageScrolled(
                    position: Int,
                    positionOffset: Float,
                    positionOffsetPixels: Int
                ) = Unit

                override fun onPageSelected(position: Int) {
                    bnv_main.menu.getItem(position).isChecked = true
                }
            })
        }

        bnv_main.setOnNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.action_movie -> view_pager.currentItem = 0
                R.id.action_tv -> view_pager.currentItem = 1
                R.id.action_star -> view_pager.currentItem = 2
            }
            true
        }
    }
}