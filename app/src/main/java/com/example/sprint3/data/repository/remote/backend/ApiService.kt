package com.example.sprint3.data.repository.remote.backend

import com.example.sprint3.data.repository.remote.response.characters.GetCharactersResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("api/character/[1,2,3,4,5,6,7,8,9,10]")
    suspend fun getCharacters(): Response<List<GetCharactersResponse>>

    @GET("api/character/{id}")
    suspend fun getCharacter(@Path("id") id: Int): Response<GetCharactersResponse>
}