package io.github.slflfl12.movieapp.viewholders

import androidx.recyclerview.widget.RecyclerView
import io.github.slflfl12.movieapp.databinding.ItemPersonBinding
import io.github.slflfl12.presentation.model.PersonPresentationModel

class PersonViewHolder(private val binding: ItemPersonBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(item: PersonPresentationModel) {
        with(binding) {
            person = item
            executePendingBindings()
        }

    }
}