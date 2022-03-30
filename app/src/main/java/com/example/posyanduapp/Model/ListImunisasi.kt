package com.example.posyanduapp.model

import java.math.BigInteger

class ListImunisasi (
    val data: ArrayList<Result>?,
    val status: String,
    val sukses :Boolean,
    val message: String,
){

    data class Result(
        val jenis_imunisasi: String?,
        val waktu_imunisasi: String?,
        val createdAt: String?,
        val id: Int?,
        val updatedAt: String?
    ) {
        override fun toString(): String {
            return jenis_imunisasi!!
        }
    }

}