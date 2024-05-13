package com.example.dikiditest.domain

import com.example.dikiditest.domain.model.CategoryModel
import com.example.dikiditest.domain.model.HomeInfoModel

sealed class State{
    class Error(val message: String): State()
    data object InProgress : State()
    class Result(
        val homeInfo: HomeInfoModel,
        val categories: List<CategoryModel>
    ): State()
}

