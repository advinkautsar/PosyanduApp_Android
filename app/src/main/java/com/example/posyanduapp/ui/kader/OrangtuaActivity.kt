package com.example.posyanduapp.ui.kader

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Adapter
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.posyanduapp.Model.ListProfilOrtu
import com.example.posyanduapp.adapter.AdapterAnak
import com.example.posyanduapp.adapter.adapterOrtu.AdapterListOrtu
import com.example.posyanduapp.databinding.ActivityOrangtuaBinding
import com.example.posyanduapp.retrofit.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OrangtuaActivity : AppCompatActivity() {

//    private var ortulist = mutableListOf<String>()
//    private var poslist = mutableListOf<String>()

    lateinit var rvlistortu: RecyclerView
    lateinit var adapter: AdapterListOrtu
    var orangtua: String = ""
    private lateinit var binding : ActivityOrangtuaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrangtuaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        rvlistortu = binding.rvOrangtua
        getortuCARI()

        binding.search.setOnEditorActionListener { v, actionId, event ->
            if (actionId === EditorInfo.IME_ACTION_SEARCH) {
//
//                rvListAnak.adapter.clear()
                orangtua = binding.search.text.toString()
                getortuCARI()
                Log.d("nilaie", orangtua)
                Toast.makeText(
                    this, orangtua,
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

        //intent
        binding.btnKembaliorangtua.setOnClickListener {
            startActivity(Intent(this, BerandakaderActivity::class.java))
        }


    }

        private fun getortuCARI(){
            try {

                ApiService.endpoint.getortuCARI(orangtua)
                    .enqueue(object : Callback<ListProfilOrtu>{
                        override fun onResponse(
                            call: Call<ListProfilOrtu>,
                            response: Response<ListProfilOrtu>
                        ) {
                            val status = response.body()?.status
                            val data = response.body()?.data
                            Log.d("responseData", data.toString())
                            if (status == "success" && data != null){
                                rvlistortu.adapter = AdapterListOrtu(this@OrangtuaActivity, data)
                                rvlistortu.layoutManager = LinearLayoutManager(this@OrangtuaActivity)
                            }else {
                                Toast.makeText(
                                    this@OrangtuaActivity, "Tidak ada List",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }

                        override fun onFailure(call: Call<ListProfilOrtu>, t: Throwable) {
                            TODO("Not yet implemented")
                        }

                    })

            }catch (e: Exception) {
                Log.d("catch error", e.message.toString())
                Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
            }
        }

}