package io.github.slflfl12.movieapp.viewholders

import androidx.recyclerview.widget.RecyclerView
import io.github.slflfl12.movieapp.databinding.ItemPersonTvBinding
import io.github.slflfl12.presentation.model.TvPresentationModel

class PersonTvViewHolder(private val binding: ItemPersonTvBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: TvPresentationModel) {
        with(binding) {
            tv = item
            palette = clTvTitle
            executePendingBindings()
        }

    }
}