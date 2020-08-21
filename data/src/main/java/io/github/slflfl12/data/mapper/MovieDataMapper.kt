package io.github.slflfl12.data.mapper

import io.github.slflfl12.data.model.KeywordData
import io.github.slflfl12.data.model.MovieData
import io.github.slflfl12.data.model.ReviewData
import io.github.slflfl12.data.model.VideoData
import io.github.slflfl12.domain.model.KeywordModel
import io.github.slflfl12.domain.model.MovieModel
import io.github.slflfl12.domain.model.ReviewModel
import io.github.slflfl12.domain.model.VideoModel

object MovieDataMapper : DataMapper<MovieData, MovieModel> {
    override fun mapToDomain(from: MovieData): MovieModel {
        return MovieModel(
            page = from.page,
            keywords = from.keywords.map { KeywordModel(id = it.id, name = it.name) },
            reviews = from.reviews.map {
                ReviewModel(
                    id = it.id,
                    author = it.author,
                    content = it.content,
                    url = it.url
                )
            },
            videos = from.videos.map {
                VideoModel(
                    id = it.id,
                    key = it.key,
                    name = it.name,
                    site = it.site,
                    size = it.size,
                    type = it.type
                )
            },
            adult = from.adult,
            backdropPath = from.backdropPath,
            genreIds = from.genreIds,
            id = from.id,
            originalLanguage = from.originalLanguage,
            originalTitle = from.originalTitle,
            overview = from.overview,
            popularity = from.popularity,
            posterPath = from.posterPath,
            releaseDate = from.releaseDate,
            title = from.title,
            video = from.video,
            voteAverage = from.voteAverage,
            voteCount = from.voteCount,
            favorite = from.favorite
        )
    }

    override fun mapToData(from: MovieModel): MovieData {
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
            backdropPath = from.backdropPath,
            genreIds = from.genreIds,
            id = from.id,
            originalLanguage = from.originalLanguage,
            originalTitle = from.originalTitle,
            overview = from.overview,
            popularity = from.popularity,
            posterPath = from.posterPath,
            releaseDate = from.releaseDate,
            title = from.title,
            video = from.video,
            voteAverage = from.voteAverage,
            voteCount = from.voteCount,
            favorite = from.favorite
        )
    }
}