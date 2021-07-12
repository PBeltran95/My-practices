package com.example.peso

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.peso.databinding.ActivityMainBinding
import com.example.peso.databinding.ActivityMainBinding.inflate
import kotlin.math.pow


class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        mBinding = inflate(layoutInflater)
        setContentView(mBinding.root)

        mBinding.btnCalculate.setOnClickListener { calculateImc() }

        mBinding.btnInfo.setOnClickListener { goToWebsite() }
        
        mBinding.ibShare.setOnClickListener { share() }
    }


    private fun calculateImc(): String {


        var stringWeight = mBinding.edWeight.text.toString().toDoubleOrNull()

        var stringHeight = mBinding.edHeight.text.toString().toDoubleOrNull()

        if (stringWeight == null) {
            stringWeight = 0.0
        }
        if (stringHeight == null) {
            stringHeight = 0.0
        }

        var imc = stringWeight / (stringHeight.pow(2))

        if (imc.isNaN()) {
            imc = 0.0
        }

        val conclusion: String = if (imc < 18.5 && imc > 1.0) {
            getString(R.string.low_weight)
        } else if (imc < 25 && imc > 18.5) {
            getString(R.string.normal_weight)
        } else if (imc < 29.9 && imc > 25.0) {
            getString(R.string.over_weight)
        } else if (imc < 34.5 && imc > 30.0) {
            getString(R.string.obesity_1)
        } else if (imc < 39.9 && imc > 35.0) {
            getString(R.string.obesity_2)
        } else if (imc > 40) {
            getString(R.string.obesity_3)
        } else {
            getString(R.string.null_error)
        }

        val number: String = String.format("%.2f", imc)
        val presentation = getString(R.string.my_imc_is)
        val have = getString(R.string.and_have)


        mBinding.tvResult.text = String.format("%.2f", imc)
        mBinding.tvVeredict.text = conclusion

        return "$presentation $number $have $conclusion."
    }


    private fun goToWebsite(){
        val url = getString(R.string.imc_info_resource)
        val i = Intent(Intent.ACTION_VIEW)
        i.data = Uri.parse(url)
        startActivity(i)
        }

    private fun share() {
        val finalConclusion = calculateImc()
        val intent = Intent()
        intent.action = Intent.ACTION_SEND
        intent.putExtra(Intent.EXTRA_TEXT, finalConclusion)
        intent.type = "text/plain"
        startActivity(Intent.createChooser(intent, "Share To:"))
    }


}