package com.example.kriscalculator

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.kriscalculator.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        var firstNumber: Double? = null
        var operation: String? = null

        val numberClickListener = View.OnClickListener { view ->
            val currentText = binding.txtNumber.text.toString()
            val clickedNumber = (view as Button).text.toString()

            Log.d("Calculator", "Clicked number: $clickedNumber")

            // Append the clicked number to the current text
            val newText = if (currentText == "0") {
                clickedNumber
            } else {
                currentText + clickedNumber
            }
            binding.txtNumber.text = newText // Use text property to update the TextView
        }

        binding.btnZero.setOnClickListener(numberClickListener)
        binding.btnOne.setOnClickListener(numberClickListener)
        binding.btnTwo.setOnClickListener(numberClickListener)
        binding.btnThree.setOnClickListener(numberClickListener)
        binding.btnFour.setOnClickListener(numberClickListener)
        binding.btnFive.setOnClickListener(numberClickListener)
        binding.btnSix.setOnClickListener(numberClickListener)
        binding.btnSeven.setOnClickListener(numberClickListener)
        binding.btnEight.setOnClickListener(numberClickListener)
        binding.btnNine.setOnClickListener(numberClickListener)

        binding.btnClear.setOnClickListener {
            binding.txtNumber.setText("0")
            firstNumber = null
            operation = null
        }

        binding.btnPlusMinus.setOnClickListener {
            val currentText = binding.txtNumber.text.toString()
            if (currentText.isNotEmpty()) {
                val currentNumber = currentText.toDoubleOrNull()

                if (currentNumber != null) {
                    val toggledNumber = -currentNumber
                    binding.txtNumber.text = toggledNumber.toInt().toString()
                }
            }
        }

        binding.btnPercent.setOnClickListener {

        }

        binding.btnDivide.setOnClickListener {
            val currentText = binding.txtNumber.text.toString()
            if (currentText.isNotEmpty()) {
                firstNumber = currentText.toDoubleOrNull()
                operation = "DIVIDE"
                binding.txtNumber.setText("")
            }
        }

        binding.btnMultiply.setOnClickListener {
            val currentText = binding.txtNumber.text.toString()
            if (currentText.isNotEmpty()) {
                firstNumber = currentText.toDoubleOrNull()
                operation = "MULTIPLY"
                binding.txtNumber.setText("")
            }
        }

        binding.btnMinus.setOnClickListener {
            val currentText = binding.txtNumber.text.toString()
            if (currentText.isNotEmpty()) {
                firstNumber = currentText.toDoubleOrNull()
                operation = "MINUS"
                binding.txtNumber.setText("")
            }
        }

        binding.btnPlus.setOnClickListener {
            val currentText = binding.txtNumber.text.toString()
            if (currentText.isNotEmpty()) {
                firstNumber = currentText.toDoubleOrNull()
                operation = "PLUS"
                binding.txtNumber.setText("")
            }
        }


        binding.btnEqual.setOnClickListener {
            val secondText = binding.txtNumber.text.toString()
            if (secondText.isNotEmpty() && firstNumber != null && operation != null) {
                val secondNumber = secondText.toDoubleOrNull()

                val result: Double? = when (operation) {
                    "DIVIDE" -> {
                        if (secondNumber != null && secondNumber != 0.0) {
                            firstNumber!! / secondNumber
                        } else {
                            null // Handle division by zero
                        }
                    }
                    "MULTIPLY" -> {
                        firstNumber!! * secondNumber!!
                    }
                    "PLUS" -> {
                        firstNumber!! + secondNumber!!
                    }
                    "MINUS" -> {
                        firstNumber!! - secondNumber!!
                    }
                    else -> null
                }

                if (result != null) {
                    // Check if the result is an integer
                    val resultText = if (result == result.toInt().toDouble()) {
                        result.toInt().toString()
                    } else {
                        result.toString()
                    }

                    binding.txtNumber.text = resultText

                    // Display the result as a toast
                    Toast.makeText(this, "Result: $resultText", Toast.LENGTH_SHORT).show()
                } else {
                    binding.txtNumber.text = "Error"
                    Toast.makeText(this, "Error: Division by zero", Toast.LENGTH_SHORT).show()
                }

                firstNumber = null
                operation = null
            }
        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}