package com.example.dikiditest.data.modelDto

import com.example.dikiditest.domain.model.HomeInfoModel

data class HomeInfoModelDto(
    val error : ErrorModelDto? = ErrorModelDto(),
    val data  : DataModelDto?  = DataModelDto()
){
    companion object{
        fun homeInfoDtoToHomeInfo(homeInfoDto: HomeInfoModelDto): HomeInfoModel {
            return HomeInfoModel(
                data = homeInfoDto.data?.let { DataModelDto.dataDtoToData(it) },
                error = homeInfoDto.error?.let { ErrorModelDto.errorDtoToError(it) }
            )
        }
    }
}