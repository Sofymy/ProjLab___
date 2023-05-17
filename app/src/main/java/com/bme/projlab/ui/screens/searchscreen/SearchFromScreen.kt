package com.bme.projlab.ui.screens.searchscreen
import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bme.projlab.domain.model.element.DestinationTraits
import com.bme.projlab.domain.viewmodel.SearchFromViewModel
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchFromScreen(
    viewModel: SearchFromViewModel = hiltViewModel(),
    onItemClick: (String) -> Unit
) {
    Column{
        Scaffold(topBar = {
            SearchView(viewModel, "Where are you going from?")
        }) { padding ->
            Column(modifier = Modifier.padding(padding)) {
                SearchList(viewModel, onItemClick)
            }
        }
    }
}


@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun SearchView(
    searchFromViewModel: SearchFromViewModel,
    text: String
) {
    var query by remember { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()

    Box(
        modifier = Modifier.fillMaxWidth()
            .padding(horizontal = 10.dp)
    ) {
        BasicTextField(
            value = query,
            onValueChange = {
                query = it
                coroutineScope.launch {
                    searchFromViewModel.performQuery(it)
                }
            },
            modifier = Modifier.height(38.dp).fillMaxWidth()
                .background(color = Color(0xFFD2F3F2), shape = RoundedCornerShape(size = 16.dp)),
            textStyle = TextStyle(
            ),
            singleLine = true,
            cursorBrush = Brush.verticalGradient(colors = listOf(
                Color(0xFF000000),
                Color(0xFF4D4D4D)
            )),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            maxLines = 1
        ) { innerTextField ->
            Box(
                contentAlignment = Alignment.CenterStart
            ) {
                if (query.isEmpty()) {
                    Text(
                        text = text,
                    )
                    coroutineScope.launch {
                        searchFromViewModel.loadDestinations()
                    }
                } else
                    coroutineScope.launch {
                        searchFromViewModel.performQuery(query)
                    }
                innerTextField()

            }
        }
    }
}

@Composable
fun SearchListItem(
    destinationItem: DestinationTraits,
    modifier: Modifier = Modifier,
    onItemClick: (String) -> Unit
) {
    Surface(
        modifier
            .padding(vertical = 8.dp, horizontal = 8.dp)
            .clip(MaterialTheme.shapes.medium)
    ) {
        Row(modifier.padding(vertical = 8.dp, horizontal = 16.dp)) {
            Column(modifier = Modifier
                .clickable(
                    onClick = {
                        onItemClick(destinationItem.name)
                    })
                .height(57.dp)
                .fillMaxWidth()){
                Text(
                    text = destinationItem.name,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 12.dp)
                        .wrapContentWidth(Alignment.Start)
                )
            }
        }
    }
}

@Composable
fun SearchList(
    searchFromViewModel: SearchFromViewModel,
    onItemClick: (String) -> Unit
){
    val list = searchFromViewModel.list.observeAsState().value
    LazyColumn {
        if (!list.isNullOrEmpty()) {
            items(list) { fromItem ->
                SearchListItem(fromItem
                ) { fromDestination ->
                    onItemClick(fromDestination)
                }
                Surface(Modifier.padding(horizontal = 24.dp)) {
                    Divider(
                    )
                }
            }
        }
    }
}
