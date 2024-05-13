package com.example.dikiditest.data.modelDto

import com.example.dikiditest.domain.model.ErrorModel

data class ErrorModelDto(
    var code    : Int?    = null,
    var message : String? = null
){
    companion object{
        fun errorDtoToError(errorDto: ErrorModelDto): ErrorModel {
            return ErrorModel(
                code = errorDto.code,
                message = errorDto.message
            )
        }
    }
}
