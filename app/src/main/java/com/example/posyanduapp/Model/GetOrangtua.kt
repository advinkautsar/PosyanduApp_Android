package com.example.posyanduapp.Model

class GetOrangtua(
    val data: Result,
    val status: String,
    val sukses :Boolean,
    val message: String,
) {
    data class Result(
        val nama_ibu: String?,
        val createdAt: String?,
        val id: Int?,
        val updatedAt: String?
    )
}