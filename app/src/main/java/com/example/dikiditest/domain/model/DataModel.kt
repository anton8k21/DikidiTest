package com.example.dikiditest.domain.model

data class DataModel(
    var title        : String?           = null,
    var image        : String?           = null,
    var catalogCount : String?           = null,
    var blocks       : BlocksModel?           = BlocksModel(),
    var order        : ArrayList<String> = arrayListOf()
)
