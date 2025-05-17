package com.bimm.takehomeassignmnent.di

import org.koin.dsl.module

//Database module in case to use the database in the future
val databaseModule = module {

    //single<SqlDriver> { DatabaseDriverFactory(androidContext()).createDriver() }

    //single<SakeLocationDatabase> { SakeLocationDatabase(get()) }
}