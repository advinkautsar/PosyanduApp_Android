package com.example.posyanduapp.model

class GetPemeriksaan  (
    val data: Result?,
    val status: String,
    val sukses :Boolean,
    val message: String,
) {

    data class Result(
        val id: String?,
        val nik_anak: String?,
        val imun1:String?,
        val imun2: String?,
        val imun3: String?,
        val imunisasi_id_1:Int?,
        val imunisasi_id_2:Int?,
        val imunisasi_id_3: Int?,
        val vitA_merah: String?,
        val vitA_biru: String?,
        val Fe_1: String?,
        val Fe_2: String?,
        val PMT: String?,
        val asi_ekslusif: String?,
        val oralit: String?,
        val obat_cacing :String?,
        val id_bidan: Int?,
        val puskesmas_id: Int?,
        val posyandu: String?,
        val id_posyandu: Int?,
        val nama: String?,
        val nama_anak: String?,
        val tanggal_rujukan: String?,
        val updatedAt: String?,
    )

{
//        override fun toString(): String {
//            return nik_anak!!
//        }
    }
}