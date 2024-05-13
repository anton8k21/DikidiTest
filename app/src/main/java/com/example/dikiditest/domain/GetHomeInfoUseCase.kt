package com.example.dikiditest.domain

import com.example.dikiditest.domain.model.HomeInfoModel

class GetHomeInfoUseCase(private val repository: Repository) {

    suspend operator fun invoke(): State{
        return repository.getHomeInfo()
    }
}