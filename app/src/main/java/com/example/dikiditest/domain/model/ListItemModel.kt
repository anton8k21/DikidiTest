package com.example.dikiditest.domain.model

data class ListItemModel(
    var id              : String? = null,
    var name            : String? = null,
    var timeStart       : String? = null,
    var timeStop        : String? = null,
    var publicTimeStart : String? = null,
    var publicTimeStop  : String? = null,
    var discountValue   : String? = null,
    var view            : String? = null,
    var usedCount       : String? = null,
    var companyId       : String? = null,
    var icon            : String? = null,
    var companyName     : String? = null,
    var companyStreet   : String? = null,
    var companyHouse    : String? = null,
    var companyImage    : String? = null
)
