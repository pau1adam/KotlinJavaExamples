package io.github.pau1adam.kotlinjavasamples.kotlin

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.experimental.CoroutineCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val API_KEY = "add api key"

val BASE_URL = "http://food2fork.com/api/"

val retrofit by lazy {
    Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
}

val api by lazy { retrofit.create(Food2ForkApi::class.java) }