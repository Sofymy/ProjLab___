package com.bme.projlab.ui.screens.profilescreen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.CardTravel
import androidx.compose.material.icons.filled.Signpost
import androidx.compose.material.icons.filled.TravelExplore
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.ui.foundation.Clickable
import com.bme.projlab.domain.viewmodel.VisitedViewModel
import com.bme.projlab.ui.theme.Background
import com.bme.projlab.ui.theme.Blue
import com.bme.projlab.ui.theme.GradientBrush
import com.bme.projlab.ui.theme.Grey
import com.bme.projlab.ui.theme.Purple
import com.bme.projlab.ui.theme.White
import com.valentinilk.shimmer.shimmer
import com.wajahatkarim.flippable.Flippable
import com.wajahatkarim.flippable.rememberFlipController
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnrememberedMutableState", "CoroutineCreationDuringComposition")
@Composable
fun VisitedScreen(
    viewModel: VisitedViewModel = hiltViewModel()
) {
    // this variable use to handle list state
    val notesList = remember {
        mutableStateListOf<String>()
    }

    val coroutineScope = rememberCoroutineScope()

    coroutineScope.launch {
        for (item in viewModel.getVisitedLocations()!!){
            if(!notesList.contains(item)){
                notesList.add(item)
            }
        }
    }
    // this variable use to handle edit text input value
    val inputvalue = remember { mutableStateOf(TextFieldValue()) }

    Column(modifier = Modifier.padding(15.dp)) {
        Row(
            Modifier
                .fillMaxWidth()
                .height(Dp(60f))
        ) {

            TextField(
                value = inputvalue.value,
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = White,
                    textColor = com.bme.projlab.ui.theme.Text,
                    placeholderColor = Grey,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                onValueChange = {
                    inputvalue.value = it
                },
                modifier = Modifier
                    .weight(0.75f)
                    .height(57.dp)
                    .fillMaxWidth()
                    .border(1.dp, brush = GradientBrush, shape = RoundedCornerShape(size = 7.dp))
                ,
                placeholder = { Text(text = "Enter a city",
                    style = MaterialTheme.typography.bodyMedium) },
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Words,
                    autoCorrect = true, keyboardType = KeyboardType.Text, imeAction = ImeAction.Done
                ),
                textStyle = MaterialTheme.typography.bodyMedium,
                maxLines = 1,
                singleLine = true
            )
            Button(
                onClick = {
                    if(!notesList.contains(inputvalue.value.text)){
                        notesList.add(inputvalue.value.text)
                        viewModel.addVisitedLocation(inputvalue.value.text)
                    }
                    inputvalue.value = TextFieldValue("")
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Background
                ),
                modifier = Modifier
                    .weight(0.25f)
                    .fillMaxHeight()
            ) {
                Text(text = "Add")
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Surface(modifier = Modifier
            .height(500.dp)
            .fillMaxWidth(),
            color = Color.Transparent,
        ) {
            Flippable(
                frontSide = {
                    Card(
                        modifier = Modifier
                            .shimmer()
                            .align(Alignment.CenterHorizontally)
                            .border(shape = RoundedCornerShape(7), width = 2.dp, color = Grey),
                        colors = CardDefaults.cardColors(
                            containerColor = Grey
                        ),
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 15.dp
                        ),
                        shape = RoundedCornerShape(7),
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(500.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = "PASSPORT",
                                    color = Purple,
                                    style = MaterialTheme.typography.bodyMedium,
                                )
                                Spacer(modifier = Modifier.height(10.dp))
                                Text(
                                    text = "Tap to open your Passport",
                                    color = Purple,
                                    style = MaterialTheme.typography.bodyMedium,
                                )
                                Spacer(modifier = Modifier.height(20.dp))
                                Icon(imageVector = Icons.Default.Book,
                                    contentDescription = "passport",
                                    tint = Purple
                                )

                            }
                        }
                    }
                },

                backSide = {
                    Card(
                        modifier = Modifier
                            .shimmer()
                            .align(Alignment.CenterHorizontally)
                            .border(shape = RoundedCornerShape(7), width = 2.dp, color = Grey),
                        colors = CardDefaults.cardColors(
                            containerColor = Grey
                        ),
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 15.dp
                        ),
                        shape = RoundedCornerShape(7),
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(15.dp)
                                .height(500.dp),
                        ) {
                            Column(
                                horizontalAlignment = Alignment.Start
                            ) {
                                Text(
                                    text = "Tap to close your Passport",
                                    color = Purple,
                                    style = MaterialTheme.typography.bodyMedium,
                                )
                                Spacer(modifier = Modifier.height(10.dp))
                                LazyColumn {
                                    itemsIndexed(notesList) { _, item ->
                                        Row {
                                            Icon(imageVector = Icons.Filled.TravelExplore,
                                                contentDescription = "passport",
                                                tint = com.bme.projlab.ui.theme.Text
                                            )
                                            Spacer(modifier = Modifier.width(4.dp))
                                            Text(
                                                item,
                                                style = MaterialTheme.typography.bodyMedium
                                            )
                                        }
                                        Spacer(modifier = Modifier.height(5.dp))
                                    }
                                }
                                Spacer(modifier = Modifier.height(20.dp))
                            }
                        }
                    }

                },

                flipController = rememberFlipController(),
            )
        }
    }
}



