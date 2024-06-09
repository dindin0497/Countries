package com.din.countries.di

import com.apollographql.apollo3.ApolloClient
import com.din.countries.domain.ApolloCountryClient
import com.din.countries.domain.CountryClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)

object AppModule {

    @Provides
    @Singleton
    fun provideApolloClient() : ApolloClient{
        return ApolloClient.Builder()
            .serverUrl("https://countries.trevorblades.com/graphql")
            .build()
    }

    @Provides
    @Singleton
    fun provideCountryClient(apollo : ApolloClient) : CountryClient{
        return ApolloCountryClient(apollo)
    }

}