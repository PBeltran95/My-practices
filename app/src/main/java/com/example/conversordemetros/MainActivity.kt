package com.example.conversordemetros
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.example.conversordemetros.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.convertButton.setOnClickListener { calculateUnit() }

        binding.tvMeters.setOnKeyListener { view, _, keyCode  ->
            pressEnterEvent(view, keycode = 25)
        }

    }

    private fun pressEnterEvent(view: View, keycode: Int): Boolean {
        if(keycode == KeyEvent.KEYCODE_ENTER){
            val inputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
            return true
        }
        return false
    }

    private fun calculateUnit() {
        val stringInTextField = binding.tvMeters.text.toString()
        var meters = stringInTextField.toDoubleOrNull()
        if (meters == null){
            meters = 0.0
        }

        val selectedId = binding.unitOptions.checkedRadioButtonId

        val metersToConvert = when(selectedId){
                R.id.cb_kilometer ->    0.001
                R.id.cb_hectometer ->   0.01
                R.id.cb_decameter ->   0.1
                R.id.cb_decimeter ->   10
                R.id.cb_centemeter ->   100
                else -> 1000
            }
        val multiplier = metersToConvert.toDouble()

        val result = meters?.times(multiplier)

        val unitText:String = when(metersToConvert){
            0.001 -> "Kilometros"
            0.01 -> "Hectometros"
            0.1 -> "Decametros"
            10 -> "Decimetros"
            100 -> "Centimetros"
            else -> "Milimetros"
        }
        displayResult(result, unitText)
    }
    private fun displayResult(result:Double?, unitText:String){
        binding.finalResult.text = "$result $unitText"
    }
}