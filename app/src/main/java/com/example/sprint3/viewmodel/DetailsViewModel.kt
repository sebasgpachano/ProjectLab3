package com.example.sprint3.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sprint3.retrofit.RetrofitHelper
import com.example.sprint3.retrofit.RickApiClient
import com.example.sprint3.RickMortyModel
import kotlinx.coroutines.launch
import java.lang.Exception

class DetailsViewModel : ViewModel() {

    private val _characterDetails = MutableLiveData<RickMortyModel>()
    val characterDetails: LiveData<RickMortyModel> get() = _characterDetails

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    fun fetchItemDetails(id: Int) {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val retrofit = RetrofitHelper.getRetrofit()
                val rickApiClient = retrofit.create(RickApiClient::class.java)
                val call = rickApiClient.getCharacter("character/$id")
                _characterDetails.value = call.body()
            } catch (e: Exception) {
                null
            } finally {
                _isLoading.value = false
            }
        }
    }
}