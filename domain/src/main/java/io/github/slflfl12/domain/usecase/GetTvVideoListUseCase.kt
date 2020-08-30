package io.github.slflfl12.domain.usecase

import android.util.Log
import io.github.slflfl12.domain.model.VideoModel
import io.github.slflfl12.domain.repository.TvRepository
import io.reactivex.Single

class GetTvVideoListUseCase(
    private val tvRepository: TvRepository
) : SingleUseCase<List<VideoModel>, Int>() {

    override fun buildUseCaseSingle(params: Int): Single<List<VideoModel>> =
        tvRepository.getLocalTv(params).flatMap { tv ->
            if (tv.videos.isNullOrEmpty()) {
                tvRepository.getVideoList(params).flatMapCompletable {
                    tv.videos = it
                    tvRepository.updateTv(tv)
                }.andThen(tvRepository.getVideoList(params))
            } else {
                Single.just(tv.videos)
            }
        }
}