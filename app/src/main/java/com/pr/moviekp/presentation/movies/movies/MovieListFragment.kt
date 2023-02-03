package com.pr.moviekp.presentation.movies.movies

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.pr.moviekp.R
import com.pr.moviekp.databinding.FragmentMovieListBinding

class MovieListFragment : Fragment(R.layout.fragment_movie_list) {

    private lateinit var binding: FragmentMovieListBinding

    private lateinit var viewModel: MovieListViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMovieListBinding.bind(view)

        binding.text.setOnClickListener {
            findNavController().navigate(R.id.action_movieListFragment_to_movieDetailFragment)
        }
    }
}