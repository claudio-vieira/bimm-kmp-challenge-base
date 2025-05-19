package com.bimm.takehomeassignmnent.presentation

import com.bimm.takehomeassignmnent.BaseViewModel
import com.bimm.takehomeassignmnent.ResponseState
import com.bimm.takehomeassignmnent.sakes.data.ISakeRepository
import com.bimm.takehomeassignmnent.sakes.data.SakeLocation
import com.bimm.takehomeassignmnent.sakes.data.SakeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class SakeLocationsSharedViewModel(
    private val sakeRepository: ISakeRepository
): BaseViewModel() {

    private val _sakeLocationState = MutableStateFlow(SakeLocationState(loading = true))
    val sakeLocationState = _sakeLocationState

    private var _sakeSelected: SakeLocation? = null
    val sakeSelected get() = _sakeSelected

    init {
        getArticles()
    }

    private fun getArticles(endRefresh: () -> Unit = {}) {
        scope.launch {
            _sakeLocationState.emit(
                SakeLocationState(
                    loading = true,
                    sakeLocations = _sakeLocationState.value.sakeLocations
                )
            )
            when(val result = sakeRepository.fetchSakeLocations()) {
                is ResponseState.Success -> {
                    _sakeLocationState.emit(SakeLocationState(sakeLocations = result.item))
                    endRefresh.invoke()
                }
                is ResponseState.Error -> TODO()
                ResponseState.Loading -> TODO()
            }
        }
    }

    fun setSakeSelected(sakeLocation: SakeLocation) {
        _sakeSelected = sakeLocation
    }
}