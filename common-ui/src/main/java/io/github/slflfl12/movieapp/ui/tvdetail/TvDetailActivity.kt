package io.github.slflfl12.movieapp.ui.tvdetail

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import dagger.hilt.android.AndroidEntryPoint
import io.github.slflfl12.movieapp.R
import io.github.slflfl12.movieapp.databinding.ActivityTvDetailBinding
import io.github.slflfl12.movieapp.extensions.applyTvMaterialTransform
import io.github.slflfl12.movieapp.ui.base.BaseActivity
import io.github.slflfl12.presentation.model.TvPresentationModel
import io.github.slflfl12.presentation.tvdetail.TvDetailViewModel

@AndroidEntryPoint
class TvDetailActivity : BaseActivity<TvDetailViewModel>(){

    override val vm: TvDetailViewModel by viewModels()

    private val binding: ActivityTvDetailBinding by binding(R.layout.activity_tv_detail)

    override fun onCreate(savedInstanceState: Bundle?) {
        applyTvMaterialTransform()
        super.onCreate(savedInstanceState)

        intent?.getParcelableExtra<TvPresentationModel>(KEY_TV)?.let {
            vm.idSubject.onNext(it.id)
        }

        with(binding) {
            lifecycleOwner = this@TvDetailActivity
            activity = this@TvDetailActivity
            vm = this@TvDetailActivity.vm
        }
        initObserve()
    }

    private fun initObserve() {
        vm.tv.observe(this, Observer { tv ->
            binding.tv = tv
        })

        vm.networkError.observe(this, Observer {
            Toast.makeText(this, getString(R.string.network_error_message), Toast.LENGTH_SHORT).show()
        })
    }


    companion object {
        const val KEY_TV = "key_tv"
    }
}