package com.pr.moviekp.presentation.movies.movies.detail

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.pr.moviekp.R
import com.pr.moviekp.base.BaseFragment
import com.pr.moviekp.base.onClickDebounce
import com.pr.moviekp.base.states.StateLCE
import com.pr.moviekp.base.states.ViewState
import com.pr.moviekp.base.viewBinding
import com.pr.moviekp.databinding.FragmentMovieDetailBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieDetailFragment : BaseFragment(R.layout.fragment_movie_detail) {

    companion object {
        private const val ARG_FILM_ID = "ARG_FILM_ID"

        fun args(
            movieId: Int,
        ) = bundleOf(
            ARG_FILM_ID to movieId,
        )
    }

    private val args: Bundle
        get() = requireArguments()

    private val filmId: Int by lazy { args.getInt(ARG_FILM_ID) }


    private val binding by viewBinding(FragmentMovieDetailBinding::bind)

    private val viewModel: MovieDetailViewModel by viewModel {
        org.koin.core.parameter.parametersOf(filmId.toString())
    }

    private lateinit var viewState: ViewState

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        isBottomNavigationVisible = false
        setToolbarTitle(null)
        setHomeButtonEnabled(true)
        initViewState()
        setObservers()
    }

    private fun setObservers() {
        viewModel.filmList.observe(viewLifecycleOwner) {
            binding.filmDescr.text = it.description
            binding.filmTitle.text = it.title
            binding.generValue.text = it.genres
            binding.countryValue.text = it.countries
            setBackdrop(it.posterUrl)
        }

        viewModel.observeStateLCE(viewLifecycleOwner) { state ->
            when (state) {
                is StateLCE.Loading -> {
                    viewState.load()
                }
                is StateLCE.Content -> {
                    viewState.content()
                }
                is StateLCE.Error -> {
                    viewState.error()
                }
            }
        }

    }

    private fun initViewState() {
        viewState = ViewState.Builder(binding.rootLayout)
            .load()
            .error()
            .build()
        viewState.btnRetry?.onClickDebounce {
            viewState.load()
            viewModel.loadFilmById()
        }
    }

    private fun setBackdrop(url: String) {
        Glide.with(requireContext())
            .load(url)
            .transition(DrawableTransitionOptions.withCrossFade())
            .centerCrop()
            .into(binding.ivMovie)
    }
}
