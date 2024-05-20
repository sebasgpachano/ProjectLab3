package com.example.sprint3.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sprint3.retrofit.RetrofitHelper
import com.example.sprint3.retrofit.RickApiClient
import com.example.sprint3.RickMortyModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CharacterViewModel : ViewModel() {

    private val _rickList = MutableLiveData<List<RickMortyModel>>()
    val rickList: LiveData<List<RickMortyModel>> get() = _rickList

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    fun fetchData() {
        _isLoading.value = true
        val retrofit = RetrofitHelper.getRetrofit()
        val rickApiClient = retrofit.create(RickApiClient::class.java)
        val call = rickApiClient.getCharacters()

        call.enqueue(object : Callback<List<RickMortyModel>> {
            override fun onResponse(
                call: Call<List<RickMortyModel>>,
                response: Response<List<RickMortyModel>>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    _rickList.postValue(response.body())
                }
                _isLoading.value = false
            }

            override fun onFailure(call: Call<List<RickMortyModel>>, t: Throwable) {
                _isLoading.value = false
            }

        })
    }
}