package com.bme.projlab.data.datasource

interface DataSource<out T, in I, out O> {
    fun load(): T
    fun get(id: I): O
}