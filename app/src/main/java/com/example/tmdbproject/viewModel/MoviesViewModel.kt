package com.example.tmdbproject.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tmdbproject.core.Constants
import com.example.tmdbproject.model.MoviesModel
import com.example.tmdbproject.network.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MoviesViewModel: ViewModel() {

    private var _listMovies = MutableLiveData<List<MoviesModel>>()
    val listMovies: LiveData<List<MoviesModel>> = _listMovies

    fun getCurrentPlayingMovies(){
        viewModelScope.launch(Dispatchers.IO){
            val response = RetrofitClient.webService.getCurrentPlayingMovies(Constants.API_KEY)
            withContext(Dispatchers.Main){
                _listMovies.value = response.body()?.results?.sortedByDescending { it.releaseDate }
            }
        }
    }

    fun getPopularMovies(){
        viewModelScope.launch(Dispatchers.IO){
            val response = RetrofitClient.webService.getPopularMovies(Constants.API_KEY)
            withContext(Dispatchers.Main){
                _listMovies.value = response.body()?.results?.sortedByDescending { it.releaseDate }
            }
        }
    }

}