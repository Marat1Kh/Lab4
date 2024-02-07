package com.example.lab4.api

import com.example.lab4.dto.ChampionData
import retrofit2.http.GET
import retrofit2.http.Path

interface ChampionService {
    @GET("/cdn/{version}/data/en_US/champion.json")
    suspend fun getChampions(@Path("version") version: String): ChampionData
}
