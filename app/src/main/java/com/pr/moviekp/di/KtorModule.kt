package com.pr.moviekp.di

import com.pr.moviekp.BuildConfig
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.http.*
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


    val personalApi = "a4012320-829c-4da7-8c88-03305e072de5"

    val client = HttpClient(Android) {
        expectSuccess = true
        install(ContentNegotiation) { json(json) }
        defaultRequest {
            url(BuildConfig.BASE_URL)
            header("X-API-KEY", BuildConfig.API_KEY)
            contentType(ContentType.Application.Json)
        }


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