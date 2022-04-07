package com.example.posyanduapp.retrofit

import com.example.posyanduapp.Model.*
import com.example.posyanduapp.model.*
import retrofit2.Call
import retrofit2.http.*

interface ApiEndpoint {

    //List Data

    @GET("list-status")
    fun getstatus(
    ): Call<StatusPilihan>

    @GET("list-anak")
    fun getanak(
    ): Call<ListAnak>

    @GET("list-imunisasi")
    fun getimunisasi(
    ): Call<ListImunisasi>

    @GET("list-posyandu")
    fun getposyandu(
    ): Call<ListPosyandu>

    @GET("list-puskesmas")
    fun getpuskesmas(
    ): Call<ListPuskesmas>

    @FormUrlEncoded
    @POST("list-anak-cari")
    fun getanakCARI(
        @Field("anak") anak: String,
    ): Call<ListAnak>

    @GET("list-notifikasi/{id}")
    fun getnotif(
        @Path("id") id:Int,
    ): Call<ListNotifikasi>


    //getUser
    @GET("get_user_bidan/{id}")
    fun getbidan(
        @Path("id") id:Int,
        ): Call<Bidan>

    @GET("get_user_kader/{id}")
    fun getkader(
        @Path("id") id:Int,
        ): Call<Kader>

    @GET("get_user_ortu/{id}")
    fun getortu(
        @Path("id") id:Int,
    ): Call<Orangtua>




    //CRUD RUJUKAN

    @DELETE("hapus_datarujukan/{id}")
    fun getdeleterujukan(
        @Path("id") id:Int,
        ): Call<ResponsePesan>

    @GET("ambil_datarujukan")
    fun getRujukan(
    ): Call<ListRujukan>

    @GET("ambil_datarujukan/{id}")
    fun getallRujukanID(
        @Path("id") id:String,
    ): Call<ListRujukanAnak>

    @GET("show_datarujukan/{id}")
    fun getRujukanID(
        @Path("id") id:Int,
    ): Call<GetRujukan>

    @FormUrlEncoded
    @POST("tambah_datarujukan")
    fun postRujukan(
        @Field("tanggal_rujukan") tanggal_rujukan: String,
        @Field("keluhan_anak") keluhan_anak: String,
        @Field("tempat_pelayanan") tempat_pelayanan : String,
        @Field("puskesmas_id")puskesmas_id:  String,
        @Field("bidan_id") bidan_id: String,
        @Field("nik_anak") nik_anak: String,
        ): Call<ResponsePesan>

    @FormUrlEncoded
    @PUT("ubah_datarujukan/{id}")
    fun updateRujukan(
        @Path("id") id: String,
        @Field("tanggal_rujukan") tanggal_rujukan: String,
        @Field("keluhan_anak") keluhan_anak: String,
        @Field("tempat_pelayanan") tempat_pelayanan: String,
        @Field("puskesmas_id") puskesmas_id:  String,
        @Field("bidan_id") bidan_id: String,
        @Field("nik_anak") nik_anak: String,
        ): Call<ResponsePesan>




    //CRUD PEMERIKSAAN

    @FormUrlEncoded
    @POST("tambah_dataPemeriksaan")
    fun postpemeriksaankesehatan(
        @Field("bidan_id") bidan_id :String,
        @Field("nik_anak")nik_anak : String,
        @Field("imunisasi_id_1")imunisasi_id_1 : String,
        @Field("imunisasi_id_2") imunisasi_id_2: String,
        @Field("imunisasi_id_3") imunisasi_id_3: String,
        @Field("vitA_merah")vitA_merah : String,
        @Field("vitA_biru")vitA_biru : String,
        @Field("Fe_1")Fe_1 : String,
        @Field("Fe_2") Fe_2: String,
        @Field("PMT") PMT: String,
        @Field("asi_ekslusif") asi_ekslusif: String,
        @Field("oralit")oralit : String,
        @Field("obat_cacing")obat_cacing : String,
        @Field("tanggal_pemeriksaan")tanggal_pemeriksaan : String,

        ): Call<ResponsePesan>

    @FormUrlEncoded
    @PUT("ubah_dataPemeriksaan/{id}")
    fun updatepemeriksaankesehatan(
        @Path("id") id:String,
        @Field("bidan_id") bidan_id :String,
        @Field("nik_anak")nik_anak : String,
        @Field("imunisasi_id_1")imunisasi_id_1 : String,
        @Field("imunisasi_id_2") imunisasi_id_2: String,
        @Field("imunisasi_id_3") imunisasi_id_3: String,
        @Field("vitA_merah")vitA_merah : String,
        @Field("vitA_biru")vitA_biru : String,
        @Field("Fe_1")Fe_1 : String,
        @Field("Fe_2") Fe_2: String,
        @Field("PMT") PMT: String,
        @Field("asi_ekslusif") asi_ekslusif: String,
        @Field("oralit")oralit : String,
        @Field("obat_cacing")obat_cacing : String,
        @Field("tanggal_pemeriksaan")tanggal_pemeriksaan : String,

        ): Call<ResponsePesan>



