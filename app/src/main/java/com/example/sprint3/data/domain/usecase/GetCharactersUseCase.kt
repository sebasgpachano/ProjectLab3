package com.example.sprint3.data.domain.usecase

import com.example.sprint3.data.domain.model.character.CharacterModel
import com.example.sprint3.data.repository.remote.backend.DataProvider
import com.example.sprint3.data.repository.remote.response.BaseResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCharactersUseCase @Inject constructor(private val dataProvider: DataProvider) {
    operator fun invoke(): Flow<BaseResponse<List<CharacterModel>>> {
        return dataProvider.getCharacters()
    }
}