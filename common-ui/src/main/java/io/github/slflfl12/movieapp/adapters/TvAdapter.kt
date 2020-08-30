package io.github.slflfl12.movieapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import io.github.slflfl12.movieapp.R
import io.github.slflfl12.movieapp.databinding.ItemMovieBinding
import io.github.slflfl12.movieapp.databinding.ItemTvBinding
import io.github.slflfl12.movieapp.viewholders.MovieViewHolder
import io.github.slflfl12.movieapp.viewholders.TvViewHolder
import io.github.slflfl12.presentation.model.MoviePresentationModel
import io.github.slflfl12.presentation.model.TvPresentationModel
import io.github.slflfl12.presentation.tv.TvViewModel

class TvAdapter(
    private val vm: TvViewModel
): RecyclerView.Adapter<TvViewHolder>() {

    private val items = mutableListOf<TvPresentationModel>()



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvViewHolder {
        val binding = DataBindingUtil.inflate<ItemTvBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_tv, parent, false
        )
        val holder = TvViewHolder(binding)
        holder.itemView.setOnClickListener {
            vm.navigateToDetail(items[holder.adapterPosition], binding.ivTvPoster)
        }
        return holder
    }

    override fun onBindViewHolder(holder: TvViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    fun setItems(list: List<TvPresentationModel>) {
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }

    fun addItems(list: List<TvPresentationModel>) {
        val preItemSize = items.size
        items.addAll(list)
        notifyItemRangeChanged(preItemSize, list.size)
    }


}