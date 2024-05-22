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

class CharacterViewModel : ViewModel() {

    private val _rickList = MutableLiveData<List<RickMortyModel>>()
    val rickList: LiveData<List<RickMortyModel>> get() = _rickList

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    fun fetchData() {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val retrofit = RetrofitHelper.getRetrofit()
                val rickApiClient = retrofit.create(RickApiClient::class.java)
                val call = rickApiClient.getCharacters()
                _rickList.value = call.body()
            } catch (e: Exception) {
                null
            } finally {
                _isLoading.value = false
            }
        }
    }
}