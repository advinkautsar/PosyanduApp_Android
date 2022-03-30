package com.example.posyanduapp.model

import java.math.BigInteger

class ListPuskesmas  (
    val data: ArrayList<Result>?,
    val status: String,
    val sukses :Boolean,
    val message: String,
){

    data class Result(
        val alamat_puskesmas: String?,
        val nama_puskesmas: String?,
        val createdAt: String?,
        val id: Int?,
        val updatedAt: String?
    ) {
        override fun toString(): String {
            return nama_puskesmas!!
        }
    }

}