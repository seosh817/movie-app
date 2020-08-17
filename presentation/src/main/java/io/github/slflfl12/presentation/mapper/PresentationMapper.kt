package io.github.slflfl12.presentation.mapper

import io.github.slflfl12.domain.model.Model
import io.github.slflfl12.presentation.model.PresentationModel

interface PresentationMapper<P : PresentationModel, D : Model> {
    fun mapToDomain(from: P): D

    fun mapToPresentation(from: D): P
}