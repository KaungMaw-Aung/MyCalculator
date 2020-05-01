package com.example.mycalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import com.example.mycalculator.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var operationText: TextView
    private var oldValue = "0"

    private var num1 = 0
    private var num2 = 0
    private var operator: Char? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        operationText = binding.operationResultText
        operationText.text = oldValue
        oldValue = ""
        settingOnClick()
    }

    private fun settingOnClick() {
        val list = listOf<View>(one_button, two_button, three_button, four_button, five_button, six_button, seven_button, eight_button, nine_button, zero_button, all_clear_button, plus_button, minus_button, multiply_button, divide_button, modulus_button, equal_to_button)

        for (each in list) {
            each.setOnClickListener {
                onClickOperation(it)
            }
        }

    }

    private fun onClickOperation(it: View?) {
        oldValue = operationText.text.toString()
        var newValue = ""

        if (oldValue == "0") {
            oldValue = ""
            newValue = "0"
        }

        when (it!!.id) {
            R.id.one_button -> newValue = oldValue + "1"
            R.id.two_button -> newValue = oldValue + "2"
            R.id.three_button -> newValue = oldValue + "3"
            R.id.four_button -> newValue = oldValue + "4"
            R.id.five_button -> newValue = oldValue + "5"
            R.id.six_button -> newValue = oldValue + "6"
            R.id.seven_button -> newValue = oldValue + "7"
            R.id.eight_button -> newValue = oldValue + "8"
            R.id.nine_button -> newValue = oldValue + "9"
            R.id.zero_button -> if (oldValue != "") {
                newValue = oldValue + "0"
            }
            R.id.all_clear_button -> newValue = "0"
            R.id.plus_button -> newValue = assignOperator('+', oldValue)
            R.id.minus_button -> newValue = assignOperator('-', oldValue)
            R.id.multiply_button -> newValue = assignOperator('*', oldValue)
            R.id.divide_button -> newValue = assignOperator('/', oldValue)
            R.id.modulus_button -> newValue = assignOperator('%', oldValue)

            R.id.equal_to_button -> newValue = doOperation(oldValue)
        }
        operationText.text = newValue
    }

    private fun assignOperator(op: Char, inputDigit: String): String {
        num1 = if (inputDigit == "") {
            0
        } else {
            inputDigit.toInt()
        }
        operator = op
        Log.d("MainActivity", "Test: $num1 , $operator")
        return "0"
    }

    private fun doOperation(inputDigit: String): String {
        var finalResult = 0
        if (operator != null) {
            num2 = if (inputDigit == "") {
                0
            } else {
                inputDigit.toInt()
            }
            finalResult = when (operator) {
                '+' -> num1 + num2
                '-' -> num1 + num2
                '*' -> num1 + num2
                '/' -> num1 + num2
                '%' -> num1 + num2
                else -> 0
            }
        }
        Log.d("MainActivity", "Test: $num1 , $operator , $num2")
        return finalResult.toString()
    }
}
