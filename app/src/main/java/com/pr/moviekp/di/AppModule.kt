package com.pr.moviekp.di

import org.koin.dsl.module

val appModule = module {
    includes(
        viewModelsModule,
        ktorModule,
        serializationModule,
    )
}