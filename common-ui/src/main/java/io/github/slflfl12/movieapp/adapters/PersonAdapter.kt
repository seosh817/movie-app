package io.github.slflfl12.movieapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import io.github.slflfl12.movieapp.R
import io.github.slflfl12.movieapp.databinding.ItemPersonBinding
import io.github.slflfl12.movieapp.viewholders.PersonViewHolder
import io.github.slflfl12.presentation.model.PersonPresentationModel

class PersonAdapter : RecyclerView.Adapter<PersonViewHolder>() {

    private val items = mutableListOf<PersonPresentationModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        val binding = DataBindingUtil.inflate<ItemPersonBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_person,
            parent,
            false
        )

        return PersonViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    fun setItems(list: List<PersonPresentationModel>) {
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }

    fun addItems(list: List<PersonPresentationModel>) {
        val preItemSize = items.size
        items.addAll(list)
        notifyItemRangeChanged(preItemSize, list.size-1)
    }


}