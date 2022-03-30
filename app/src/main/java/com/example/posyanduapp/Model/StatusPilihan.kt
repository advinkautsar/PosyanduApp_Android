package com.example.posyanduapp.model

import java.math.BigInteger

class StatusPilihan  (
    val data: ArrayList<Result>?,
    val status: String,
    val sukses :Boolean,
    val message: String,
){

    data class Result(
        val status: String?,

    ) {
        override fun toString(): String {
            return status!!
        }
    }

}