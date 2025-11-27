package com.example.pokedexapp.di

import com.example.pokedexapp.data.remote.api.SudokuApi
import com.example.pokedexapp.data.repository.SudokuRepositoryImpl
import com.example.pokedexapp.domain.repository.SudokuRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val apiKeyInterceptor =
            Interceptor { chain ->
                val originalRequest = chain.request()
                val newRequest =
                    originalRequest
                        .newBuilder()
                        .header("x-api-key", "wLVPN1zV08lJYF7uXqgyPw==zVwp6TlVcAO1NLUf")
                        .build()
                chain.proceed(newRequest)
            }

        return OkHttpClient
            .Builder()
            .addInterceptor(apiKeyInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit
            .Builder()
            .client(okHttpClient)
            .baseUrl("https://api.api-ninjas.com/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    // Aquí va la Api de Pokemon
    @Provides
    @Singleton
    fun provideSudokuApi(retrofit: Retrofit): SudokuApi = retrofit.create(SudokuApi::class.java)

    // Acá se instancia el repositorio
    @Provides
    @Singleton
    fun provideSudokuRepository(api: SudokuApi): SudokuRepository = SudokuRepositoryImpl(api)
}
