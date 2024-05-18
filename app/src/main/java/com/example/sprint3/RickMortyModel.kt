package com.example.sprint3

import com.google.gson.annotations.SerializedName

data class RickMortyModel (@SerializedName("name") var name:String,
                           @SerializedName("status") var status:String,
                           @SerializedName("species") var species:String,
                           @SerializedName("gender") var gender:String,
                           @SerializedName("image") var image:String) {
}