package com.example.dikiditest.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptoapp.data.network.ApiFactory
import com.example.dikiditest.data.repository.RepositoryImpl
import com.example.dikiditest.domain.GetHomeInfoUseCase
import com.example.dikiditest.domain.Repository
import com.example.dikiditest.domain.State
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

open class ViewModel: ViewModel() {

    private val _state = MutableStateFlow<State>(State.InProgress)
    val state = _state.asStateFlow()

    private val repository: Repository = RepositoryImpl(ApiFactory.apiService)
    private val getHomeInfoUseCase = GetHomeInfoUseCase(repository)
    private val getCategoriesUseCase= GetHomeInfoUseCase(repository)

    init {
        getHomeInfo()
    }

    private fun getHomeInfo(){
        viewModelScope.launch {
            val state = getHomeInfoUseCase.invoke()
            _state.value = state
        }
    }
}