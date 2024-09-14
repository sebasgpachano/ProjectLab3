package com.example.sprint3.data.domain.model.character

import com.example.sprint3.data.domain.model.BaseModel
import kotlinx.parcelize.Parcelize

@Parcelize
data class CharacterModel(
    val id: Int,
    val name: String,
    val image: String
) : BaseModel()