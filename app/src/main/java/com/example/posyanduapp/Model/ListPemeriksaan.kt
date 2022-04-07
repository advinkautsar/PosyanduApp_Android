package com.example.posyanduapp.model

class ListPemeriksaan (
    val data: ArrayList<Result>?,
    val status: String,
    val sukses :Boolean,
    val message: String,
){

    data class Result(
        val id: String?,
        val nik_anak: String?,
        val imun1: String?,
        val imun2: String?,
        val imun3: String?,
        val imunisasi_id_1: String?,
        val imunisasi_id_2: String?,
        val imunisasi_id_3: String?,
        val vitA_merah: String?,
        val vitA_biru: String?,
        val Fe_1: String?,
        val Fe_2: String?,
        val PMT: String?,
        val asi_ekslusif: String?,
        val oralit: String?,
        val obat_cacing :String?,
        val nama_bidan :String?,
        val id_bidan: String?,
        val puskesmas_id: Int?,
        val posyandu: String?,
        val id_posyandu: Int?,
        val nama: String?,
        val nama_anak: String?,
        val tanggal_pemeriksaan: String?,
        val updatedAt: String?,
    )
    {
//        override fun toString(): String {
//            return key!!
//        }
    }

}