package com.example.posyanduapp.model

class ListNotifikasi(
    val data: ArrayList<Result>?,
    val status: String,
    val sukses :Boolean,
    val message: String,
){

    data class Result(
        val key1: String?,
        val key2: String?,
        val key3: String?,
    ) {
//        override fun toString(): String {
//            return key!!
//        }
    }

}