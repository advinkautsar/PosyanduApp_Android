package com.example.posyanduapp.model

import java.math.BigInteger

class Bidan  (
    val data: Result,
    val status: String,
    val sukses :Boolean,
    val message: String,
){

    data class Result(
        val nama_bidan: String?,
        val createdAt: String?,
        val id: Int?,
        val updatedAt: String?
    ) {
        override fun toString(): String {
            return nama_bidan!!
        }
    }

}