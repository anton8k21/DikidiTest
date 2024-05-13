package com.example.dikiditest.data.modelDto

import com.example.dikiditest.domain.model.CatalogModel


data class CatalogModelDto(
    var id                : String?           = null,
    var name              : String?           = null,
    var image             : ImageModelDto?       = ImageModelDto(),
    var street            : String?           = null,
    var house             : String?           = null,
    var lat               : String?           = null,
    var lng               : String?           = null,
    var categories        : ArrayList<String> = arrayListOf(),
    var categoriesCatalog : ArrayList<String> = arrayListOf(),
    var rating            : Double?              = null,
    var isMaster          : Boolean?          = null,
    var award             : String?           = null,
    var vipTariff         : Boolean?          = null,
    var reviewCount       : String?           = null,
    var backgrounds       : ArrayList<String> = arrayListOf(),
    var masterId          : String?           = null

){
    companion object{
        fun catalogDtoToCatalog(catalogModelDto: CatalogModelDto): CatalogModel {
            return CatalogModel(
                id = catalogModelDto.id,
                name = catalogModelDto.name,
                image = catalogModelDto.image?.let { ImageModelDto.imageDtoToImage(it) },
                street = catalogModelDto.street,
                house = catalogModelDto.house,
                lat = catalogModelDto.lat,
                lng = catalogModelDto.lng,
                categories = catalogModelDto.categories,
                categoriesCatalog = catalogModelDto.categoriesCatalog,
                rating = catalogModelDto.rating,
                isMaster = catalogModelDto.isMaster,
                award = catalogModelDto.award,
                vipTariff = catalogModelDto.vipTariff,
                reviewCount = catalogModelDto.reviewCount,
                backgrounds = catalogModelDto.backgrounds,
                masterId = catalogModelDto.masterId
            )
        }
    }
}

