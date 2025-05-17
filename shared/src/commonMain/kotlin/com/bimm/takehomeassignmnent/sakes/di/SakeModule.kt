package com.bimm.takehomeassignmnent.sakes.di

import com.bimm.takehomeassignmnent.sakes.data.SakeDataSource
import com.bimm.takehomeassignmnent.sakes.data.SakeRepository
import com.bimm.takehomeassignmnent.presentation.SakeLocationsSharedViewModel
import org.koin.dsl.module

val sakeModule = module {

    single<SakeLocationsSharedViewModel> { SakeLocationsSharedViewModel(get()) }
    single<SakeDataSource> { SakeDataSource() }
    single<SakeRepository> { SakeRepository(get()) }
}