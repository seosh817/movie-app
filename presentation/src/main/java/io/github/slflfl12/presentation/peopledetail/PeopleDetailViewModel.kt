package io.github.slflfl12.presentation.peopledetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.github.slflfl12.presentation.base.BaseViewModel
import io.github.slflfl12.presentation.model.PersonPresentationModel
import io.reactivex.subjects.BehaviorSubject

class PeopleDetailViewModel: BaseViewModel() {


    var idSubject: BehaviorSubject<Int> = BehaviorSubject.create()

    private val _person = MutableLiveData<PersonPresentationModel>()
    val person: LiveData<PersonPresentationModel>
        get() = _person


}