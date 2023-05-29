package com.bme.projlab.ui.screens.searchscreen

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowCircleDown
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bme.projlab.domain.viewmodel.HomeViewModel
import com.bme.projlab.ui.theme.Background
import com.bme.projlab.ui.theme.Blue
import com.bme.projlab.ui.theme.GradientBrush
import com.bme.projlab.ui.theme.Purple
import com.bme.projlab.ui.theme.White
import com.google.maps.android.ui.IconGenerator
import com.valentinilk.shimmer.shimmer
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchDatesExactScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    navigateToResults: (String, String) -> Unit,
    fromAirport: String,
    toAirport: String?,
    fromDestination: String?,
    toDestination: String?
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

    val fromDate = remember { mutableStateOf("$mYear-${modifyDate(mMonth+1)}-${modifyDate(mDay)}") }
    val toDate = remember { mutableStateOf("$mYear-${modifyDate(mMonth+1)}-${modifyDate(mDay)}") }

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

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(15.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Phases(0.9f)
        Text(
            "Pick a dates for your trip",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(15.dp))
        Column(
            modifier = Modifier
                .background(White)
                .border(
                    border = BorderStroke(1.dp, GradientBrush),
                    shape = RoundedCornerShape(7.dp)
                )
                .fillMaxWidth()
                .padding(15.dp)
                .clickable {
                    fromDatePickerDialog.show()
                },
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "To $toDestination", style = MaterialTheme.typography.bodyMedium)
            Text(text = "$toAirport airport", style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(15.dp))
            Text(
                fromDate.value,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onBackground
                )

        }
        Spacer(modifier = Modifier.height(15.dp))
        Icon(imageVector = Icons.Default.ArrowDropDown, tint = Purple, contentDescription = "",
modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        Spacer(modifier = Modifier.height(15.dp))
        Column(
            modifier = Modifier
                .background(White)
                .border(
                    border = BorderStroke(1.dp, GradientBrush),
                    shape = RoundedCornerShape(7.dp)
                )
                .fillMaxWidth()
                .padding(15.dp)
                .clickable {
                    toDatePickerDialog.show()
                },
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Fly back to $fromDestination", style = MaterialTheme.typography.bodyMedium)
            Text(text = "$fromAirport airport", style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(15.dp))
            Text(
                toDate.value,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground
            )

        }
        Spacer(modifier = Modifier.height(15.dp))
        Icon(imageVector = Icons.Default.ArrowDropDown, tint = Purple, contentDescription = "",
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(15.dp))
        Button(onClick = {
            navigateToResults(fromDate.value, toDate.value)
        },
            colors = ButtonDefaults.buttonColors(
                containerColor = Blue
            ),
            modifier = Modifier.shimmer()
        ){
            Text(
                "Let's see the results",
                style = MaterialTheme.typography.bodyMedium,
                color = White
            )
        }
    }
}

fun modifyDate(date: Int): String {
    return if(date<10){
        date.toString().padStart(2,'0')
    }
    else date.toString()
}