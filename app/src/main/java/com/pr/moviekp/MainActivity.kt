package com.pr.moviekp

import android.app.Activity
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.unit.dp
import androidx.core.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.pr.moviekp.base.dp
import com.pr.moviekp.databinding.ActivityMainBinding

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
            onNavControllerActivated(f.findNavController())
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
//        setStatusBarTransparent()
    }

    private fun setStatusBarTransparent() {
//        window.statusBarColor = Color.TRANSPARENT
//        WindowCompat.setDecorFitsSystemWindows(window, false)

        makeStatusBarTransparent()
//        binding.root.setMarginTop(100)

//        window.apply {
//            clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
//            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
//            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//            statusBarColor = Color.TRANSPARENT
//
//            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
//
//            ViewCompat.setOnApplyWindowInsetsListener(
//                binding.root) { _, insets ->
//                //and so on for left and right insets
//                insets.consumeSystemWindowInsets()
//            }
//
//            ViewCompat.setOnApplyWindowInsetsListener(
//                binding.root) { _, insets ->
//                val menu = findViewById<FloatingActionButton>(R.id.fab_back_or_menu)
//                val menuLayoutParams = menu.layoutParams as ViewGroup.MarginLayoutParams
//                menuLayoutParams.setMargins(0, insets.systemWindowInsetTop, 0, 0)
//                menu.layoutParams = menuLayoutParams
//                insets.consumeSystemWindowInsets()
//            }
//
//        }
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


    private fun onNavControllerActivated(navController: NavController) {
        if (this.navController == navController) return
        this.navController?.removeOnDestinationChangedListener(destinationListener)
        navController.addOnDestinationChangedListener(destinationListener)
        this.navController = navController
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

fun Activity.makeStatusBarTransparent() {
    window.apply {
        clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        statusBarColor = Color.TRANSPARENT
    }
}

fun View.setMarginTop(marginTop: Int) {
    val menuLayoutParams = this.layoutParams as ViewGroup.MarginLayoutParams
    menuLayoutParams.setMargins(0, marginTop, 0, 0)
    this.layoutParams = menuLayoutParams
}