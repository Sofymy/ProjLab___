package com.bme.projlab.domain.repository

interface BadgeRepository{
    fun addBadge(name: String){

    }
    suspend fun loadBadges(): ArrayList<String> {
        return ArrayList()
    }
}