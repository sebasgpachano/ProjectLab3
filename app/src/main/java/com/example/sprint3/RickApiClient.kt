package com.example.sprint3

import retrofit2.Call
import retrofit2.http.GET

interface RickApiClient {
    @GET("character/[1,2,3,4,5,6,7,8,9,10]")
    fun getCharacters(): Call<List<RickMortyModel>>
}