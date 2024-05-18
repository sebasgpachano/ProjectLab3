package com.example.sprint3

import retrofit2.Call
import retrofit2.http.GET

interface RickApiClient {
    @GET
    fun getCharacters(): Call<List<RickMortyModel>>
}