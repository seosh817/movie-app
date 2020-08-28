package io.github.slflfl12.movieapp.viewholders

import androidx.recyclerview.widget.RecyclerView
import io.github.slflfl12.movieapp.databinding.ItemReviewBinding
import io.github.slflfl12.presentation.model.ReviewPresentationModel

class ReviewViewHolder(private val binding: ItemReviewBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(reviewModel: ReviewPresentationModel) {
        binding.apply {
            review = reviewModel
            executePendingBindings()
        }
    }
}