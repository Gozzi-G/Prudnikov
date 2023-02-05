package com.pr.moviekp.presentation.movies.movies.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.pr.moviekp.databinding.ItemMovieBinding
import com.pr.moviekp.databinding.ItemMovieFavouriteBinding
import com.pr.moviekp.domain.models.FilmItem

class MoviesListAdapter(
    val onLongItemClickListener: ((FilmItem) -> Unit)? = null,
    val onItemClickListener: ((FilmItem) -> Unit)? = null
) : ListAdapter<FilmItem, RecyclerView.ViewHolder>(FilmItemDiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            VIEW_TYPE_DEFAULT -> FilmItemViewHolder(
                binding = ItemMovieBinding.inflate(inflater, parent, false),
                onItemLongClick = onLongItemClickListener,
                onItemClick = onItemClickListener
            )
            VIEW_TYPE_FAVOURITE -> FilmItemViewHolderFavourite(
                binding = ItemMovieFavouriteBinding.inflate(inflater, parent, false),
                onItemLongClick = onLongItemClickListener,
                onItemClick = onItemClickListener
            )
            else -> throw RuntimeException("Unknown view type: $viewType")
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        when (holder) {
            is FilmItemViewHolder -> {
                holder.bind(item)
            }
            is FilmItemViewHolderFavourite -> {
                holder.bind(item)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)
        return if (item.isFavourite) {
            VIEW_TYPE_FAVOURITE
        } else {
            VIEW_TYPE_DEFAULT
        }
    }

    companion object {
        const val VIEW_TYPE_DEFAULT = 0
        const val VIEW_TYPE_FAVOURITE = 1

        const val MAX_POOL_SIZE = 30
    }
}