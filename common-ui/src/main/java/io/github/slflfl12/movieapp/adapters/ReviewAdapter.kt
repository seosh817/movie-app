package io.github.slflfl12.movieapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import io.github.slflfl12.movieapp.R
import io.github.slflfl12.movieapp.databinding.ItemReviewBinding
import io.github.slflfl12.movieapp.viewholders.ReviewViewHolder
import io.github.slflfl12.presentation.model.ReviewPresentationModel
import io.github.slflfl12.presentation.moviedetail.MovieDetailViewModel

class ReviewAdapter(val vm: MovieDetailViewModel): RecyclerView.Adapter<ReviewViewHolder>() {

    private val items = mutableListOf<ReviewPresentationModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        val binding = DataBindingUtil.inflate<ItemReviewBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_review,
            parent,
            false
        )
        val holder =
            ReviewViewHolder(binding)
        return holder
    }

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun setReviewList(list: List<ReviewPresentationModel>) {
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = items.size

}