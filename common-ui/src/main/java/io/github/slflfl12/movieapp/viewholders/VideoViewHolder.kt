package io.github.slflfl12.movieapp.viewholders

import androidx.recyclerview.widget.RecyclerView
import io.github.slflfl12.movieapp.databinding.ItemVideoBinding
import io.github.slflfl12.presentation.model.VideoPresentationModel

class VideoViewHolder(private val binding:ItemVideoBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(videoModel: VideoPresentationModel) {
        binding.apply {
            video = videoModel
            palette = layoutVideoTitle
            executePendingBindings()
        }
    }
}