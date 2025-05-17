package com.bimm.takehomeassignmnent.presentation

import com.bimm.takehomeassignmnent.BaseViewModel
import com.bimm.takehomeassignmnent.sakes.data.SakeLocation
import com.bimm.takehomeassignmnent.sakes.data.SakeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class SakeLocationsSharedViewModel(
    private val sakeRepository: SakeRepository
): BaseViewModel() {
    private val _sakeLocationState = MutableStateFlow<List<SakeLocation>>(emptyList())
    val sakeLocationState = _sakeLocationState

    private var _sakeSelected: SakeLocation? = null
    val sakeSelected: SakeLocation get() = _sakeSelected ?: throw IllegalStateException("No sake selected")

    init {
        scope.launch {
            _sakeLocationState.value = sakeRepository.getAllSakesJSON()
        }
    }

    fun setSakeSelected(sakeLocation: SakeLocation) {
        _sakeSelected = sakeLocation
    }
}