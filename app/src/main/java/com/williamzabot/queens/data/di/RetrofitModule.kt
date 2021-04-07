package com.williamzabot.queens.data.di

import com.williamzabot.queens.data.api.QueenApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Singleton
    @Provides
    fun provideQueenApi() = provideWebService<QueenApi>()

    private inline fun <reified T> provideWebService(): T {
        return Retrofit.Builder()
            .baseUrl("http://www.nokeynoshade.party/api/")
            .client(provideOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(T::class.java)
    }

    private fun provideOkHttpClient() = OkHttpClient.Builder().apply {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        addInterceptor(logging)
    }.build()
}