    @GET("ambil_dataPemeriksaan/{id}")
    fun getallPemeriksaanID(
        @Path("id") id:String,
    ): Call<ListPemeriksaan>

    @GET("show_dataPemeriksaan/{id}")
    fun getPemeriksaanID(
        @Path("id") id:String,
    ): Call<GetPemeriksaan>

    @DELETE("hapus_dataPemeriksaan/{id}")
    fun getdeletepemkes(
        @Path("id") id:Int,
    ): Call<ResponsePesan>




    //Jadwal Notif

    @FormUrlEncoded
    @POST("create-jadwal-imunisasi")
    fun postJadwalImunisasi(
        @Field("tanggal_imunisasi") jenis_imunisasi: String,
        @Field("bidan_id") bidan_id: String,
        @Field("nik_anak") nik_anak: String,
        @Field("imunisasi_id")imunisasi_id: String,
        ): Call<ResponsePesan>

    @FormUrlEncoded
    @POST("create-jadwal-posyandu")
    fun postJadwalPosyandu(
        @Field("tanggal_kegiatan") tanggal_kegiatan: String,
        @Field("waktu_kegiatan") waktu_kegiatan: String,
        @Field("kader_id") kader_id: String,
        @Field("posyandu_id")posyandu_id : String,
        @Field("keterangan_kegiatan")keterangan_kegiatan : String,

    ): Call<ResponsePesan>




    //cek dan update akun
    @FormUrlEncoded
    @POST("update-akun-ortu")
    fun updateUserOrtu(
        @Field("nama_pengguna") nama_pengguna: String,
        @Field("kata_sandi") kata_sandi: String,
        @Field("no_hp") no_hp: String,
        @Field("nik") nik: String,
        @Field("token") token: String,
    ): Call<ResponRegister>

    @FormUrlEncoded
    @POST("cek-nik-ortu")
    fun ceknikortu(
        @Field("nik") nik: String,
        ): Call<ResponsePesan>

    @GET("logout/{id}")
    fun LOGOUT(
        @Path("id") id:Int,
    ): Call<ResponsePesan>





    @FormUrlEncoded
    @POST("update-kartu-anak")
    fun postnoKartuanak(
        @Field("nik_anak")nik_anak: String,
        @Field("no_kartu") no_kartu: String,
    ): Call<ResponsePesan>


    // Get Detail Anak
    @GET("ambil_data_anak/{id}")
    fun getDetailAnakID(
        @Path("id") id:String,
    ): Call<GetDetailAnak>

    // Get Riwayat Status Gizi Anak
    @GET("ambil_datastatusgizi_anak/{id}")
    fun getDetailStatGizAnakID(
        @Path("id") id:String,
    ): Call<GetRiwayatStatusGiziAnak>


    //Get List Profil Ortu
    @FormUrlEncoded
    @POST("list-ortu-cari")
    fun getortuCARI(
        @Field("orangtua") orangtua: String,
    ): Call<ListProfilOrtu>

    // Get Profil Ortu
    @GET("show_dataOrtu/{id}")
    fun getProfilOrtu(
        @Path("id") id:String,
    ): Call<GetDetailOrtu>

    //Get Anak Dari Ortu
    @GET("ambil_data_anakortu/{id}")
    fun getdataAnakOrtu(
        @Path("id") id: Int,
    ): Call<ListAnakOrtu>

    //Get Profil Ortu ( Orangtua )
    @GET("get_profil_ortu/{id}")
    fun getProfilOrtu(
        @Path("id") id: Int,
    ): Call<GetProfilOrtu>

    //Get Kecamatan
      @GET("list-kecamatan")
    fun getKecamatan(
    ): Call<ListKecamatan>

    //Get Kelurahan/Desa
    @GET("list-desa")
    fun getKelurahan_Desa(
    ): Call<ListKelurahanDesa>

    //Update Profil Orangtua
    @FormUrlEncoded
    @POST("updateProfilOrtu/{id}")
    fun updateProfilOrtu(
        @Path("id") id: Int,
        //update User
        @Field("nama_pengguna") nama_pengguna: String,
        @Field("kata_sandi") kata_sandi: String,
        @Field("no_hp") no_hp: String,
        //update orangtua
        @Field("nik_ayah") nik_ayah: String,
        @Field("nama_ayah") nama_ayah: String,
        @Field("nik_ibu") nik_ibu: String,
        @Field("nama_ibu") nama_ibu: String,
        @Field("alamat") alamat: String,
        @Field("rt") rt: String,
        @Field("rw") rw: String,
        @Field("kecamatan_id") kecamatan_id: String,
        @Field("desa_kelurahan_id") desa_kelurahan_id: String,
    ): Call<ResponsePesan>
}