package com.example.sprint3.data.domain.usecase

import app.cash.turbine.test
import com.example.sprint3.data.domain.model.ErrorModel
import com.example.sprint3.data.domain.model.character.CharacterModel
import com.example.sprint3.data.repository.remote.backend.DataProvider
import com.example.sprint3.data.repository.remote.response.BaseResponse
import io.mockk.clearMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import kotlin.time.ExperimentalTime


class GetCharactersUseCaseTest {

    private lateinit var dataProvider: DataProvider
    private lateinit var getCharactersUseCase: GetCharactersUseCase

    @Before
    fun setUp() {
        dataProvider = mockk()
        getCharactersUseCase = GetCharactersUseCase(dataProvider)
    }

    @After
    fun tearDown() {
        clearMocks(dataProvider)
    }

    @OptIn(ExperimentalTime::class)
    @Test
    fun `invoke should return success response with characters list`() = runTest {
        // Datos de prueba
        val fakeCharacters = listOf(
            CharacterModel(1, "Rick", "Human"),
            CharacterModel(2, "Morty", "Human")
        )

        // Simular comportamiento del DataProvider
        coEvery { dataProvider.getCharacters() } returns flow {
            emit(BaseResponse.Success(fakeCharacters))
        }

        // Ejecutar el caso de uso
        getCharactersUseCase().test {
            val response = awaitItem()
            assert(response is BaseResponse.Success)
            assertEquals(fakeCharacters, (response as BaseResponse.Success).data)
            awaitComplete()
        }

        // Verificar que se llamó al método getCharacters
        coVerify { dataProvider.getCharacters() }
    }

    @OptIn(ExperimentalTime::class)
    @Test
    fun `invoke should return error response`() = runTest {

        val errorMessage = "Failed to load characters"
        val errorModel = ErrorModel(errorMessage)

        // Simular comportamiento del DataProvider con un error
        coEvery { dataProvider.getCharacters() } returns flow {
            emit(BaseResponse.Error(errorModel))
        }

        // Ejecutar el caso de uso
        getCharactersUseCase().test {
            val response = awaitItem()
            assert(response is BaseResponse.Error)
            assertEquals(errorModel, (response as BaseResponse.Error).error)
            awaitComplete()
        }

        // Verificar que se llamó al método getCharacters
        coVerify { dataProvider.getCharacters() }
    }
}