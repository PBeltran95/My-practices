package com.example.vipuser

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.vipuser.UserVipApplication.Companion.prefs
import com.example.vipuser.databinding.ActivityMainBinding
import com.example.vipuser.databinding.ActivityMainBinding.inflate

class MainActivity : AppCompatActivity() {
    private lateinit var mBinding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = inflate(layoutInflater)
        setContentView(mBinding.root)
        initUI()
        checkUserValues()
    }

    private fun checkUserValues() {
        if (prefs.getName().isNotEmpty())
            goToDetail()
    }

    private fun initUI() {
        mBinding.btnLogin.setOnClickListener {
            accessToDetail()
        }
    }

    private fun accessToDetail() {
        if (mBinding.etName.text.toString().isNotEmpty()){
            prefs.saveName(mBinding.etName.text.toString())
            prefs.saveVip(mBinding.cbVip.isChecked)
            goToDetail()
        }else{ Toast.makeText(this, "Tenes que poner un nombre, idiota",Toast.LENGTH_SHORT).show()
        }
    }

    private fun goToDetail() {
        startActivity(Intent(this,ResultActivity::class.java))
    }
}