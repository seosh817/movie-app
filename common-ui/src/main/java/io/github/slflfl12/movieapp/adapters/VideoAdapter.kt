package io.github.slflfl12.movieapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import io.github.slflfl12.movieapp.R
import io.github.slflfl12.movieapp.databinding.ItemVideoBinding
import io.github.slflfl12.movieapp.viewholders.VideoViewHolder
import io.github.slflfl12.presentation.model.VideoPresentationModel
import io.github.slflfl12.presentation.movie.MovieDetailViewModel

class VideoAdapter(val vm: MovieDetailViewModel): RecyclerView.Adapter<VideoViewHolder>() {

    private val items = mutableListOf<VideoPresentationModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        val binding = DataBindingUtil.inflate<ItemVideoBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_video,
            parent,
            false
        )

        val holder =
            VideoViewHolder(binding)
        holder.itemView.setOnClickListener {
            vm.onVideoItemClick(items[holder.adapterPosition])
        }
        return holder
    }

    fun setVideoList(list: List<VideoPresentationModel>) {
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size


}