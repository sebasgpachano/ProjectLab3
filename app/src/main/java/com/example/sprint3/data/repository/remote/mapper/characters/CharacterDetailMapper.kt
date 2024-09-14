package com.example.sprint3.data.repository.remote.mapper.characters

import com.example.sprint3.data.domain.model.character.CharacterDetailModel
import com.example.sprint3.data.repository.remote.mapper.ResponseMapper
import com.example.sprint3.data.repository.remote.response.characters.GetCharactersResponse

class CharacterDetailMapper : ResponseMapper<GetCharactersResponse, CharacterDetailModel> {
    override fun fromResponse(response: GetCharactersResponse): CharacterDetailModel {
        return CharacterDetailModel(
            id = response.id,
            name = response.name,
            image = response.image,
            status = response.status,
            species = response.species,
            gender = response.gender
        )
    }
}