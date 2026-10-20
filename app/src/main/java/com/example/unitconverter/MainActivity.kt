package com.example.unitconverter

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var dropDownFrom: Spinner
    private lateinit var dropDownTo: Spinner
    private lateinit var unitInput: EditText
    private lateinit var convertButton: Button
    private lateinit var unitOutput: TextView

    private val conversionRates = mapOf(
        // Kilometers
        "kmtom" to 1000.0f,
        "kmtocm" to 100000.0f,
        "kmtomiles" to 0.6213712f,
        "kmtofeet" to 3280.8398f,
        "kmtoinches" to 39370.08f,

        // Meters
        "mtokm" to 0.001f,
        "mtocm" to 100.0f,
        "mtomiles" to 0.0006213712f,
        "mtofeet" to 3.28084f,
        "mtoinches" to 39.37008f,

        // Centimeters
        "cmtokm" to 0.00001f,
        "cmtom" to 0.01f,
        "cmtomiles" to 0.000006213712f,
        "cmtofeet" to 0.0328084f,
        "cmtoinches" to 0.3937008f,

        // Miles
        "milestokm" to 1.609344f,
        "milestom" to 1609.344f,
        "milestocm" to 160934.4f,
        "milestofeet" to 5280.0f,
        "milestoinches" to 63360.0f,

        // Feet
        "feettokm" to 0.0003048f,
        "feettom" to 0.3048f,
        "feettocm" to 30.48f,
        "feettomiles" to 0.0001893939f,
        "feettoinches" to 12.0f,

        // Inches
        "inchestokm" to 0.0000254f,
        "inchestom" to 0.0254f,
        "inchestocm" to 2.54f,
        "inchestomiles" to 0.00001578283f,
        "inchestofeet" to 0.083333336f
        )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        dropDownFrom = findViewById(R.id.dropDownFrom)
        dropDownTo = findViewById(R.id.dropDownTo)
        unitInput = findViewById(R.id.unitInput)
        convertButton = findViewById(R.id.convertButton)
        unitOutput = findViewById(R.id.unitOutput)

        ArrayAdapter.createFromResource(this, R.array.units,
            android.R.layout.simple_spinner_dropdown_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            dropDownFrom.adapter = adapter
            dropDownTo.adapter = adapter
        }

        convertButton.setOnClickListener {
            val fromUnit = dropDownFrom.selectedItem.toString()
            val toUnit = dropDownTo.selectedItem.toString()
            val input = unitInput.text.toString()

            if (input.isNotEmpty() && fromUnit != toUnit) {
                val result = input.toFloat() * (conversionRates["${fromUnit}to${toUnit}"] ?: 0.0f)
                unitOutput.text = "$input $fromUnit is equal to ${"%.3f".format(result)} $toUnit"
            }
            else {
                unitOutput.text = getString(R.string.invalid_input)
            }
        }
    }
}