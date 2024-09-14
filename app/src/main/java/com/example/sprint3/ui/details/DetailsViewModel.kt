package com.example.sprint3.ui.details

import androidx.lifecycle.viewModelScope
import com.example.sprint3.data.domain.model.character.CharacterDetailModel
import com.example.sprint3.data.domain.usecase.GetCharacterUseCase
import com.example.sprint3.data.repository.remote.response.BaseResponse
import com.example.sprint3.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(private val getCharacterUseCase: GetCharacterUseCase) :
    BaseViewModel() {

    private val detailsMutableStateFlow = MutableStateFlow<CharacterDetailModel?>(null)
    val detailsStateFlow: StateFlow<CharacterDetailModel?> = detailsMutableStateFlow

    fun getCharacter(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            loadingMutableSharedFlow.emit(true)
            getCharacterUseCase(id).collect {
                when (it) {
                    is BaseResponse.Error -> {
                        loadingMutableSharedFlow.emit(false)
                        errorMutableSharedFlow.emit(it.error)
                    }

                    is BaseResponse.Success -> {
                        loadingMutableSharedFlow.emit(false)
                        detailsMutableStateFlow.value = it.data
                    }
                }
            }
        }
    }
}