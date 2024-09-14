package com.example.sprint3.data.repository.remote.backend

import com.example.sprint3.data.constants.GeneralConstants.Companion.BASE_URL
import com.example.sprint3.data.constants.GeneralConstants.Companion.RETROFIT_TIMEOUT_IN_SECOND
import com.google.gson.GsonBuilder
import okhttp3.CertificatePinner
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton
import javax.net.ssl.HostnameVerifier

@Singleton
class RetrofitClient @Inject constructor() {

    companion object {
        const val HEADER_KEY_TOKEN = "Authorization"
        const val URL = "rickandmortyapi.com"
        private const val SHA256 = "sha256/dAFbai5eSQy4IsN10BR9A33RoO4e8uxDVnRXw0mA9Dw="
    }

    val retrofit: Retrofit

    init {
        val httpClient: OkHttpClient.Builder = OkHttpClient.Builder()

        val certificatePinner = CertificatePinner.Builder()
            .add(URL, SHA256)
            .build()
        httpClient.certificatePinner(certificatePinner)

        val hostnamesAllow = listOf(
            URL,
        )
        val hostnameVerifier = HostnameVerifier { hostname, _ ->
            hostname in hostnamesAllow
        }
        httpClient.hostnameVerifier(hostnameVerifier)

        httpClient
            .connectTimeout(RETROFIT_TIMEOUT_IN_SECOND, TimeUnit.SECONDS)
            .readTimeout(RETROFIT_TIMEOUT_IN_SECOND, TimeUnit.SECONDS)
            .writeTimeout(RETROFIT_TIMEOUT_IN_SECOND, TimeUnit.SECONDS)

        httpClient.interceptors().clear()
        val logging = HttpLoggingInterceptor()

        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        httpClient
            .addInterceptor(logging)
            .addInterceptor(Interceptor { chain ->
                val originalRequest: Request = chain.request()
                val builder: Request.Builder = originalRequest.newBuilder()
                val newRequest: Request = builder.build()
                chain.proceed(newRequest)
            })

        val gson = GsonBuilder().setLenient().create()

        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(httpClient.build())
            .callbackExecutor(Executors.newSingleThreadExecutor())
            .build()
    }
}