package com.pr.moviekp.presentation.movies.movies.list.adapter

import androidx.recyclerview.widget.DiffUtil
import com.pr.moviekp.domain.models.FilmItem

class FilmItemDiffCallback : DiffUtil.ItemCallback<FilmItem>() {
    override fun areItemsTheSame(oldItem: FilmItem, newItem: FilmItem): Boolean {
        return oldItem.filmId == newItem.filmId
    }

    override fun areContentsTheSame(oldItem: FilmItem, newItem: FilmItem): Boolean {
        return oldItem == newItem
    }
}