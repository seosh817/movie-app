package io.github.slflfl12.data.mapper

import io.github.slflfl12.data.model.PersonData
import io.github.slflfl12.domain.model.PersonModel

object PersonDataMapper : DataMapper<PersonData, PersonModel> {
    override fun mapToData(from: PersonModel): PersonData {
        return PersonData(
            page = from.page,
            adult = from.adult,
            personDetailData = from.personDetailModel?.let { PersonDetailDataMapper.mapToData(it) },
            gender = from.gender,
            id = from.id,
            name = from.name,
            popularity = from.popularity,
            profilePath = from.profilePath
        )
    }

    override fun mapToDomain(from: PersonData): PersonModel {
        return PersonModel(
            page = from.page,
            adult = from.adult,
            personDetailModel = from.personDetailData?.let { PersonDetailDataMapper.mapToDomain(it) },
            gender = from.gender,
            id = from.id,
            name = from.name,
            popularity = from.popularity,
            profilePath = from.profilePath
        )
    }
}