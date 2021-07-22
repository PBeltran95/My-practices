package com.example.dogphotos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.SearchView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dogphotos.databinding.ActivityMainBinding
import com.example.dogphotos.databinding.ActivityMainBinding.inflate
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

class MainActivity : AppCompatActivity(), androidx.appcompat.widget.SearchView.OnQueryTextListener {
    private lateinit var mBinding:ActivityMainBinding
    private lateinit var adapter:DogAdapter
    private val dogImages = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = inflate(layoutInflater)
        setContentView(mBinding.root)
        mBinding.svDogs.setOnQueryTextListener(this)
        innitRecyclerView()
    }

    private fun innitRecyclerView() {
        adapter = DogAdapter(dogImages)
        mBinding.rvDogs.layoutManager = LinearLayoutManager(this)
        mBinding.rvDogs.adapter = adapter
    }


    private fun getRetrofit():Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://dog.ceo/api/breed/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun searchByName(query:String){
        CoroutineScope(Dispatchers.IO).launch {
            val call:Response<DogResponse> = getRetrofit().create(APIService::class.java)
                .getDogsByBreeds("$query/images")
            val puppies:DogResponse? = call.body()

            runOnUiThread {
                if (call.isSuccessful){
                    val images:List<String> = puppies?.images ?: emptyList()
                    dogImages.clear()
                    dogImages.addAll(images)
                    adapter.notifyDataSetChanged()
                 }else{
                    showError()
                }
                hideKeyboard()
            }

        }
    }

    private fun hideKeyboard() {
        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(mBinding.supremeParent.windowToken, 0)
    }

    private fun showError() {
        Toast.makeText(this, "Ha ocurrido un error",Toast.LENGTH_SHORT).show()
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (!query.isNullOrEmpty()){
            searchByName(query.toLowerCase())
        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean = true
}