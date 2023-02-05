package com.pr.moviekp.presentation.movies.movies.list.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.pr.moviekp.R
import com.pr.moviekp.databinding.ItemMovieBinding
import com.pr.moviekp.domain.models.FilmItem

class FilmItemViewHolder(
    private val binding: ItemMovieBinding,
    private val onItemLongClick: ((FilmItem) -> Unit)?,
    private val onItemClick: ((FilmItem) -> Unit)?
) : RecyclerView.ViewHolder(binding.root) {

    private lateinit var item: FilmItem

    init {
        itemView.setOnLongClickListener {
            onItemLongClick?.invoke(item)
            true
        }
        itemView.setOnClickListener {
            onItemClick?.invoke(item)
        }
    }

    fun bind(item: FilmItem) {
        this.item = item
        setViews()
    }

    private fun setViews() {
        binding.tvTitle.text = item.title
        binding.tvGenre.text = item.genres
        binding.tvYear.text = itemView.context.getString(R.string.film_year, item.year)

        Glide.with(binding.root.context)
            .load(item.posterUrl)
            .transition(DrawableTransitionOptions.withCrossFade())
            .placeholder(R.drawable.movie_image_placeholder)
            .centerCrop()
            .into(binding.ivPoster)
    }
}