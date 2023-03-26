package com.nwt.amphibians.data

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.nwt.amphibians.network.AmphibiansApiService
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory

interface AppContainer {
    val amphibiansRepository: AmphibiansRepository
}

class ConcreteAppContainer() : AppContainer {

    private val BASE_URL = "https://android-kotlin-fun-mars-server.appspot.com"

    private val retrofit = Retrofit.Builder()
        //.addConverterFactory(ScalarsConverterFactory.create()) //--Scalar Converter for String and primitive types
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(BASE_URL)
        .build()

    private val retrofitService: AmphibiansApiService by lazy {
        retrofit.create(AmphibiansApiService::class.java)
    }

    override val amphibiansRepository: AmphibiansRepository by lazy {
        ConcreteAmphibiansRepository(retrofitService)
    }
}
