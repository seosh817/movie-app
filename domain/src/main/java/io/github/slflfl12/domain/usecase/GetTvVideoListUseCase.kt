package io.github.slflfl12.domain.usecase

import android.util.Log
import io.github.slflfl12.domain.model.TvModel
import io.github.slflfl12.domain.model.VideoModel
import io.github.slflfl12.domain.repository.TvRepository
import io.reactivex.Single

class GetTvVideoListUseCase(
    private val tvRepository: TvRepository
) : SingleUseCase<List<VideoModel>, TvModel>() {

    override fun buildUseCaseSingle(params: TvModel): Single<List<VideoModel>> {
        return tvRepository.getLocalTv(params.id).flatMap { tv ->
            if (tv.videos.isNullOrEmpty()) {
                tvRepository.getVideoList(params.id).flatMapCompletable {
                    tv.videos = it
                    tvRepository.updateTv(tv)
                }.andThen(tvRepository.getVideoList(params.id))
            } else {
                tvRepository.getVideoList(params.id)
            }
        }.onErrorResumeNext {
            tvRepository.insertTv(params).andThen(
                Single.just(params)
            ).flatMap { tv ->
                tvRepository.getVideoList(params.id).flatMapCompletable {
                    tv.videos = it
                    tvRepository.updateTv(tv)
                }.andThen(tvRepository.getVideoList(params.id))
            }
        }
    }
}