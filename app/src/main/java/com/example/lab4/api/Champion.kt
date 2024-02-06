package com.example.lab4.api

data class Champion(
    val id: String,
    val key: String,
    val name: String,
    val title: String,
    val blurb: String,
    val info: Info,
    val stats: Stats,
    val image: Image
)
data class Info(
    val magic: Int,
    val attack: Int,
    val defense: Int,
    val difficulty: Int,
)
data class Stats(
    val hp: Int,
    val movespeed: Int,
    val armor: Int,
    val attackrange: Int,
    val hpregen: Float,
)
data class Image(
    val full: String
)