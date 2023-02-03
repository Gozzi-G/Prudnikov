package com.pr.moviekp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.pr.moviekp.presentation.movies.MoviesViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {


    private val viewModel: MoviesViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.create()
    }
}