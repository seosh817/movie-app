package io.github.slflfl12.local.mapper

import io.github.slflfl12.data.model.KeywordData
import io.github.slflfl12.data.model.MovieData
import io.github.slflfl12.data.model.ReviewData
import io.github.slflfl12.data.model.VideoData
import io.github.slflfl12.local.model.KeywordEntity
import io.github.slflfl12.local.model.MovieEntity
import io.github.slflfl12.local.model.ReviewEntity
import io.github.slflfl12.local.model.VideoEntity

object MovieLocalMapper : LocalMapper<MovieEntity, MovieData> {

    override fun mapToData(from: MovieEntity): MovieData {
        return MovieData(
            page = from.page,
            keywords = from.keywords.map { KeywordData(id = it.id, name = it.name) },
            reviews = from.reviews.map {
                ReviewData(
                    id = it.id,
                    author = it.author,
                    content = it.content,
                    url = it.url
                )
            },
            videos = from.videos.map {
                VideoData(
                    id = it.id,
                    key = it.key,
                    name = it.name,
                    site = it.site,
                    size = it.size,
                    type = it.type
                )
            },
            adult = from.adult,
            backdropPath = from.backdrop_path,
            genreIds = from.genre_ids,
            id = from.id,
            originalLanguage = from.original_language,
            originalTitle = from.original_title,
            overview = from.overview,
            popularity = from.popularity,
            posterPath = from.poster_path,
            releaseDate = from.release_date,
            title = from.title,
            video = from.video,
            voteAverage = from.vote_average,
            voteCount = from.vote_count,
            favorite = from.favorite
        )
    }

    override fun mapToLocal(from: MovieData): MovieEntity {
        return MovieEntity(
            page = from.page,
            keywords = from.keywords.map { KeywordEntity(id = it.id, name = it.name) },
            reviews = from.reviews.map {
                ReviewEntity(
                    id = it.id,
                    author = it.author,
                    content = it.content,
                    url = it.url
                )
            },
            videos = from.videos.map {
                VideoEntity(
                    id = it.id,
                    key = it.key,
                    name = it.name,
                    site = it.site,
                    size = it.size,
                    type = it.type
                )
            },
            adult = from.adult,
            backdrop_path = from.backdropPath,
            genre_ids = from.genreIds,
            id = from.id,
            original_language = from.originalLanguage,
            original_title = from.originalTitle,
            overview = from.overview,
            popularity = from.popularity,
            poster_path = from.posterPath,
            release_date = from.releaseDate,
            title = from.title,
            video = from.video,
            vote_average = from.voteAverage,
            vote_count = from.voteCount,
            favorite = from.favorite
        )
    }


}