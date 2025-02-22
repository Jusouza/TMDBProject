package com.example.tmdbproject.view

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
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

        viewModel = ViewModelProvider(this)[MoviesViewModel::class.java]

        setupRecyclerView()

        viewModel.listMovies.observe(this) { movies ->
            adapter.listMovies = movies
            adapter.notifyDataSetChanged()
        }

        viewModel.errorMessage.observe(this) { errorMsg ->
            if (!errorMsg.isNullOrEmpty()) {
                Toast.makeText(this, errorMsg, Toast.LENGTH_SHORT).show()
            }
        }

        binding.filterRecently.setOnClickListener {
            viewModel.getCurrentPlayingMovies()
            changeButtonColor(FILTER_REC)
        }

        binding.filterPopulars.setOnClickListener {
            viewModel.getPopularMovies()
            changeButtonColor(FILTER_POP)
        }

        viewModel.getCurrentPlayingMovies()
    }

    private fun changeButtonColor(button: String) {
        when (button) {
            FILTER_REC -> {
                binding.filterRecently.setCardBackgroundColor(resources.getColor(R.color.red))
                binding.filterPopulars.setCardBackgroundColor(resources.getColor(R.color.black))
            }

            FILTER_POP -> {
                binding.filterPopulars.setCardBackgroundColor(resources.getColor(R.color.red))
                binding.filterRecently.setCardBackgroundColor(resources.getColor(R.color.black))
            }
        }
    }

    private fun setupRecyclerView() {
        val layoutManager = GridLayoutManager(this, 3)
        binding.listItems.layoutManager = layoutManager
        adapter = AdapterMovies(this, arrayListOf())
        binding.listItems.adapter = adapter
    }

    companion object {
        const val FILTER_REC = "1"
        const val FILTER_POP = "2"
    }
}