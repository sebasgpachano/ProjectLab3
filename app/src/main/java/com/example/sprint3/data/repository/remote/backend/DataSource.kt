package com.example.sprint3.data.repository.remote.backend

import com.example.sprint3.data.domain.model.character.CharacterDetailModel
import com.example.sprint3.data.domain.model.character.CharacterModel
import com.example.sprint3.data.repository.remote.response.BaseResponse
import kotlinx.coroutines.flow.Flow

interface DataSource {

    fun getCharacters(): Flow<BaseResponse<List<CharacterModel>>>

    fun getCharacter(id: Int): Flow<BaseResponse<CharacterDetailModel>>
}