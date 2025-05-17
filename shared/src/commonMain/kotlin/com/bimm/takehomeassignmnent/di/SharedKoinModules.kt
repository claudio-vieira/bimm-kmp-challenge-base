package com.bimm.takehomeassignmnent.di

import com.bimm.takehomeassignmnent.sakes.di.networkModule
import com.bimm.takehomeassignmnent.sakes.di.sakeModule

//We could use the networkModule in the future to call an API instead of the json
val sharedKoinModules = listOf(
    sakeModule,
    networkModule
)