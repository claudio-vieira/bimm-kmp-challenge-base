package com.bimm.takehomeassignmnent

import android.app.Application
import com.bimm.takehomeassignmnent.di.databaseModule
import com.bimm.takehomeassignmnent.di.sharedKoinModules
import com.bimm.takehomeassignmnent.di.viewModelsModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class SakeLocationApp: Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    //We could use a databaseModule in the future
    private fun initKoin() {
        val modules = sharedKoinModules + viewModelsModule + databaseModule

        startKoin {
            androidContext(this@SakeLocationApp)
            modules(modules)
        }
    }
}