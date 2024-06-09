package com.din.countries.data

import com.din.CountriesQuery
import com.din.CountryQuery
import com.din.countries.domain.DetailCountry
import com.din.countries.domain.SimpleCountry

fun CountriesQuery.Country.toSimple() : SimpleCountry{
    return SimpleCountry(code, name, continent.name, emoji)
}

fun CountryQuery.Country.toDetail() : DetailCountry{
    return DetailCountry(code, name, continent.name, emoji,
        languages.mapNotNull { it.name }, currency ?: "No currency"
        )
}