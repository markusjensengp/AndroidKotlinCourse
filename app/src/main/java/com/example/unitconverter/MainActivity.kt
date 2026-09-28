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

    private val units = arrayOf("km", "m", "cm", "miles", "feet", "inches")
    private lateinit var dropDownFrom: Spinner
    private lateinit var dropDownTo: Spinner
    private lateinit var unitInput: EditText
    private lateinit var convertButton: Button
    private lateinit var unitOutput: TextView

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
            val input = unitInput.text.toString()
            if (input.isNotEmpty()) {
                val result = (input.toFloat() * 2.54f).toString()
                unitOutput.text = getString(R.string.result) + " " + result
            }
            else {
                unitOutput.text = getString(R.string.empty_input)
                unitOutput.text = dropDownFrom.selectedItem.toString()
            }
        }
    }
}