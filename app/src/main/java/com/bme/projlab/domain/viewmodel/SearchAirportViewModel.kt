package com.bme.projlab.domain.viewmodel

import android.annotation.SuppressLint
import android.location.Geocoder
import android.location.Location
import android.util.Log
import androidx.compose.Context
import androidx.core.content.PackageManagerCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bme.projlab.data.repository.AirportRepositoryImpl
import com.bme.projlab.domain.model.element.Airports
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.Locale
import javax.inject.Inject


@HiltViewModel
class SearchAirportViewModel @Inject constructor(
    private val airportRepositoryImpl: AirportRepositoryImpl,
) : ViewModel() {
    private var _list = MutableLiveData<List<Airports.Airport>>()
    val list: LiveData<List<Airports.Airport>>
        get() = _list


    suspend fun loadAirports(city: String) {
        _list.postValue(airportListData(city))
    }

    suspend fun loadAirports(city: String, context: Context, max: Int) {
        _list.postValue(airportListData(city, context, max))
    }


    suspend fun performQuery(query: String, city: String) {
        val filteredList = airportListData(city)
            .filter {
                it.resultingPhrase.lowercase(Locale.getDefault()).contains(
                    query.lowercase(Locale.getDefault())
                )
            }
        _list.value = filteredList
    }


    suspend fun performQuery(query: String, city: String, context: Context, max: Int) {
        val filteredList = airportListData(city, context, max)
            .filter {
                it.resultingPhrase.lowercase(Locale.getDefault()).contains(
                    query.lowercase(Locale.getDefault())
                )
            }
        _list.value = filteredList
    }


    private suspend fun airportListData(city: String): ArrayList<Airports.Airport> {
        val lowercase = airportRepositoryImpl.loadAirports(city)
        for(airport in lowercase){
            airport.resultingPhrase = airport.resultingPhrase.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
        }
        return lowercase
    }

    private suspend fun airportListData(city: String, context: Context, max: Int): ArrayList<Airports.Airport> {
        val lowercase = airportRepositoryImpl.loadAirports(city)
        val iterator = lowercase.iterator()
        while(iterator.hasNext()){
            val item = iterator.next()
            item.resultingPhrase = item.resultingPhrase.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
            if(!checkDistance(context, city, max, item)){
                iterator.remove()
            }
        }
        return lowercase
    }

    @SuppressLint("RestrictedApi")
    fun checkDistance(context: Context, city: String, max: Int, airport: Airports.Airport): Boolean{
        val geocoder = Geocoder(context, Locale.getDefault())
        try {
            val result = geocoder.getFromLocationName(city, 1)?.get(0)
            val airportLong = airport.location.substringAfter(",").toDouble()
            val airportLat= airport.location.substringBefore(",").toDouble()

            val distance = result?.let { calcDistance(it.longitude, it.latitude, airportLong,airportLat)
             }
            if (distance != null) {
                if(distance < max) {
                    return true
                }
            }
        }
        catch (ex : Exception){
            Log.d(PackageManagerCompat.LOG_TAG, "${ex.message}")
        }
        return false
    }

    private fun calcDistance(lon1: Double, lat1: Double, lon2: Double, lat2: Double): Int {
        val a = Location("")
        a.longitude = lon1
        a.latitude = lat1
        val b = Location("")
        b.longitude = lon2
        b.latitude = lat2
        return a.distanceTo(b).toInt()/1000
    }


}


