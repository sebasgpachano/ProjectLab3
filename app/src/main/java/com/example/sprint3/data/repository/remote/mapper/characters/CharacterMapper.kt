package com.example.sprint3.data.repository.remote.mapper.characters

import com.example.sprint3.data.domain.model.character.CharacterModel
import com.example.sprint3.data.repository.remote.mapper.ResponseMapper
import com.example.sprint3.data.repository.remote.response.characters.GetCharactersResponse

class CharacterMapper : ResponseMapper<List<GetCharactersResponse>, List<CharacterModel>> {
    override fun fromResponse(response: List<GetCharactersResponse>): List<CharacterModel> {
        val characters = ArrayList<CharacterModel>()
        for (characterResponse in response) {
            val characterModel = CharacterModel(
                id = characterResponse.id,
                name = characterResponse.name,
                image = characterResponse.image
            )
            characters.add(characterModel)
        }
        return characters
    }
}