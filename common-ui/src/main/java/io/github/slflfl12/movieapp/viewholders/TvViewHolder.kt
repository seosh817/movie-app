package io.github.slflfl12.movieapp.viewholders

import androidx.recyclerview.widget.RecyclerView
import io.github.slflfl12.movieapp.databinding.ItemTvBinding
import io.github.slflfl12.presentation.model.TvPresentationModel

class TvViewHolder(private val binding: ItemTvBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(tv: TvPresentationModel) {
        binding.tv = tv
        with(binding) {
            ivTvPoster.transitionName = tv.name
            palette = layoutPosterTitle
            executePendingBindings()
        }
    }
}