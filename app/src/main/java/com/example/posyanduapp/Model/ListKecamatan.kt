package com.example.posyanduapp.Model

import com.example.posyanduapp.model.ListPosyandu

class ListKecamatan(
    val data: ArrayList<Result>?,
    val status: String,
    val sukses :Boolean,
    val message: String,
) {

    data class Result(
        val nama_kecamatan: String?,
        val kabupaten: String?,
        val id :Int?,
    )
}