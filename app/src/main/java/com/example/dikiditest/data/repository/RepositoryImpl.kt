package com.example.dikiditest.data.repository

import ApiService
import com.example.dikiditest.R
import com.example.dikiditest.data.modelDto.HomeInfoModelDto
import com.example.dikiditest.domain.Repository
import com.example.dikiditest.domain.State
import com.example.dikiditest.domain.model.CategoryModel


class RepositoryImpl(
    private val api: ApiService
): Repository {

    override suspend fun getHomeInfo(): State {
        val response = api.getHomeInfo("maJ9RyT4TJLt7bmvYXU7M3h4F797fUKofUf3373foN94q4peAM")

        return if (response.isSuccessful){
            val homeInfoModel = response.body()?.let { HomeInfoModelDto.homeInfoDtoToHomeInfo(it) }
            State.Result(homeInfoModel!!, getCategories())
        }else{
            State.Error("code:" + response.code() + response.message())
        }

    }

    private fun getCategories(): List<CategoryModel> {
        return listOf(
            CategoryModel(image = R.drawable.fitness, name = "Фитнес"),
            CategoryModel(image = R.drawable.nails, name = "Ногтевой сервис"),
            CategoryModel(image = R.drawable.barber,name = "Барбершоп"),
            CategoryModel(image = R.drawable.eyelashes,name = "Ресницы"),
            CategoryModel(image = R.drawable.barbershop,name = "Парикмахерские услуги"),
            CategoryModel(image = R.drawable.shugaring,name = "Депиляция, \n эпиляция")
        )
    }
}