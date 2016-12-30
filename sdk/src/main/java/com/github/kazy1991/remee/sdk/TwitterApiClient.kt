package com.github.kazy1991.remee.sdk

import okhttp3.OkHttpClient
import retrofit2.Retrofit

class TwitterApiClient {

    val retrofit by lazy {
        Retrofit.Builder()
                .client(okHttpClient)
                .build()
    }

    val okHttpClient by lazy {
        OkHttpClient.Builder()
                .build()
    }

}