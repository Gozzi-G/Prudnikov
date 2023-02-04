package com.pr.moviekp.presentation.movies.movies.favourite

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.pr.moviekp.R
import com.pr.moviekp.databinding.FragmentFavouriteMovieBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavouriteMovieFragment : Fragment(R.layout.fragment_favourite_movie) {

    private lateinit var binding: FragmentFavouriteMovieBinding
    private val viewModel: FavouriteMovieViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentFavouriteMovieBinding.bind(view)
    }
}