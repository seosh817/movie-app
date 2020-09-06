package io.github.slflfl12.local.mapper

import io.github.slflfl12.data.model.PersonData
import io.github.slflfl12.local.model.PersonEntity

object PersonLocalMapper : LocalMapper<PersonEntity, PersonData> {

    override fun mapToData(from: PersonEntity): PersonData {
        return PersonData(
            page = from.page,
            adult = from.adult,
            personDetailData = from.personDetailEntity?.let { PersonDetailLocalMapper.mapToData(it) },
            gender = from.gender,
            id = from.id,
            name = from.name,
            popularity = from.popularity,
            profilePath = from.profilePath
        )
    }

    override fun mapToLocal(from: PersonData): PersonEntity {
        return PersonEntity(
            page = from.page,
            adult = from.adult,
            personDetailEntity = from.personDetailData?.let { PersonDetailLocalMapper.mapToLocal(it) },
            gender = from.gender,
            id = from.id,
            name = from.name,
            popularity = from.popularity,
            profilePath = from.profilePath
        )
    }
}