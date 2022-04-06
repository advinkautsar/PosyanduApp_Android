package com.example.posyanduapp.Model

import com.example.posyanduapp.model.Kader
import java.math.BigInteger

class Orangtua(
    val data: ArrayList<Result>,
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
    )
}
