package com.example.sprint3.data.domain.model


class ErrorModel(
    var error: String = "unknown",
    var errorCode: String = "",
    var message: String = "unknown"
) : BaseModel()