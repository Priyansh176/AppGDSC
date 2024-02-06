package com.example.dobtodays

import android.os.Bundle
import android.widget.CalendarView
import android.widget.CalendarView.OnDateChangeListener
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import kotlin.math.absoluteValue
import java.time.Month

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var dateOfBirth = findViewById<EditText>(R.id.date)
        var monthOfBirth = findViewById<EditText>(R.id.month)
        var yearOfBirth = findViewById<EditText>(R.id.year)


        var btnStart = findViewById<AppCompatButton>(R.id.btnStart)

        var calenderDate = findViewById<CalendarView>(R.id.calender)

        calenderDate.setOnDateChangeListener(
                OnDateChangeListener{ _, year, month, dayOfMonth ->
                    var currentDate:Int = dayOfMonth
                    var currentMonth:Int = month + 1
                    var currentYear:Int = year


                    btnStart.setOnClickListener{
                        if(dateOfBirth.text.toString()=="" || dateOfBirth.text == null){
                            dateOfBirth.error = "Required"
                        }
                        else if(monthOfBirth.text.toString()=="" || monthOfBirth.text == null){
                            monthOfBirth.error = "Required"
                        }
                        else if(yearOfBirth.text.toString()=="" || yearOfBirth.text == null){
                            yearOfBirth.error = "Required"
                        }

                        else {
                            val day1 = Integer.parseInt(dateOfBirth.text.toString())
                            val month1 = Integer.parseInt(monthOfBirth.text.toString())
                            val year1 = Integer.parseInt(yearOfBirth.text.toString())

                            val day2 = currentDate
                            val month2 = currentMonth
                            val year2 = currentYear


                            val daysInYear =
                                intArrayOf(0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31)

                            fun isLeapYear(year: Int): Boolean {
                                return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)
                            }

                            val totalDays1 = day1 + daysInYear.take(month1)
                                .sum() + (year1 - 1) * 365 + (1 until year1).count(::isLeapYear)
                            val totalDays2 = day2 + daysInYear.take(month2)
                                .sum() + (year2 - 1) * 365 + (1 until year2).count(::isLeapYear)

                            val numDays = Math.abs(totalDays2 - totalDays1)

                            var days = findViewById<TextView>(R.id.days)

                            days.text = "Days you lived- $numDays"
                        }
                    }
                }
            )
    }

}