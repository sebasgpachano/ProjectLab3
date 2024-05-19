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

class DetailsViewModel : ViewModel() {

    private val _characterDetails = MutableLiveData<RickMortyModel>()
    val characterDetails: LiveData<RickMortyModel> get() = _characterDetails

    fun fetchItemDetails(id: Int) {
        val retrofit = RetrofitHelper.getRetrofit()
        val rickApiClient = retrofit.create(RickApiClient::class.java)
        val call = rickApiClient.getCharacter("character/$id")

        call.enqueue(object : Callback<RickMortyModel> {
            override fun onResponse(
                call: Call<RickMortyModel>,
                response: Response<RickMortyModel>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    _characterDetails.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<RickMortyModel>, t: Throwable) {
                //Error
            }

        })
    }
}