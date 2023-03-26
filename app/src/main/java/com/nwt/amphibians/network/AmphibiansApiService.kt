package com.nwt.amphibians.network

import com.nwt.amphibians.data.Amphibian
import retrofit2.http.GET

interface AmphibiansApiService {
    @GET("amphibians")
    suspend fun getInfo(): List<Amphibian>
}
