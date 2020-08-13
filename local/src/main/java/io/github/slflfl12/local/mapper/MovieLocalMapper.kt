package io.github.slflfl12.local.mapper

import io.github.slflfl12.data.model.Data
import io.github.slflfl12.data.model.KeywordData
import io.github.slflfl12.data.model.MovieData
import io.github.slflfl12.data.model.VideoData
import io.github.slflfl12.local.model.Entity
import io.github.slflfl12.local.model.MovieEntity

object MovieLocalMapper: LocalMapper<MovieEntity, MovieData> {

    override fun mapToData(from: MovieEntity): MovieData {
        return MovieData(
            page = from.page,
            adult = from.adult,
            backdrop_path = from.backdrop_path,
            genre_ids = from.genre_ids,
            id = from.id,
            original_language = from.original_language,
            original_title = from.original_title,
            overview = from.overview,
            popularity = from.popularity,
            poster_path = from.poster_path,
            release_date = from.release_date,
            title = from.title,
            video = from.video,
            vote_average = from.vote_average,
            vote_count = from.vote_count,
            favorite = from.favorite
        )
    }

    override fun mapToLocal(from: MovieData): MovieEntity {
        return MovieEntity(
            page = from.page,
            adult = from.adult,
            backdrop_path = from.backdrop_path,
            genre_ids = from.genre_ids,
            id = from.id,
            original_language = from.original_language,
            original_title = from.original_title,
            overview = from.overview,
            popularity = from.popularity,
            poster_path = from.poster_path,
            release_date = from.release_date,
            title = from.title,
            video = from.video,
            vote_average = from.vote_average,
            vote_count = from.vote_count,
            favorite = from.favorite
        )
    }


}