package io.github.slflfl12.movieapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import io.github.slflfl12.movieapp.R
import io.github.slflfl12.movieapp.databinding.ItemMovieDetailBinding
import io.github.slflfl12.movieapp.viewholders.MovieDetailViewHolder
import io.github.slflfl12.presentation.model.MoviePresentationModel

class MovieDetailAdapter: RecyclerView.Adapter<MovieDetailViewHolder>() {

    private val items = mutableListOf<MoviePresentationModel>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieDetailViewHolder {
        val binding = DataBindingUtil.inflate<ItemMovieDetailBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_movie_detail, parent, false
        )


        return MovieDetailViewHolder(binding)
    }

    override fun onBindViewHolder(holderDetail: MovieDetailViewHolder, position: Int) {
        holderDetail.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    fun setMovieList(list: List<MoviePresentationModel>) {
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }


}