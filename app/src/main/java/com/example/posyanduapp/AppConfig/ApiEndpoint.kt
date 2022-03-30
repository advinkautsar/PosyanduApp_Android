package com.example.posyanduapp.AppConfig

import com.example.posyanduapp.Model.ResponRegister
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiEndpoint {
    @FormUrlEncoded
    @POST("register")
    fun register(
        @Field("nama_pengguna") nama_pengguna:String,
        @Field("kata_sandi") kata_sandi:String,
        @Field("no_hp") no_hp:String,
        ):Call<ResponRegister>

    @FormUrlEncoded
    @POST("login")
    fun login(
        @Field("nama_pengguna") nama_pengguna:String,
        @Field("kata_sandi") kata_sandi:String,
        @Field("token") token:String,
    ):Call<ResponRegister>
}