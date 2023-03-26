package com.nwt.amphibians.data

import com.nwt.amphibians.network.AmphibiansApiService

interface AmphibiansRepository {
    //
    suspend fun getAmphibiansInfo(): List<Amphibian>
}

class ConcreteAmphibiansRepository(private val apiService: AmphibiansApiService) : AmphibiansRepository {
    override suspend fun getAmphibiansInfo(): List<Amphibian> {
        return apiService.getInfo()
    }
}