package com.bme.projlab.data.network

import com.bme.projlab.data.datasource.AirportDataSource
import com.bme.projlab.data.datasource.FlightDataSource
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Protocol
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object ApiService {

    @Provides
    @Singleton
    fun getGson(): Gson {
        return GsonBuilder().serializeNulls().setLenient().create()
    }

    @Provides
    fun getInterceptor(): Interceptor {
        return Interceptor {
            val request = it.request().newBuilder()
                .addHeader("content-type", "application/octet-stream")
                .addHeader("X-RapidAPI-Key", "a46066c606msh280175865ae4b13p118c1fjsn13be809a360c")
                .addHeader("X-RapidAPI-Host", "skyscanner50.p.rapidapi.com")
            val actualRequest = request.build()
            it.proceed(actualRequest)
        }
    }

    @Provides
    fun getOkHttpClient(
        interceptor: Interceptor
    ): OkHttpClient {
        val httpBuilder = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(50, TimeUnit.SECONDS)

        return httpBuilder
            .protocols(mutableListOf(Protocol.HTTP_1_1))
            .build()
    }


    @Provides
    @Singleton
    fun getRetrofit(
        client: OkHttpClient,
        gson: Gson
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://skyscanner50.p.rapidapi.com/api/v1/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build()
    }

    @Singleton
    @Provides
    fun provideApiInterface(retrofit: Retrofit): ApiInterface = retrofit.create(ApiInterface::class.java)

    @Singleton
    @Provides
    fun providesFlightDataSource(apiInterface: ApiInterface) = FlightDataSource(apiInterface)

    @Singleton
    @Provides
    fun providesAirportDataSource(apiInterface: ApiInterface) = AirportDataSource(apiInterface)

}