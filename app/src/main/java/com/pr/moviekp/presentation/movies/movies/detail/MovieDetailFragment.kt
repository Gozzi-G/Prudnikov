package com.pr.moviekp.presentation.movies.movies.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.pr.moviekp.R
import com.pr.moviekp.base.viewBinding
import com.pr.moviekp.databinding.FragmentMovieDetailBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieDetailFragment : Fragment(R.layout.fragment_movie_detail) {


    private val binding by viewBinding(FragmentMovieDetailBinding::bind)


    private val args: MovieDetailFragmentArgs by navArgs()

    private val viewModel: MovieDetailViewModel by viewModel {
        org.koin.core.parameter.parametersOf(
            args.filmItem.filmId.toString()
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setViews()
        setObservers()
        setBackdrop()
    }

    private fun setObservers() {

        viewModel.filmList.observe(viewLifecycleOwner) {
            binding.filmDescr.text = it
        }

    }

    private fun setViews() {
        binding.filmTitle.text = args.filmItem.title
        binding.generValue.text = args.filmItem.genres?.joinToString(", ")
        binding.countryValue.text = args.filmItem.countries?.joinToString(", ")
    }

    private fun setBackdrop() {
        Glide.with(requireContext())
            .load(args.filmItem.posterUrl)
            .transition(DrawableTransitionOptions.withCrossFade())
            .centerCrop()
            .into(binding.ivMovie)
    }
}
