package com.din.countries.domain

import com.apollographql.apollo3.ApolloClient
import com.din.CountriesQuery
import com.din.CountryQuery
import com.din.countries.data.toDetail
import com.din.countries.data.toSimple

class ApolloCountryClient(private val apollo: ApolloClient) : CountryClient{
    override suspend fun getCountries(): List<SimpleCountry> {
        return apollo
            .query(CountriesQuery())
            .execute()
            .data?.countries?.map {
                it.toSimple()
            }
            ?: emptyList()

    }

    override suspend fun getCountry(code: String): DetailCountry? {
        return apollo
            .query(CountryQuery(code))
            .execute()
            .data?.country?.toDetail()
    }
}