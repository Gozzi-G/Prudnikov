package com.pr.moviekp.base

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.pr.moviekp.MainActivity
import com.pr.moviekp.R

abstract class BaseFragment(@LayoutRes layoutId: Int) : Fragment(layoutId) {

    var isBottomNavigationVisible: Boolean
        get() = (activity as? MainActivity)?.isBottomNavigationVisible ?: false
        set(value) {
            (activity as? MainActivity)?.isBottomNavigationVisible = value
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindToolbar(view)
    }

    private fun bindToolbar(view: View) {
        val toolbar = view.findViewById<Toolbar>(R.id.toolbar)
        if (toolbar != null) {
            toolbar.setupWithNavController(findNavController())
            (activity as? MainActivity)?.setSupportActionBar(toolbar)
        }
    }

    fun setToolbarTitle(title: String?) {
        (activity as? MainActivity)?.supportActionBar?.title = title
    }

}