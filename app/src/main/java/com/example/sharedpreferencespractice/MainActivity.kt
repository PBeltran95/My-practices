package com.example.sharedpreferencespractice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.sharedpreferencespractice.databinding.ActivityMainBinding
import com.example.sharedpreferencespractice.databinding.ActivityMainBinding.inflate
import com.example.sharedpreferencespractice.UserVipApplication.Companion.preferences as preferences1

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = inflate(layoutInflater)
        setContentView(mBinding.root)
        checkUserName()
        initUI()
    }

    private fun checkUserName() {
        if (preferences1.getName().isNotEmpty()){
            nextWindow()
        }
    }

    private fun initUI() {
        mBinding.btnNext.setOnClickListener {
            if (mBinding.etName.text.toString().isNotEmpty()){
                preferences1.saveName(mBinding.etName.text.toString())
                preferences1.saveVip(mBinding.cbVip.isChecked)
                nextWindow()
            }else Toast
                .makeText(this, "Deberias poner un nombre",Toast.LENGTH_SHORT)
                .show()
        }
    }



    private fun nextWindow() {
        startActivity(Intent(this, ResultActivity::class.java))
    }
}