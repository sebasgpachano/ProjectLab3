package com.example.sprint3.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sprint3.data.repository.remote.backend.ApiService
import com.example.sprint3.data.repository.remote.response.characters.RickMortyModel
import com.example.sprint3.retrofit.RetrofitHelper
import kotlinx.coroutines.launch

class DetailsViewModel : ViewModel() {

    private val _characterDetails = MutableLiveData<RickMortyModel>()
    val characterDetails: LiveData<RickMortyModel> get() = _characterDetails

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    fun fetchItemDetails(id: Int) {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val retrofit = RetrofitHelper.getRetrofit()
                val rickApiClient = retrofit.create(ApiService::class.java)
                val call = rickApiClient.getCharacter("character/$id")
                _characterDetails.value = call.body()
            } catch (e: Exception) {
                _error.value = (e.message ?: "Error desconocido")
            } finally {
                _isLoading.value = false
            }
        }
    }
}