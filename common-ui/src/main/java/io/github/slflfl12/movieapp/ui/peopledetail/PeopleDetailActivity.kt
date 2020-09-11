package io.github.slflfl12.movieapp.ui.peopledetail

import android.os.Bundle
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import io.github.slflfl12.movieapp.R
import io.github.slflfl12.movieapp.databinding.ActivityPeopleDetailBinding
import io.github.slflfl12.movieapp.ui.base.BaseActivity
import io.github.slflfl12.presentation.peopledetail.PeopleDetailViewModel

@AndroidEntryPoint
class PeopleDetailActivity: BaseActivity<PeopleDetailViewModel>() {

    override val vm: PeopleDetailViewModel by viewModels()

    private val binding: ActivityPeopleDetailBinding by binding(R.layout.activity_people_detail)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        intent?.getIntExtra(KEY_PERSON_ID, 1)?.let {
            vm.idSubject.onNext(it)
        }

        with(binding) {
            lifecycleOwner = this@PeopleDetailActivity
            activity = this@PeopleDetailActivity
            vm = this@PeopleDetailActivity.vm
        }
        initObserve()
    }

    private fun initObserve() {
        vm.person.observe(this@PeopleDetailActivity, {
            binding.person = it
        })


    }


    companion object {
        const val KEY_PERSON_ID = "key_person_id"
    }


}