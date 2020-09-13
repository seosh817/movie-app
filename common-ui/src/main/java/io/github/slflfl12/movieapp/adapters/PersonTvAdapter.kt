package io.github.slflfl12.movieapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import io.github.slflfl12.movieapp.R
import io.github.slflfl12.movieapp.databinding.ItemPersonTvBinding
import io.github.slflfl12.movieapp.databinding.ItemTvBinding
import io.github.slflfl12.movieapp.viewholders.PersonTvViewHolder
import io.github.slflfl12.presentation.model.TvPresentationModel
import io.github.slflfl12.presentation.model.VideoPresentationModel

class PersonTvAdapter : RecyclerView.Adapter<PersonTvViewHolder>() {

    private val items = mutableListOf<TvPresentationModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonTvViewHolder {
        val binding = DataBindingUtil.inflate<ItemPersonTvBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_person_tv, parent, false
        )

        return PersonTvViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PersonTvViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    fun setTvList(list: List<TvPresentationModel>) {
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }
}