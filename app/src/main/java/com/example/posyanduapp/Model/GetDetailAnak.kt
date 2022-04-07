package com.example.posyanduapp.Model

import com.example.posyanduapp.model.GetPemeriksaan

class GetDetailAnak(
    val data: Result?,
    val status: String,
    val sukses :Boolean,
    val message: String,
) {
    data class Result(
        val id: String?,
        val nik_anak: String?,
        val orangtua_id: String?,
        val no_kartu: String?,
        val nama_anak: String?,
        val jenis_kelamin: String?,
        val tanggal_lahir: String?,
        val berat_lahir: String?,
        val panjang_lahir: String?,
        val nama_ibu: String?,
        )


}