package com.example.posyanduapp.Model

import com.example.posyanduapp.model.ListAnak
import java.math.BigInteger

class ListAnakOrtu(
    val data: ArrayList<Result>?,
    val status: String,
    val sukses :Boolean,
    val message: String,
) {
    data class Result(
        val nik_anak: BigInteger,
        val nama_anak: String?,
        val nama_ibu: String?,
        val nama_posyandu: String?,
        val createdAt: String?,
        val updatedAt: String?
    ) {
        override fun toString(): String {
            return nama_anak!!
        }
    }
}