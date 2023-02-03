package com.pr.moviekp.di

import com.pr.moviekp.BuildConfig
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import org.koin.dsl.module
import timber.log.Timber

val ktorModule = module {
    single { createHttpClient(get()) }
}


private fun createHttpClient(
    json: Json,
): HttpClient {

    val client = HttpClient(Android) {
        expectSuccess = true
        install(ContentNegotiation) { json(json) }
        defaultRequest { url(BuildConfig.BASE_URL) }


        if (BuildConfig.DEBUG) {
            install(Logging) {
                logger = object : Logger {
                    override fun log(message: String) {
                        Timber.tag("Timber").d("========KTOR-DEFAULT-HttpClient========\n$message")
                    }
                }
                level = LogLevel.ALL
            }
        }

    }
    return client
}