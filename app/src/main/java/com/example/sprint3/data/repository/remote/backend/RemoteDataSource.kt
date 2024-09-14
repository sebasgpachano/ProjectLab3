package com.example.sprint3.data.repository.remote.backend

import com.example.sprint3.data.domain.model.character.CharacterModel
import com.example.sprint3.data.repository.remote.mapper.characters.CharacterMapper
import com.example.sprint3.data.repository.remote.response.BaseResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val callApiService: CallApiService,
) : BaseService(), DataSource {

    override fun getCharacters(): Flow<BaseResponse<List<CharacterModel>>> = flow {
        val apiResult = callApiService.callGetCharacters()
        if (apiResult is BaseResponse.Success) {
            emit(BaseResponse.Success(CharacterMapper().fromResponse(apiResult.data)))
        } else if (apiResult is BaseResponse.Error) {
            emit(BaseResponse.Error(apiResult.error))
        }
    }
}