package com.example.dikiditest.domain.model

import com.example.dikiditest.domain.model.DataModel
import com.example.dikiditest.domain.model.ErrorModel

data class HomeInfoModel(
    val error: ErrorModel? = ErrorModel(),
    val data: DataModel? = DataModel()
)