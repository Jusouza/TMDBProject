package com.example.tmdbproject.view

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.tmdbproject.R
import com.example.tmdbproject.databinding.ActivityMainBinding
import com.example.tmdbproject.model.MoviesModel
import com.example.tmdbproject.view.adapter.AdapterMovies
import com.example.tmdbproject.viewModel.MoviesViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MoviesViewModel
    private lateinit var adapter: AdapterMovies

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this) [MoviesViewModel::class.java]

        setupRecyclerView()

        viewModel.listMovies.observe(this) {
            adapter.listMovies = it
            adapter.notifyDataSetChanged()
        }

        binding.filterFavorites.setOnClickListener {
            viewModel.getCurrentPlayingMovies()
            changeButtonColor("1")
        }

        binding.filterPopulars.setOnClickListener {
            viewModel.getPopularMovies()
            changeButtonColor("2")
        }

        viewModel.getCurrentPlayingMovies()
    }

    private fun changeButtonColor(button: String) {
        when(button){
            "1" -> {
                binding.filterFavorites.setCardBackgroundColor(resources.getColor(R.color.red))
            }
            "2" -> {
                binding.filterPopulars.setCardBackgroundColor(resources.getColor(R.color.red))
            }
        }
    }

    private fun setupRecyclerView() {
        val layoutManager = GridLayoutManager(this, 3)
        binding.listItems.layoutManager = layoutManager
        adapter = AdapterMovies(this, arrayListOf())
        binding.listItems.adapter = adapter
    }


}