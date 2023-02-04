package com.pr.moviekp.presentation.movies.movies.favourite.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.pr.moviekp.databinding.ItemMovieFavouriteBinding
import com.pr.moviekp.domain.models.FilmItem
import com.pr.moviekp.presentation.movies.movies.list.adapter.FilmItemDiffCallback
import com.pr.moviekp.presentation.movies.movies.list.adapter.FilmItemViewHolderFavourite

class FilmFavouriteAdapter(
    val onLongItemClickListener: ((FilmItem) -> Unit)? = null,
    val onItemClickListener: ((FilmItem) -> Unit)? = null
) : ListAdapter<FilmItem, RecyclerView.ViewHolder>(FilmItemDiffCallback()) {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return FilmItemViewHolderFavourite(
            binding = ItemMovieFavouriteBinding.inflate(inflater, parent, false),
            onItemLongClick = onLongItemClickListener,
            onItemClick = onItemClickListener
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        when (holder) {
            is FilmItemViewHolderFavourite -> holder.bind(item)
            else -> throw RuntimeException("Unknown ViewHolder: $holder")
        }
    }

}

