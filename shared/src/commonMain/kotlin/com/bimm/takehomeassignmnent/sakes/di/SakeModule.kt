package com.bimm.takehomeassignmnent.sakes.di

import com.bimm.takehomeassignmnent.presentation.SakeLocationsSharedViewModel
import com.bimm.takehomeassignmnent.sakes.data.ISakeRepository
import com.bimm.takehomeassignmnent.sakes.data.SakeDataSource
import com.bimm.takehomeassignmnent.sakes.data.SakeRepository
import com.bimm.takehomeassignmnent.sakes.services.ISakeLocationService
import com.bimm.takehomeassignmnent.sakes.services.SakeLocationService
import io.ktor.client.HttpClient
import org.koin.dsl.module

val sakeModule = module {

    single { HttpClient() }
    single<SakeLocationsSharedViewModel> { SakeLocationsSharedViewModel(get()) }
    single<SakeDataSource> { SakeDataSource() }
    single<ISakeLocationService> { SakeLocationService(get()) }
    single<ISakeRepository> { SakeRepository(get()) }
}