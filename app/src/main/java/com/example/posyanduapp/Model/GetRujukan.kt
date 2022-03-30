package com.example.posyanduapp.model


class GetRujukan  (
    val data: Result?,
    val status: String,
    val sukses :Boolean,
    val message: String,
){

    data class Result(
        val id: String?,
        val nik_anak:String?,
        val nama_bidan: String?,
        val keluhan_anak: String?,
        val nama_puskesmas: String?,
        val puskesmas_id: Int?,
        val posyandu :String?,
        val id_posyandu :Int?,
        val nama_posyandu :String?,
        val nama_anak :String?,
        val tanggal_rujukan: String?,
        val updatedAt: String?
    ) {
//        override fun toString(): String {
//            return nik_anak!!
//        }
    }


}