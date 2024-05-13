package com.example.dikiditest.domain.model


data class CatalogModel(
    var id                : String?           = null,
    var name              : String?           = null,
    var image             : ImageModel?       = ImageModel(),
    var street            : String?           = null,
    var house             : String?           = null,
    var lat               : String?           = null,
    var lng               : String?           = null,
    var categories        : ArrayList<String> = arrayListOf(),
    var categoriesCatalog : ArrayList<String> = arrayListOf(),
    var rating            : Double?           = null,
    var isMaster          : Boolean?          = null,
    var award             : String?           = null,
    var vipTariff         : Boolean?          = null,
    var reviewCount       : String?           = null,
    var backgrounds       : ArrayList<String> = arrayListOf(),
    var masterId          : String?           = null

)

