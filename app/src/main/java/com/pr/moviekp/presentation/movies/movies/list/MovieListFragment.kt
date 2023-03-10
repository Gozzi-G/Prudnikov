package com.pr.moviekp.presentation.movies.movies.list

import android.content.res.Configuration
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.pr.moviekp.R
import com.pr.moviekp.base.BaseFragment
import com.pr.moviekp.base.onClickDebounce
import com.pr.moviekp.base.states.StateLCE
import com.pr.moviekp.base.states.ViewState
import com.pr.moviekp.base.viewBinding
import com.pr.moviekp.databinding.FragmentMovieListBinding
import com.pr.moviekp.presentation.movies.movies.detail.MovieDetailFragment
import com.pr.moviekp.presentation.movies.movies.list.adapter.MoviesListAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieListFragment : BaseFragment(R.layout.fragment_movie_list) {


    private val binding by viewBinding(FragmentMovieListBinding::bind)
    private val viewModel: MovieListViewModel by viewModel()

    private lateinit var viewState: ViewState

    lateinit var filmListAdapter: MoviesListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setToolbarTitle(getString(R.string.movie_list_toolbar_title))
        initViewState()
        setupRecyclerView()
        setupObservers()
        viewModel.onViewCreated()
    }

    private fun initViewState() {
        viewState = ViewState.Builder(binding.rvContent)
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
            filmListAdapter.submitList(it)
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
        val rv = binding.rvContent

        filmListAdapter = MoviesListAdapter(
            onLongItemClickListener = {
                viewModel.addToFavourite(it)
            },
            onItemClickListener = {
                val args = MovieDetailFragment.args(it.filmId)
                findNavController().navigate(
                    R.id.action_movieListFragment_to_movieDetailFragment,
                    args
                )
            }
        )

        rv.adapter = filmListAdapter
        rv.recycledViewPool.setMaxRecycledViews(
            MoviesListAdapter.VIEW_TYPE_DEFAULT,
            MoviesListAdapter.MAX_POOL_SIZE
        )
        rv.recycledViewPool.setMaxRecycledViews(
            MoviesListAdapter.VIEW_TYPE_FAVOURITE,
            MoviesListAdapter.MAX_POOL_SIZE
        )

        val columnsQuantity = when (resources.configuration.orientation) {
            Configuration.ORIENTATION_PORTRAIT -> 1
            Configuration.ORIENTATION_LANDSCAPE -> 2
            else -> 1
        }
        binding.rvContent.layoutManager = GridLayoutManager(activity, columnsQuantity)
        rv.itemAnimator = null
    }

    private fun setupMenu() {
        (requireActivity() as MenuHost).addMenuProvider(object : MenuProvider {
            override fun onPrepareMenu(menu: Menu) {
                // Handle for example visibility of menu items
            }

            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.toolbar_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                // Validate and handle the selected menu item
                return true
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }
}