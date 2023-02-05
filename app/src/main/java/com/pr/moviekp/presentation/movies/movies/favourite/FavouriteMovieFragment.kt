package com.pr.moviekp.presentation.movies.movies.favourite

import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.pr.moviekp.R
import com.pr.moviekp.base.BaseFragment
import com.pr.moviekp.base.onClickDebounce
import com.pr.moviekp.base.states.StateLCE
import com.pr.moviekp.base.states.ViewState
import com.pr.moviekp.base.viewBinding
import com.pr.moviekp.databinding.FragmentFavouriteMovieBinding
import com.pr.moviekp.presentation.movies.movies.detail.MovieDetailFragment
import com.pr.moviekp.presentation.movies.movies.favourite.adapter.FilmFavouriteAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavouriteMovieFragment : BaseFragment(R.layout.fragment_favourite_movie) {

    private val binding by viewBinding(FragmentFavouriteMovieBinding::bind)
    private val viewModel: FavouriteMovieViewModel by viewModel()

    private lateinit var viewState: ViewState

    lateinit var filmAdapter: FilmFavouriteAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setToolbarTitle(getString(R.string.movie_fav_toolbar_title))
        initViewState()
        setupRecyclerView()
        setupObservers()
        viewModel.onViewCreated()
    }

    private fun initViewState() {
        viewState = ViewState.Builder(binding.rvFavourites)
            .load()
            .error()
            .build()
        viewState.btnRetry?.onClickDebounce {
            viewState.load()
            viewModel.loadFilms()
        }
    }

    private fun setupObservers() {
        viewModel.filmList.observe(viewLifecycleOwner) {
            filmAdapter.submitList(it)
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
            isBottomNavigationVisible = state is StateLCE.Content
        }
    }

    private fun setupRecyclerView() {
        val rv = binding.rvFavourites

        filmAdapter = FilmFavouriteAdapter(
            onLongItemClickListener = {
                viewModel.deleteFromFavourite(it)
            },
            onItemClickListener = {
                val args = MovieDetailFragment.args(it.filmId)
                findNavController().navigate(R.id.action_favouriteMovieFragment_to_movieDetailFragment2, args)
            }
        )

        rv.adapter = filmAdapter

        val columnsQuantity = when (resources.configuration.orientation) {
            Configuration.ORIENTATION_PORTRAIT -> 1
            Configuration.ORIENTATION_LANDSCAPE -> 2
            else -> 1
        }
        binding.rvFavourites.layoutManager = GridLayoutManager(activity, columnsQuantity)
        rv.itemAnimator = null
    }


}