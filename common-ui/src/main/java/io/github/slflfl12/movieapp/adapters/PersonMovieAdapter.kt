package io.github.slflfl12.movieapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import io.github.slflfl12.movieapp.R
import io.github.slflfl12.movieapp.databinding.ItemMovieBinding
import io.github.slflfl12.movieapp.databinding.ItemPersonMovieBinding
import io.github.slflfl12.movieapp.viewholders.PersonMovieViewHolder
import io.github.slflfl12.presentation.model.MoviePresentationModel
import io.github.slflfl12.presentation.model.VideoPresentationModel

class PersonMovieAdapter: RecyclerView.Adapter<PersonMovieViewHolder>() {

    private val items = mutableListOf<MoviePresentationModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonMovieViewHolder {
        val binding = DataBindingUtil.inflate<ItemPersonMovieBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_person_movie, parent, false
        )
        return PersonMovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PersonMovieViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    fun setMovieList(list: List<MoviePresentationModel>) {
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }
}