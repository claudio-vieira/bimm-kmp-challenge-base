package com.bimm.takehomeassignmnent.di

import com.bimm.takehomeassignmnent.presentation.SakeLocationsSharedViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin

fun initKoin() {

    val modules = sharedKoinModules + databaseModule

    startKoin {
        modules(modules)
    }
}

class SakeLocationInjector : KoinComponent {
    val sakeLocationsSharedViewModel: SakeLocationsSharedViewModel by inject()
}