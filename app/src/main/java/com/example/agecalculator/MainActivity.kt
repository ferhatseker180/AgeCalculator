package com.example.agecalculator

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonSelect.setOnClickListener {

            clickDatePicker()
        }
    }

    fun clickDatePicker(){

        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)

     val dpd =   DatePickerDialog(this,
             DatePickerDialog.OnDateSetListener { view, selectedYear, selectedMonth, selectedDayOfMonth ->

                 val selectedDate = "$selectedDayOfMonth/${selectedMonth+1}/$selectedYear"
                 textViewDate.text = selectedDate.toString()
                 val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

                 val theDate = sdf.parse(selectedDate)
                 theDate?.let {
                     val convertToMinute = theDate.time / 60000
                     val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                     currentDate?.let {
                         val currentDateToMinute = currentDate.time / 60000
                         val minus = currentDateToMinute - convertToMinute
                         val hours = minus/60
                         val day = hours / 24
                         textViewHours.text = hours.toString()
                         textViewTime.text = minus.toString()
                         textViewDay.text = day.toString()
                     }

                 }

             },
            year,
            month,
            day
            )


        dpd.datePicker.maxDate = System.currentTimeMillis()-86400000
            dpd.show()

    }

}