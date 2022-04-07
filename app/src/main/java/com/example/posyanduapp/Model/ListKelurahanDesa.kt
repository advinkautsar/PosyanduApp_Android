package com.example.posyanduapp.Model

class ListKelurahanDesa(
    val data: ArrayList<Result>?,
    val status: String,
    val sukses :Boolean,
    val message: String,
) {
    data class Result(
        val id :Int?,
        val nama :String?,
    ){
        override fun toString(): String {
            return nama!!
        }
    }
}