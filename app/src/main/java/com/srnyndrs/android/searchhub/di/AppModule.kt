package com.srnyndrs.android.searchhub.di

import com.srnyndrs.android.searchhub.data.repository.SearchRepository
import com.srnyndrs.android.searchhub.data.api.ApiService
import com.srnyndrs.android.searchhub.data.shared.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    // Provides a singleton instance of OkHttpClient
    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().build()
    }

    // Provides a singleton instance of Retrofit, configured with OkHttpClient and GsonConverterFactory
    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // Provides a singleton instance of ApiService using Retrofit
    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    // Provides an instance of SearchRepository with ApiService dependency
    @Provides
    fun provideRepository(apiService: ApiService): SearchRepository {
        return SearchRepository(apiService = apiService)
    }
}