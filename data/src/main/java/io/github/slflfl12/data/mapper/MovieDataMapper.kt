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
            keywords = from.keywords?.map(KeywordDataMapper::mapToDomain),
            reviews = from.reviews?.map(ReviewDataMapper::mapToDomain),
            videos = from.videos?.map(VideoDataMapper::mapToDomain),
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
            keywords = from.keywords?.map(KeywordDataMapper::mapToData),
            reviews = from.reviews?.map(ReviewDataMapper::mapToData),
            videos = from.videos?.map(VideoDataMapper::mapToData),
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