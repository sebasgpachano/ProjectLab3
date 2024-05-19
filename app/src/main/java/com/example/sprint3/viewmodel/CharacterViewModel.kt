package com.example.sprint3.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sprint3.RetrofitHelper
import com.example.sprint3.RickApiClient
import com.example.sprint3.RickMortyModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CharacterViewModel : ViewModel() {

    private val _rickList = MutableLiveData<List<RickMortyModel>>()
    val rickList: LiveData<List<RickMortyModel>> get() = _rickList
    fun fetchData() {
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
            }

            override fun onFailure(call: Call<List<RickMortyModel>>, t: Throwable) {
                //Error
            }

        })
    }
}