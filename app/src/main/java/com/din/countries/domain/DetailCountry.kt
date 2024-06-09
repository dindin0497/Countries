package com.din.countries.domain

data class DetailCountry(val code: String, val name: String,
    val continent: String, val emoji: String,
    val language: List<String>, val currency: String
)
