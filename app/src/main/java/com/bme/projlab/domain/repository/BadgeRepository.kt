package com.bme.projlab.domain.repository

import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await

interface BadgeRepository{
    fun addBadge(name: String){

    }
    suspend fun loadBadges(): ArrayList<String>{
        val badges = ArrayList<String>()
        return badges
    }
}