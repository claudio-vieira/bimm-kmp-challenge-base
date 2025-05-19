package com.bimm.takehomeassignmnent.di

import com.bimm.takehomeassignmnent.presentation.SakeLocationsSharedViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelsModule = module {
    viewModel { SakeLocationsSharedViewModel(get()) }
}