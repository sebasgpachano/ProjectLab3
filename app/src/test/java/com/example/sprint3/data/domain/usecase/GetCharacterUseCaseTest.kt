package com.example.sprint3.data.domain.usecase

import com.example.sprint3.data.domain.model.ErrorModel
import com.example.sprint3.data.domain.model.character.CharacterDetailModel
import com.example.sprint3.data.repository.remote.backend.DataProvider
import com.example.sprint3.data.repository.remote.response.BaseResponse
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test


class GetCharacterUseCaseTest {

    private lateinit var getCharacterUseCase: GetCharacterUseCase
    private val dataProvider: DataProvider = mockk()

    @Before
    fun setUp() {
        // Inyecta el mock DataProvider en GetCharacterUseCase
        getCharacterUseCase = GetCharacterUseCase(dataProvider)
    }

    @Test
    fun `invoke should return success response`() = runBlocking {
        // Prepara el modelo de personaje que esperas recibir
        val characterId = 1
        val characterDetail = CharacterDetailModel(
            id = characterId,
            name = "Rick Sanchez",
            image = "https://image-url.com",
            status = "Alive",
            species = "Human",
            gender = "Male"
        )

        // Configura el mock para devolver el flujo correcto
        coEvery { dataProvider.getCharacter(characterId) } returns flowOf(
            BaseResponse.Success(
                characterDetail
            )
        )

        // Ejecuta el caso de uso
        val response = getCharacterUseCase(characterId).first()

        // Verifica que el resultado es el esperado
        assertTrue(response is BaseResponse.Success)
        assertEquals(characterDetail, (response as BaseResponse.Success).data)
    }

    @Test
    fun `invoke should return error response`() = runBlocking {
        // Prepara un ID de personaje y un mensaje de error esperado
        val characterId = 1
        val errorMessage = "Character not found"
        val errorModel = ErrorModel(errorMessage)

        // Configura el mock para devolver un error
        coEvery { dataProvider.getCharacter(characterId) } returns flowOf(
            BaseResponse.Error(
                errorModel
            )
        )

        // Ejecuta el caso de uso
        val response = getCharacterUseCase(characterId).first()

        // Verifica que el resultado es un error y que el mensaje es correcto
        assertTrue(response is BaseResponse.Error)
        assertEquals(errorModel, (response as BaseResponse.Error).error)
    }
}