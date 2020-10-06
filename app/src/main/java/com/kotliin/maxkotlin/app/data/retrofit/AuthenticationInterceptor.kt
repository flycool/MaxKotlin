package com.kotliin.maxkotlin.app.data.retrofit

import okhttp3.Interceptor
import okhttp3.Response

/**
 * create by max at 2020/10/1 15:53
 *
 */

class AuthenticationInterceptor(private val apiKey: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response = chain.request().let {
        val url = it.url.newBuilder()
            .addQueryParameter("api_key", apiKey)
            .addQueryParameter("format", "json")
            .build()

        val newRequest = it.newBuilder()
            .url(url)
            .build()

        chain.proceed(newRequest)
    }
}