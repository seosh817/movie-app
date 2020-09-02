package io.github.slflfl12.movieapp.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import androidx.viewpager.widget.ViewPager
import com.google.android.material.transition.MaterialContainerTransformSharedElementCallback
import dagger.hilt.android.AndroidEntryPoint
import io.github.slflfl12.movieapp.R
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        window.requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS)
        setExitSharedElementCallback(MaterialContainerTransformSharedElementCallback())
        window.sharedElementsUseOverlay = false

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
                R.id.action_celebrities -> view_pager.currentItem = 2
            }
            true
        }
    }
}