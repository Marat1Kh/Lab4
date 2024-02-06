package com.example.lab4.api

import retrofit2.http.GET

interface VersionService {
    @GET("/api/versions.json")
    suspend fun getVersions(): List<String>
}