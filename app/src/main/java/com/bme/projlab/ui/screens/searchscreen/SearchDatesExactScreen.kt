package com.bme.projlab.ui.screens.searchscreen

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bme.projlab.domain.viewmodel.HomeViewModel
import java.util.*

@Composable
fun SearchDatesExactScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    navigateToResults: (String, String) -> Unit,
    fromAirport: String,
    toAirport: String?
) {

    // Fetching the Local Context
    val mContext = LocalContext.current

    val mYear: Int
    val mMonth: Int
    val mDay: Int

    val fromCalendar = Calendar.getInstance()

    mYear = fromCalendar.get(Calendar.YEAR)
    mMonth = fromCalendar.get(Calendar.MONTH)
    mDay = fromCalendar.get(Calendar.DAY_OF_MONTH)

    fromCalendar.time = Date()

    val toCalendar = Calendar.getInstance()


    toCalendar.time = Date()

    val fromDate = remember { mutableStateOf("") }
    val toDate = remember { mutableStateOf("") }

    val fromDatePickerDialog = DatePickerDialog(
        mContext,
        { _: DatePicker, year: Int, month: Int, day: Int ->
            fromDate.value = "$year-${modifyDate(month+1)}-${modifyDate(day)}"
        }, mYear, mMonth, mDay
    )
    val toDatePickerDialog = DatePickerDialog(
        mContext,
        { _: DatePicker, year: Int, month: Int, day: Int ->
            toDate.value = "$year-${modifyDate(month+1)}-${modifyDate(day)}"
        }, mYear, mMonth, mDay
    )

    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        Button(onClick = {
            fromDatePickerDialog.show()
        }, colors = ButtonDefaults.buttonColors(containerColor = Color(0XFF0F9D58)) ) {
            Text(text = "FROM", color = Color.White)
        }
        Button(onClick = {
            toDatePickerDialog.show()
        }, colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF673AB7)) ) {
            Text(text = "TO", color = Color.White)
        }
        // Adding a space of 100dp height
        Spacer(modifier = Modifier.size(100.dp))

        // Displaying the mDate value in the Text
        Text(text = "FROM: ${fromDate.value}", fontSize = 30.sp, textAlign = TextAlign.Center)
        Text(text = "TO: ${toDate.value}", fontSize = 30.sp, textAlign = TextAlign.Center)

        Button(onClick = {
            navigateToResults(fromDate.value, toDate.value)
        }){
            Text("Next")
        }
    }
}

fun modifyDate(date: Int): String {
    return if(date<10){
        date.toString().padStart(2,'0')
    }
    else date.toString()
}