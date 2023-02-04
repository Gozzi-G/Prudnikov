package com.pr.moviekp

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.pr.moviekp.base.setMarginTop
import com.pr.moviekp.databinding.ActivityMainBinding
import com.pr.moviekp.presentation.movies.movies.detail.MovieDetailFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var navController: NavController? = null

    private val topLevelDestinations = setOf(getMainIdDestination(), getFavouriteInDestination())

    var isBottomNavigationVisible: Boolean
        get() = binding.bottomNavigationView.isVisible
        set(value) {
            binding.bottomNavigationView.isVisible = value
        }

    // fragment listener is sued for tracking current nav controller
    private val fragmentListener = object : FragmentManager.FragmentLifecycleCallbacks() {
        override fun onFragmentViewCreated(
            fm: FragmentManager,
            f: Fragment,
            v: View,
            savedInstanceState: Bundle?
        ) {
            super.onFragmentViewCreated(fm, f, v, savedInstanceState)
            if (f is MovieDetailFragment) {
                binding.root.setMarginTop(0)
            } else {
                binding.root.setMarginTop(50)
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also { setContentView(it.root) }

        navController = getNavController()
        navController?.let {
            NavigationUI.setupWithNavController(binding.bottomNavigationView, it)
        }
        supportFragmentManager.registerFragmentLifecycleCallbacks(fragmentListener, true)
        setStatusBarTransparent()
    }

    private fun setStatusBarTransparent() {
        window.statusBarColor = Color.TRANSPARENT
        WindowCompat.setDecorFitsSystemWindows(window, false)
    }


    override fun onSupportNavigateUp(): Boolean {
        return navController?.navigateUp() ?: false
    }

    private fun getNavController(): NavController {
        val navHost = supportFragmentManager.findFragmentById(R.id.tabsContainer) as NavHostFragment
        return navHost.navController
    }


    override fun onDestroy() {
        supportFragmentManager.unregisterFragmentLifecycleCallbacks(fragmentListener)
        navController = null
        super.onDestroy()
    }


    private val destinationListener =
        NavController.OnDestinationChangedListener { _, destination, _ ->
            supportActionBar?.setDisplayHomeAsUpEnabled(!isStartDestination(destination))
        }

    private fun isStartDestination(destination: NavDestination?): Boolean {
        if (destination == null) return false
        val graph = destination.parent ?: return false
        val startDestinations = topLevelDestinations + graph.startDestinationId
        return startDestinations.contains(destination.id)
    }

    private fun getMainIdDestination(): Int = R.id.main
    private fun getFavouriteInDestination(): Int = R.id.favourite
}