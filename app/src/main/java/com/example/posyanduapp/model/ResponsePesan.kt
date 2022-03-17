package com.example.posyanduapp.model

class ResponsePesan (val status: Boolean, val pesan: String) {
    override fun toString(): String {
        return "ResponseKirimPesanan(status=$status, pesan='$pesan')"
    }
}