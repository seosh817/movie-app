package io.github.slflfl12.movieapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import io.github.slflfl12.movieapp.R
import io.github.slflfl12.movieapp.databinding.ItemTvDetailBinding
import io.github.slflfl12.movieapp.viewholders.TvDetailViewHolder
import io.github.slflfl12.presentation.model.TvPresentationModel

class TvDetailAdapter : RecyclerView.Adapter<TvDetailViewHolder>() {

    private val items = mutableListOf<TvPresentationModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvDetailViewHolder {
        val binding = DataBindingUtil.inflate<ItemTvDetailBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_tv_detail, parent, false
        )
        return TvDetailViewHolder(binding)
    }

    override fun onBindViewHolder(holderDetail: TvDetailViewHolder, position: Int) {
        holderDetail.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    fun setTvList(list: List<TvPresentationModel>) {
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }

}