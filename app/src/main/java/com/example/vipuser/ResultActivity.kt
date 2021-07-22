package com.example.vipuser

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.example.vipuser.UserVipApplication.Companion.prefs
import com.example.vipuser.databinding.ActivityResultBinding
import com.example.vipuser.databinding.ActivityResultBinding.inflate

class ResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = inflate(layoutInflater)
        setContentView(binding.root)
        initUI()
    }

    private fun initUI() {
        binding.btnLogout.setOnClickListener {
            prefs.wipe()
            onBackPressed()
        }
        val userName = prefs.getName()
        binding.tvResult.text = "Bienvenido $userName"
        if (prefs.getVip()){
            setVipBackground()
        }
    }

    private fun setVipBackground() {
        binding.container.setBackgroundColor(ContextCompat.getColor(this,
            android.R.color.holo_orange_light
        ))
    }


}