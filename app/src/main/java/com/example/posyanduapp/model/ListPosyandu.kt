package com.example.posyanduapp.model

class ListPosyandu (
    val data: ArrayList<Result>?,
    val status: String,
    val sukses :Boolean,
    val message: String,
){

    data class Result(
        val nama_posyandu: String?,
        val alamat_posyandu: String?,
        val hari_kegiatan: String?,
        val createdAt: String?,
        val minggu_kegiatan: String?,
        val id :Int?,
        val updatedAt: String?
    ) {
        override fun toString(): String {
            return nama_posyandu!!
        }
    }

}