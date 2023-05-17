package com.bme.projlab.domain.viewmodel

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bme.projlab.data.repository.DataSourceRepositoryImpl
import com.bme.projlab.data.repository.DestinationRepositoryImpl
import com.bme.projlab.domain.model.element.Destination
import com.bme.projlab.domain.model.element.DestinationTraits
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

@HiltViewModel
class SearchDestinationViewModel @Inject constructor(
    private val destinationRepositoryImpl: DestinationRepositoryImpl
) : ViewModel() {
    private var _list = MutableLiveData<List<DestinationTraits>>()
    val list: LiveData<List<DestinationTraits>>
        get() = _list


    suspend fun loadDestinations() {
        _list.postValue(destinationListData())
    }

    suspend fun performQuery(query: String) {
        val filteredList = destinationListData()
            .filter {
                it.name?.lowercase(Locale.getDefault())?.startsWith(
                    query.lowercase(Locale.getDefault())
                ) == true
            }
        _list.value = filteredList
    }

    private suspend fun destinationListData(): ArrayList<DestinationTraits> {
        val lowercase = destinationRepositoryImpl.loadDestinations()
        for(city in lowercase){
            city.name = city.name.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
        }
        return lowercase
    }
}