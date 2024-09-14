package com.example.sprint3.data.repository.remote.backend

import com.example.sprint3.data.domain.model.character.CharacterDetailModel
import com.example.sprint3.data.domain.model.character.CharacterModel
import com.example.sprint3.data.repository.remote.response.BaseResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataProvider @Inject constructor(private val remoteDataSource: RemoteDataSource) :
    DataSource {
    override fun getCharacters(): Flow<BaseResponse<List<CharacterModel>>> {
        return remoteDataSource.getCharacters()
    }

    override fun getCharacter(id: Int): Flow<BaseResponse<CharacterDetailModel>> {
        return remoteDataSource.getCharacter(id)
    }

}