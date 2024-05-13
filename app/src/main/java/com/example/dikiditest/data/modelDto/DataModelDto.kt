package com.example.dikiditest.data.modelDto

import com.example.dikiditest.domain.model.DataModel

data class DataModelDto(
    var title        : String?           = null,
    var image        : String?           = null,
    var catalogCount : String?           = null,
    var blocks       : BlocksModelDto?           = BlocksModelDto(),
    var order        : ArrayList<String> = arrayListOf()
){
    companion object{
        fun dataDtoToData(dataModelDto: DataModelDto): DataModel {
            return DataModel(
                title = dataModelDto.title,
                image = dataModelDto.image,
                catalogCount = dataModelDto.catalogCount,
                blocks = dataModelDto.blocks?.let { BlocksModelDto.blocksDtoToBlocks(it) },
                order = dataModelDto.order
            )
        }
    }
}
