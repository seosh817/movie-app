package io.github.slflfl12.movieapp.viewholders

import androidx.recyclerview.widget.RecyclerView
import io.github.slflfl12.movieapp.databinding.ItemMovieDetailBinding
import io.github.slflfl12.presentation.model.MoviePresentationModel

class MovieDetailViewHolder(private val binding: ItemMovieDetailBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(item: MoviePresentationModel) {
        with(binding) {
            movie = item
            palette = llPersonMovieInfo
            executePendingBindings()
        }
    }
}