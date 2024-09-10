package com.example.sprint3.data.repository.remote.backend

import com.example.sprint3.data.repository.remote.response.characters.RickMortyModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiService {
    @GET("character/[1,2,3,4,5,6,7,8,9,10]")
    suspend fun getCharacters(): Response<List<RickMortyModel>>

    @GET
    suspend fun getCharacter(@Url url: String): Response<RickMortyModel>
}