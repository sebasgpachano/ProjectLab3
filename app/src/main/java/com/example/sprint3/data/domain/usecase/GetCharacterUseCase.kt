package com.example.sprint3.data.domain.usecase

import com.example.sprint3.data.domain.model.character.CharacterDetailModel
import com.example.sprint3.data.repository.remote.backend.DataProvider
import com.example.sprint3.data.repository.remote.response.BaseResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCharacterUseCase @Inject constructor(private val dataProvider: DataProvider) {
    operator fun invoke(id: Int): Flow<BaseResponse<CharacterDetailModel>> {
        return dataProvider.getCharacter(id)
    }
}