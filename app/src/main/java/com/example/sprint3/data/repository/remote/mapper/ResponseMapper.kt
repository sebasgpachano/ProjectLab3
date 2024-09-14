package com.example.sprint3.data.repository.remote.mapper

fun interface ResponseMapper<E, M> {
    fun fromResponse(response: E): M
}