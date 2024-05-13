package com.example.dikiditest.data.modelDto

import com.example.dikiditest.domain.model.BlocksModel
import com.example.dikiditest.domain.model.CatalogModel

data class BlocksModelDto(
    var examples  : String?            = null,
    var catalog   : ArrayList<CatalogModelDto> = arrayListOf(),
    var examples2 : String?            = null

){
    companion object{
        fun blocksDtoToBlocks(blocksModelDto: BlocksModelDto): BlocksModel {
            return BlocksModel(
                examples = blocksModelDto.examples,
                catalog = blocksModelDto.catalog.map {
                    CatalogModelDto.catalogDtoToCatalog(it)
                } as ArrayList<CatalogModel>,
                examples2 = blocksModelDto.examples2

            )
        }
    }
}
