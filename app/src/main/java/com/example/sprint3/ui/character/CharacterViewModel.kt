package com.example.sprint3.ui.character

import androidx.lifecycle.viewModelScope
import com.example.sprint3.data.domain.model.character.CharacterModel
import com.example.sprint3.data.domain.usecase.GetCharactersUseCase
import com.example.sprint3.data.repository.remote.response.BaseResponse
import com.example.sprint3.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(private val getCharactersUseCase: GetCharactersUseCase) :
    BaseViewModel() {

    private val rickListMutableStateFlow = MutableStateFlow<List<CharacterModel>>(arrayListOf())
    val rickStateFlow: StateFlow<List<CharacterModel>> = rickListMutableStateFlow

    init {
        getCharacterList()
    }

    fun getCharacterList() {
        viewModelScope.launch(Dispatchers.IO) {
            loadingMutableSharedFlow.emit(true)
            getCharactersUseCase().collect {
                when (it) {
                    is BaseResponse.Error -> {
                        loadingMutableSharedFlow.emit(false)
                        errorMutableSharedFlow.emit(it.error)
                    }

                    is BaseResponse.Success -> {
                        loadingMutableSharedFlow.emit(false)
                        rickListMutableStateFlow.value = it.data
                    }
                }
            }
        }
    }
}