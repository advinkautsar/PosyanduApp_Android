package com.example.posyanduapp.Model

import java.math.BigInteger

class ListTipsKes(
    val data: ArrayList<Result>,
    val status: String,
    val sukses :Boolean,
    val message: String,
) {
    data class Result(
        val id: Int?,
        val judul_tips: String?,
        val createdAt: String?,
        val updatedAt: String?
    )
}