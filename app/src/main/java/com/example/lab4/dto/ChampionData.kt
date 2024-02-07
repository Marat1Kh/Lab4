package com.example.lab4.dto

data class ChampionData(
    val type: String,
    val format: String,
    val version: String,
    val data: Map<String, Champion>,
    val info: Map<Int, Champion>,
    val stats: Map<Int, Champion>
)