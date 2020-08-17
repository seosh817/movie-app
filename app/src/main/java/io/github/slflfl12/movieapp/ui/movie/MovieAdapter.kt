package io.github.slflfl12.movieapp.ui.movie

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import io.github.slflfl12.movieapp.R
import io.github.slflfl12.movieapp.databinding.ItemMovieBinding
import io.github.slflfl12.presentation.model.MoviePresentationModel
import io.github.slflfl12.presentation.movie.MovieViewModel

class MovieAdapter(
    private val vm: MovieViewModel
) : RecyclerView.Adapter<MovieViewHolder>() {


    private val items = mutableListOf<MoviePresentationModel>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = DataBindingUtil.inflate<ItemMovieBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_movie, parent, false
        )
        return MovieViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun setItems(list: List<MoviePresentationModel>) {
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }

    fun addItems(list: List<MoviePresentationModel>) {
        val preItemSize = items.size
        items.addAll(list)
        notifyItemRangeChanged(preItemSize, list.size)
    }


}