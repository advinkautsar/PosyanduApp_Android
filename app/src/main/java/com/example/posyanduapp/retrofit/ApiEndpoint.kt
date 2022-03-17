package com.example.posyanduapp.retrofit

import com.example.posyanduapp.model.ListAnak
import com.example.posyanduapp.model.ResponsePesan
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface ApiEndpoint {

    @GET("list-anak")
    fun getanak(
//        @Header("api-token") api_token: String,
//        @Header("role") role: String
    ): Call<ListAnak>

    @FormUrlEncoded
    @POST("create-imunisasi")
    fun postJadwalImunisasi(

//        @Field("nik_anak") nik_anak: String,
        @Field("jenis_imunisasi") jenis_imunisasi: String,
        @Field("waktu_imunisasi") waktu_imunisasi: String,
    ): Call<ResponsePesan>

}