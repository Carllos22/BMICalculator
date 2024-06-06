package com.example.bmicalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlin.math.pow
import com.google.android.material.slider.Slider


class MainActivity : AppCompatActivity() {

    lateinit var heightEditText: EditText
    lateinit var weightTextView: TextView
    lateinit var minusButton: Button
    lateinit var addButton: Button
    lateinit var descriptionTextView: TextView
    lateinit var resultTextView: TextView
    lateinit var calculateButton: Button
    lateinit var weightSlider: Slider

    var height = 150
    var weight = 70

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        heightEditText = findViewById(R.id.heightEditText)
        weightTextView = findViewById(R.id.weightTextView)
        minusButton = findViewById(R.id.minusButton)
        addButton = findViewById(R.id.addButton)
        descriptionTextView = findViewById(R.id.descriptionTextView)
        resultTextView = findViewById(R.id.resultTextView)
        calculateButton = findViewById(R.id.calcutateButton)
        weightSlider = findViewById(R.id.weightSlider)


        setHeight()
        setWeight()

        minusButton.setOnClickListener {
            weight--
            if (weight < 0) weight = 0
            setWeight()
            weightSlider.value = weight.toFloat()
        }

        addButton.setOnClickListener {
            weight++
            if (weight > 200) weight = 200
            setWeight()
            weightSlider.value = weight.toFloat()
        }

        weightSlider.addOnChangeListener { slider, value, fromUser ->
            weight = value.toInt()
            setWeight()
        }

        calculateButton.setOnClickListener {
            val heightText = heightEditText.text.toString()
            if (heightText.isNotEmpty()) {
                height = heightText.toInt()
                val result = weight / (height / 100f).pow(2)
                resultTextView.text = String.format("%.2f", result)
            } else {
                heightEditText.error = "Please enter your height"
            }
        }
    }
    fun setHeight() {
        heightEditText.setText(height.toString())

    }

    fun setWeight() {
        weightTextView.text = "$weight Kg"
    }
}
