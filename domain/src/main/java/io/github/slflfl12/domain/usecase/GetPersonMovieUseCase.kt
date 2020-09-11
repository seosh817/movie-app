package io.github.slflfl12.domain.usecase

import io.github.slflfl12.domain.model.MovieModel
import io.github.slflfl12.domain.model.PersonModel
import io.github.slflfl12.domain.repository.PeopleRepository
import io.reactivex.Single

class GetPersonMovieUseCase(
    private val peopleRepository: PeopleRepository
): SingleUseCase<List<MovieModel>, Int>() {

    override fun buildUseCaseSingle(params: Int): Single<List<MovieModel>> {
        return peopleRepository.getPersonMovie(params)
    }
}