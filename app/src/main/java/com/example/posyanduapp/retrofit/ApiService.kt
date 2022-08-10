package com.example.posyanduapp.retrofit

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiService {

//    val BASE_URL: String = "http://192.168.100.9/TA_Posyandu2021/api/"
    val BASE_URL: String = "https://ta.poliwangi.ac.id/~ti18069/api/"
    val BASE_URL_WEB: String = "https://ta.poliwangi.ac.id/~ti18069/"

    val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    val client: OkHttpClient = OkHttpClient.Builder().apply {
        addInterceptor(interceptor)
    }.build()
    val endpoint: ApiEndpoint
        get() {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
            return retrofit.create(ApiEndpoint::class.java)
        }
}