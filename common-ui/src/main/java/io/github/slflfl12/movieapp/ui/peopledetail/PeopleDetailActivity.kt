package io.github.slflfl12.movieapp.ui.peopledetail

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import dagger.hilt.android.AndroidEntryPoint
import io.github.slflfl12.movieapp.R
import io.github.slflfl12.movieapp.databinding.ActivityPeopleDetailBinding
import io.github.slflfl12.movieapp.ui.base.BaseActivity
import io.github.slflfl12.presentation.model.PersonPresentationModel
import io.github.slflfl12.presentation.peopledetail.PeopleDetailViewModel

@AndroidEntryPoint
class PeopleDetailActivity : BaseActivity<PeopleDetailViewModel>() {

    override val vm: PeopleDetailViewModel by viewModels()

    private val binding: ActivityPeopleDetailBinding by binding(R.layout.activity_people_detail)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        intent?.getParcelableExtra<PersonPresentationModel>(KEY_PERSON)?.let {
            vm.personSubject.onNext(it)
            binding.person = it
        }

        with(binding) {
            lifecycleOwner = this@PeopleDetailActivity
            activity = this@PeopleDetailActivity
            vm = this@PeopleDetailActivity.vm
        }
        initObserve()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) onBackPressed()
        return false
    }

    private fun initObserve() {

        vm.networkError.observe(this, Observer {
            Toast.makeText(this, getString(R.string.network_error_message), Toast.LENGTH_SHORT)
                .show()
            Log.d("seunghwan", it.toString())
        })
    }



    companion object {
        const val KEY_PERSON = "key_person"
    }


}