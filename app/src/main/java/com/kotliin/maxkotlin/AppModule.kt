package com.kotliin.maxkotlin

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.kotliin.maxkotlin.app.data.retrofit.AuthenticationInterceptor
import com.kotliin.maxkotlin.app.data.retrofit.UserAgentInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.net.ssl.HostnameVerifier
import javax.net.ssl.SSLSession

/**
 * create by max at 2020/9/30 19:25
 *
 */

internal const val MODULE_NAME = "App"
 
val appModule = Kodein.Module("${MODULE_NAME}Module") {

    bind() from singleton { AuthenticationInterceptor(BuildConfig.apiToken) }

    bind() from singleton { UserAgentInterceptor() }

    bind<HttpLoggingInterceptor>() with singleton {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    bind<Retrofit.Builder>() with singleton { Retrofit.Builder() }

    bind<OkHttpClient.Builder>() with singleton { OkHttpClient.Builder() }

    bind<OkHttpClient>() with singleton {
        instance<OkHttpClient.Builder>()
            .addNetworkInterceptor(StethoInterceptor())
            .addInterceptor(instance<AuthenticationInterceptor>())
            //.addInterceptor(instance<UserAgentInterceptor>())
            .addInterceptor(instance<HttpLoggingInterceptor>())
//            .hostnameVerifier(object : HostnameVerifier {
//                override fun verify(hostname: String?, session: SSLSession?): Boolean {
//                    return true
//                }
//            })
            .build()
    }

    bind<Retrofit>() with singleton {
            instance<Retrofit.Builder>()
                .baseUrl(BuildConfig.apiBaseUrl)
                .addConverterFactory(MoshiConverterFactory.create())
                .client(instance())
                .build()
    }

}