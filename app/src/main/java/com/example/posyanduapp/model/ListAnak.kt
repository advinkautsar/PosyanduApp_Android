package com.example.posyanduapp.model

import java.math.BigInteger

class ListAnak (
    val data: ArrayList<Result>?,
    val status: String,
    val sukses :Boolean,
    val message: String,
        ){

    data class Result(
        val nama_anak: String?,
        val nama_ibu: String?,
        val nama_posyandu: String?,
        val createdAt: String?,
        val nik_anak: BigInteger,
        val updatedAt: String?
    ) {
        override fun toString(): String {
            return nama_anak!!
        }
    }

}