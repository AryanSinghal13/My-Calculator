package com.example.mycalculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var resultTextView: TextView
    private var operand1: Double? = null
    private var operator: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        resultTextView = findViewById(R.id.resultTextView)

        val numberButtons = arrayOf(
            findViewById<Button>(R.id.button0),
            findViewById<Button>(R.id.button1),
            findViewById<Button>(R.id.button2),
            findViewById<Button>(R.id.button3),
            findViewById<Button>(R.id.button4),
            findViewById<Button>(R.id.button5),
            findViewById<Button>(R.id.button6),
            findViewById<Button>(R.id.button7),
            findViewById<Button>(R.id.button8),
            findViewById<Button>(R.id.button9)
        )

        val operatorButtons = arrayOf(
            findViewById<Button>(R.id.buttonPlus),
            findViewById<Button>(R.id.buttonMinus),
            findViewById<Button>(R.id.buttonMultiply),
            findViewById<Button>(R.id.buttonDivide)
        )

        val equalsButton = findViewById<Button>(R.id.buttonEquals)
        val clearButton = findViewById<Button>(R.id.buttonClear)

        // Set click listeners for number buttons
        for (i in numberButtons.indices) {
            numberButtons[i].setOnClickListener {
                appendNumber(i)
            }
        }

        // Set click listeners for operator buttons
        for (operatorButton in operatorButtons) {
            operatorButton.setOnClickListener {
                setOperator(operatorButton.text.toString())
            }
        }

        equalsButton.setOnClickListener {
            calculateResult()
        }

        clearButton.setOnClickListener {
            clear()
        }
    }

    private fun appendNumber(number: Int) {
        val currentText = resultTextView.text.toString()
        resultTextView.text = currentText + number
    }

    private fun setOperator(op: String) {
        operand1 = resultTextView.text.toString().toDoubleOrNull()
        operator = op
        resultTextView.text = ""
    }

    private fun calculateResult() {
        val operand2 = resultTextView.text.toString().toDoubleOrNull()
        if (operand1 != null && operator != null && operand2 != null) {
            val result = when (operator) {
                "+" -> operand1!! + operand2
                "-" -> operand1!! - operand2
                "*" -> operand1!! * operand2
                "/" -> operand1!! / operand2
                else -> null
            }
            resultTextView.text = result?.toString()
        }
    }

    private fun clear() {
        resultTextView.text = ""
        operand1 = null
        operator = null
    }
}
