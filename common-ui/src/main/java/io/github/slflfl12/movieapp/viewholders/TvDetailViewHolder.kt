package io.github.slflfl12.movieapp.viewholders

import androidx.recyclerview.widget.RecyclerView
import io.github.slflfl12.movieapp.databinding.ItemTvDetailBinding
import io.github.slflfl12.presentation.model.TvPresentationModel

class TvDetailViewHolder(private val binding: ItemTvDetailBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: TvPresentationModel) {
        with(binding) {
            tv = item
            palette = llPersonTvInfo
            executePendingBindings()
        }

    }
}