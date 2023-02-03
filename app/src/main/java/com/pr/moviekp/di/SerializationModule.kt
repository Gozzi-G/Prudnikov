package com.pr.moviekp.di

import kotlinx.serialization.json.Json
import org.koin.dsl.module

val serializationModule = module {
    single {
        Json {
            isLenient = true
            ignoreUnknownKeys = true
        }
    }
}
