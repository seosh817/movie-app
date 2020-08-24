package io.github.slflfl12.movieapp.ui.movie

import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import io.github.slflfl12.movieapp.databinding.ItemMovieBinding
import io.github.slflfl12.presentation.model.MoviePresentationModel

class MovieViewHolder(private val binding: ItemMovieBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(movie: MoviePresentationModel) {
        binding.movie = movie
        with(binding) {
            ViewCompat.setTransitionName(layoutPosterTitle, movie.title)
            palette = layoutPosterTitle
            executePendingBindings()
        }

    }
}