package com.example.posyanduapp.ui.bidan

import android.app.PendingIntent.getActivity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.posyanduapp.adapter.AdapterAnak
import com.example.posyanduapp.databinding.ActivityAnakBinding
import com.example.posyanduapp.model.ListAnak
import com.example.posyanduapp.retrofit.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AnakActivity : AppCompatActivity() {

    private var anaklist = mutableListOf<String>()
    private var ibulist = mutableListOf<String>()
    private var poslist = mutableListOf<String>()

    lateinit var rvListAnak: RecyclerView
    lateinit var adapter: AdapterAnak
    var anak: String = ""


    private lateinit var binding: ActivityAnakBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnakBinding.inflate(layoutInflater)
        setContentView(binding.root)
        rvListAnak = binding.rvAnak
        getlistanakcari()

//        postTolist()


        binding.search.setOnEditorActionListener { v, actionId, event ->
            if (actionId === EditorInfo.IME_ACTION_SEARCH) {
//
//                rvListAnak.adapter.clear()
                anak = binding.search.text.toString()
                getlistanakcari()
                Log.d("nilaie", anak)
                Toast.makeText(
                    this@AnakActivity, anak,
                    Toast.LENGTH_SHORT
                ).show()
                val inputManager = this
                    .getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager

                // check if no view has focus:
                val currentFocusedView: View = this.getCurrentFocus()!!
                if (currentFocusedView != null) {
                    inputManager.hideSoftInputFromWindow(
                        currentFocusedView.windowToken,
                        InputMethodManager.HIDE_NOT_ALWAYS
                    )
                }
                return@setOnEditorActionListener true
            }
            false
        }

        binding.btnKembalianak.setOnClickListener {
            startActivity(Intent(this, BerandabidanActivity::class.java))
        }
//
//        binding.rvAnak.layoutManager = LinearLayoutManager(this)
//        binding.rvAnak.adapter = RecyclerAdapterAnak(this,anaklist, ibulist, poslist)
    }

//    private fun addTolist(anak: String, ibu: String, pos: String){
//        anaklist.add(anak)
//        ibulist.add(ibu)
//        poslist.add(pos)
//
//
//    }
//    private fun postTolist(){
//        for ( i in 1..20){
//            addTolist("Abrar Ibrahim","Ibu Leni ikawangi", "Posyandu Melati")
//        }
//    }

    private fun getlistanakcari() {
        try {

            ApiService.endpoint.getanakCARI(anak)
                .enqueue(object : Callback<ListAnak> {
                    override fun onResponse(
                        call: Call<ListAnak>,
                        response: Response<ListAnak>
                    ) {
                        val status = response.body()?.status
                        val data = response.body()?.data
                        Log.d("responseData", data.toString())
                        if (status == "success" && data != null) {
                            // set adapter and layout manager for rv
                            rvListAnak.adapter = AdapterAnak(this@AnakActivity, data)
                            rvListAnak.layoutManager = LinearLayoutManager(this@AnakActivity)

                        } else {
                            Toast.makeText(
                                this@AnakActivity, "Tidak ada List",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                    }
//                    {
//                        Log.d("Req Response", response.body().toString())
//                        val data = response.body()?.data
//                        if (response.body()?.sukses == true && data != null) {
//                            // set adapter and layout manager
//                            rvListAnak.adapter = AdapterAnak(this@AnakActivity, data)
//                            rvListAnak.layoutManager = LinearLayoutManager(this@AnakActivity)
//                        } else {
//                            Toast.makeText(
//                                this@AnakActivity, "Tidak ada pesanan.",
//                                Toast.LENGTH_SHORT
//                            ).show()
//                        }
//                    }

                    override fun onFailure(call: Call<ListAnak>, t: Throwable) {
                        Toast.makeText(this@AnakActivity, t.message, Toast.LENGTH_SHORT).show()
                        Log.d("Error List", t.toString())
                    }

                })
        } catch (e: Exception) {
            Log.d("catch error", e.message.toString())
            Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        fun getInstance(): AnakActivity = AnakActivity()
    }
}