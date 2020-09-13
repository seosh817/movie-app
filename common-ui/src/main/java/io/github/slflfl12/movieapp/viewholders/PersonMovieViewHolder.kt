package io.github.slflfl12.movieapp.viewholders

import androidx.recyclerview.widget.RecyclerView
import io.github.slflfl12.movieapp.databinding.ItemPersonMovieBinding
import io.github.slflfl12.presentation.model.MoviePresentationModel

class PersonMovieViewHolder(private val binding: ItemPersonMovieBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(item: MoviePresentationModel) {
        with(binding) {
            movie = item
            palette = clMovieTitle
            executePendingBindings()
        }
    }
}