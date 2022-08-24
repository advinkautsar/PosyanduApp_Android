package com.example.posyanduapp.Model

class ListPersetujuan(
    val data: ArrayList<Result>?,
    val status: String,
    val sukses :Boolean,
    val message: String,
) {
    data class Result(
        val status_persetujuan: String?,

        ) {
        override fun toString(): String {
            return status_persetujuan!!
        }
    }
}