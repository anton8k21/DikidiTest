package com.example.dikiditest.domain

import com.example.dikiditest.domain.model.CategoryModel


interface Repository {
   suspend fun getHomeInfo(): State
}