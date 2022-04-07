package com.example.posyanduapp.ui.kader

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.posyanduapp.R
import com.example.posyanduapp.adapter.AdapterAnak
import com.example.posyanduapp.adapter.adapterKader.AdapterFiturAnakKader
import com.example.posyanduapp.databinding.ActivityFiturAnakKaderBinding
import com.example.posyanduapp.model.ListAnak
import com.example.posyanduapp.retrofit.ApiService
import com.example.posyanduapp.ui.bidan.AnakActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FiturAnakKaderActivity : AppCompatActivity() {

    lateinit var rv_listAnakKader : RecyclerView
    lateinit var adapter : AdapterFiturAnakKader
    lateinit var binding:ActivityFiturAnakKaderBinding
    var anak: String = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFiturAnakKaderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        rv_listAnakKader = binding.rvAnakkader
        getlistanakcari()

        //intent
        binding.btnKembalianak.setOnClickListener {
            startActivity(Intent(this,BerandakaderActivity::class.java))
        }

        binding.search.setOnEditorActionListener { v, actionId, event ->
            if (actionId === EditorInfo.IME_ACTION_SEARCH) {
//
//                rvListAnak.adapter.clear()
                anak = binding.search.text.toString()
                getlistanakcari()
                Log.d("nilaie", anak)
                Toast.makeText(
                    this, anak,
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

    }

    private fun getlistanakcari(){
        try{
            ApiService.endpoint.getanakCARI(anak)
                .enqueue(object : Callback<ListAnak>{
                    override fun onResponse(call: Call<ListAnak>, response: Response<ListAnak>) {
                        val status = response.body()?.status
                        val data = response.body()?.data
                        Log.d("responseData", data.toString())
                        if (status == "success" && data != null){
                            rv_listAnakKader.adapter = AdapterFiturAnakKader(this@FiturAnakKaderActivity, data)
                            rv_listAnakKader.layoutManager = LinearLayoutManager(this@FiturAnakKaderActivity)
                        }
                    }

                    override fun onFailure(call: Call<ListAnak>, t: Throwable) {
                        Toast.makeText(
                            this@FiturAnakKaderActivity, "Tidak ada List",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                })



        }catch (e: Exception) {
            Log.d("catch error", e.message.toString())
            Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
        }
    }
    companion object {
        fun getInstance(): FiturAnakKaderActivity = FiturAnakKaderActivity()
    }
}