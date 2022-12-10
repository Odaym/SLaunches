package com.saltserv.slaunches.android.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saltserv.SpaceXSDK
import com.saltserv.slaunches.entity.RocketLaunch
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val sdk: SpaceXSDK
) : ViewModel() {

    sealed class MainScreenState {
        object Loading : MainScreenState()
        data class Error(val throwable: Throwable) : MainScreenState()
        data class Success(val rocketLaunches: List<RocketLaunch> = listOf()) : MainScreenState()
    }

    var state by mutableStateOf<MainScreenState>(MainScreenState.Loading)

    init {
        loadItems()
    }

    fun loadItems() {
        if (state != MainScreenState.Loading) {
            state = MainScreenState.Loading
        }

        viewModelScope.launch {
            kotlin.runCatching {
                sdk.getLaunches(true)
            }.onSuccess {
                state = MainScreenState.Success(it)
            }.onFailure {
                state = MainScreenState.Error(it)
            }
        }
    }
}