package com.example.sprint3.ui.character

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sprint3.retrofit.RetrofitHelper
import com.example.sprint3.retrofit.RickApiClient
import com.example.sprint3.data.RickMortyModel
import kotlinx.coroutines.launch
import java.lang.Exception

class CharacterViewModel : ViewModel() {

    private val _rickList = MutableLiveData<List<RickMortyModel>>()
    val rickList: LiveData<List<RickMortyModel>> get() = _rickList

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    init {
        fetchData()
    }

    fun fetchData() {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val retrofit = RetrofitHelper.getRetrofit()
                val rickApiClient = retrofit.create(RickApiClient::class.java)
                val call = rickApiClient.getCharacters()
                _rickList.value = call.body()
            } catch (e: Exception) {
                _error.value = (e.message ?: "Error desconocido")
            } finally {
                _isLoading.value = false
            }
        }
    }
}