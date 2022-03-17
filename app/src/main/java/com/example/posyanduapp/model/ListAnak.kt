package com.example.posyanduapp.model

class ListAnak (
    val data: ArrayList<Result>?,
    val status: String,
    val message: String,
        ){

    data class Result(
        val nama_anak: String?,
        val createdAt: String?,
        val nik_anak: Int?,
        val updatedAt: String?
    ) {
        override fun toString(): String {
            return nama_anak!!
        }
    }

}