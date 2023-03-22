package com.example.amphibians.data

import com.example.amphibians.network.AmphibiansApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AmphibiansContainer{
    val repository: AmphibiansRepository
}

class DefaultAmphibiansContainer: AmphibiansContainer{

    private val baseUrl =
        "https://android-kotlin-fun-mars-server.appspot.com/"

    @OptIn(ExperimentalSerializationApi::class)
    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(
            Json.asConverterFactory(contentType = "application/json".toMediaType())
        )
        .baseUrl(baseUrl)
        .build()

    private val retrofitService: AmphibiansApiService by lazy {
        retrofit.create(AmphibiansApiService::class.java)
    }

    override val repository: AmphibiansRepository by lazy {
        NetworkAmphibiansRepository(retrofitService)
    }
}