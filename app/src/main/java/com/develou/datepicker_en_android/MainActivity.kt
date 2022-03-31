package com.develou.datepicker_en_android

import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class MainActivity : AppCompatActivity() {
    private lateinit var dateField: EditText
    private val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val defaultDateString = LocalDate.now().format(formatter)

        dateField = findViewById(R.id.date_field)
        dateField.setText(defaultDateString)
        dateField.setOnClickListener {
            showDatePicker()
        }
    }

    private fun showDatePicker() {
        val date = getCurrentDate()
        DatePickerFragment.newInstance(
            date.year,
            date.monthValue,
            date.dayOfMonth
        ) { _, year, month, day ->
            dateField.setText(formatDate(year, month, day))
        }.show(supportFragmentManager, "date-picker")
    }

    private fun getCurrentDate(): LocalDate {
        val date = dateField.text.toString()
        return LocalDate.parse(date, formatter)
    }

    private fun formatDate(year: Int, month: Int, day: Int): String {
        val sanitizeMonth = month + 1
        return LocalDate.of(year, sanitizeMonth, day).format(formatter)
    }
}
