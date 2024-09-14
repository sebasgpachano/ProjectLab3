package com.example.sprint3.data.repository.remote.backend

import com.example.sprint3.data.repository.remote.response.BaseResponse
import com.example.sprint3.data.repository.remote.response.characters.GetCharactersResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CallApiService @Inject constructor(private val apiService: ApiService) : BaseService() {

    suspend fun callGetCharacters(): BaseResponse<List<GetCharactersResponse>> {
        return apiCall { apiService.getCharacters() }
    }

    suspend fun callGetCharacter(id: Int): BaseResponse<GetCharactersResponse> {
        return apiCall { apiService.getCharacter(id) }
    }

}