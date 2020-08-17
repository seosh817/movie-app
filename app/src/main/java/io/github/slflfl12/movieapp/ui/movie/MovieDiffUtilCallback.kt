package io.github.slflfl12.movieapp.ui.movie

import androidx.recyclerview.widget.DiffUtil
import io.github.slflfl12.presentation.model.MoviePresentationModel

class MovieDiffUtilCallback(
    private val oldList: List<MoviePresentationModel>,
    private val newList: List<MoviePresentationModel>
) : DiffUtil.Callback(){
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }


    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        areItemsTheSame(oldItemPosition, newItemPosition)

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

}