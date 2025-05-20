package com.gbreagan.challenge.exchange.di

import com.gbreagan.challenge.exchange.BuildConfig
import com.gbreagan.challenge.exchange.data.remote.ExchangeApiService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {

    single(named("logging")) {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    single(named("apikey")) {
        Interceptor { chain ->
            val original = chain.request()
            val originalUrl = original.url

            val url = originalUrl.newBuilder()
                .addQueryParameter("access_key", BuildConfig.API_KEY)
                .build()

            val request = original.newBuilder()
                .url(url)
                .build()
            chain.proceed(request)
        }
    }

    single<OkHttpClient> {
        OkHttpClient.Builder()
//            .hostnameVerifier { _, _ -> true }
            .addInterceptor(interceptor = get<Interceptor>(named("apikey")))
            .addInterceptor(interceptor = get<HttpLoggingInterceptor>(named("logging")))
            .build()
    }

    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(BuildConfig.API_URL)
            .client(get<OkHttpClient>())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single<ExchangeApiService> { get<Retrofit>().create(ExchangeApiService::class.java) }
}