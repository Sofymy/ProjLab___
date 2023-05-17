package com.bme.projlab.data.datasource

//Todo: delete this file
interface DataSource<out T, in I, out O> {
    fun load(): T
    fun get(id: I): O
}