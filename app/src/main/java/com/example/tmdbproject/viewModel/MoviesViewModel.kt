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

class MoviesViewModel : ViewModel() {

    private var _listMovies = MutableLiveData<List<MoviesModel>>()
    val listMovies: LiveData<List<MoviesModel>> = _listMovies

    private var _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    fun getCurrentPlayingMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = RetrofitClient.webService.getCurrentPlayingMovies(BuildConfig.API_KEY)
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        _listMovies.value = response.body()?.results?.sortedByDescending { it.releaseDate }
                    } else {
                        _errorMessage.value = LOADING_ERROR_MSG_REC
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    _errorMessage.value = NETWORK_ERROR_MSG
                }
            }
        }
    }

    fun getPopularMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = RetrofitClient.webService.getPopularMovies(BuildConfig.API_KEY)
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        _listMovies.value = response.body()?.results?.sortedByDescending { it.releaseDate }
                    } else {
                        _errorMessage.value = LOADING_ERROR_MSG_POP
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    _errorMessage.value = NETWORK_ERROR_MSG
                }
            }
        }
    }

    companion object {
        const val LOADING_ERROR_MSG_REC = "Erro ao carregar filmes recentes"
        const val LOADING_ERROR_MSG_POP = "Erro ao carregar filmes populares"
        const val NETWORK_ERROR_MSG = "Erro de conexão, verifique sua rede"
    }
}