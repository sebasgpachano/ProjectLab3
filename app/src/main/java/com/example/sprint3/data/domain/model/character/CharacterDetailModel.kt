package com.example.sprint3.data.domain.model.character

import com.example.sprint3.data.domain.model.BaseModel
import kotlinx.parcelize.Parcelize

@Parcelize
data class CharacterDetailModel(
    val id: Int,
    val name: String,
    val image: String,
    val status: String,
    val species: String,
    val gender: String
) : BaseModel()