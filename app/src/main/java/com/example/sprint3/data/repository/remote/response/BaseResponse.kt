package com.example.sprint3.data.repository.remote.response

import com.example.sprint3.data.domain.model.ErrorModel

sealed class BaseResponse<T> {
    class Success<T>(val data: T) : BaseResponse<T>()
    class Error<T>(val error: ErrorModel) : BaseResponse<T>()
}