package com.example.ryusei.calcapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.ceil
import kotlin.math.floor

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.zeroButton)
        findViewById<Button>(R.id.oneButton)
        findViewById<Button>(R.id.twoButton)
        findViewById<Button>(R.id.threeButton)
        findViewById<Button>(R.id.fourButton)
        findViewById<Button>(R.id.fiveButton)
        findViewById<Button>(R.id.sixButton)
        findViewById<Button>(R.id.sevenButton)
        findViewById<Button>(R.id.eightButton)
        findViewById<Button>(R.id.nineButton)
        findViewById<Button>(R.id.dotButton)
        findViewById<Button>(R.id.equalButton)
        findViewById<Button>(R.id.plusButton)
        findViewById<Button>(R.id.minusButton)
        findViewById<Button>(R.id.mulButton)
        findViewById<Button>(R.id.divButton)
        findViewById<Button>(R.id.acButton)
        findViewById<Button>(R.id.acButton)
        findViewById<TextView>(R.id.resultText)

        zeroButton.setOnClickListener { view -> addStringToResultText("0") }
        oneButton.setOnClickListener { view -> addStringToResultText("1") }
        twoButton.setOnClickListener { view -> addStringToResultText("2") }
        threeButton.setOnClickListener { view -> addStringToResultText("3") }
        fourButton.setOnClickListener { view -> addStringToResultText("4") }
        fiveButton.setOnClickListener { view -> addStringToResultText("5") }
        sixButton.setOnClickListener { view -> addStringToResultText("6") }
        sevenButton.setOnClickListener { view -> addStringToResultText("7") }
        eightButton.setOnClickListener { view -> addStringToResultText("8") }
        nineButton.setOnClickListener { view -> addStringToResultText("9") }

        dotButton.setOnClickListener { view -> addDotToResultText() }

        equalButton.setOnClickListener { view -> calc(tmpValue, resultText.text.toString()) }
        plusButton.setOnClickListener { view -> changeOperator("add") }
        minusButton.setOnClickListener { view -> changeOperator("sub") }
        mulButton.setOnClickListener { view -> changeOperator("mul") }
        divButton.setOnClickListener { view -> changeOperator("div") }

        acButton.setOnClickListener { view -> allClear() }
        cButton.setOnClickListener { view -> resultText.text = "0" }
    }

    fun addStringToResultText(str: String) {
        if (resultText.text.length >= 9) return
        resultText.text = if (resultText.text.length > 1 || resultText.text != "0") {
            resultText.text.toString() + str
        }
        else {
            str
        }
    }

    fun addDotToResultText(){
        if(resultText.text.indexOf(".") == -1) {
            resultText.text = resultText.text.toString() + "."
        }
    }

    var tmpValue = "0"
    var operator = "equal"

    fun calc(str1: String, str2: String){
        val num1 = str1.toDouble()
        val num2 = str2.toDouble()
        when(operator){
            "add" -> resultText.text = (num1 + num2).toString()
            "sub" -> resultText.text = (num1 - num2).toString()
            "mul" -> resultText.text = (num1 * num2).toString()
            "div" -> resultText.text = if(num2 != 0.0) (num1 / num2).toString() else "9999999999999999"
            else -> return
        }
        val value = resultText.text.toString().toDouble()
        if(ceil(value) == floor(value)){
            resultText.text = value.toLong().toString()
        }

        operator = "equal"
    }

    fun changeOperator(opr: String){
        if(operator != "equal") {
            equalButton.callOnClick()
        }
        operator = opr
        tmpValue = resultText.text.toString()
        resultText.text = "0"
    }

    fun allClear(){
        tmpValue = "0"
        resultText.text = "0"
        operator = "equal"
    }
}
