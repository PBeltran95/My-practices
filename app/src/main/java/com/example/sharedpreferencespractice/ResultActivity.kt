package com.example.sharedpreferencespractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.example.sharedpreferencespractice.UserVipApplication.Companion.preferences
import com.example.sharedpreferencespractice.databinding.ActivityResultBinding
import com.example.sharedpreferencespractice.databinding.ActivityResultBinding.inflate

class ResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = inflate(layoutInflater)
        setContentView(binding.root)
        initActivity()
    }

    private fun initActivity() {
        binding.btnBack.setOnClickListener {
            preferences.deleteAll()
            onBackPressed()
        }
        val userName = preferences.getName()
        binding.tvResultName.text = "Bienvenido $userName"
        if (preferences.getVip()){
            setVipBackGround()
        }

    }

    private fun setVipBackGround() {
        binding.containerVip.setBackgroundColor(ContextCompat.getColor(this,
        android.R.color.holo_green_light))
    }
}