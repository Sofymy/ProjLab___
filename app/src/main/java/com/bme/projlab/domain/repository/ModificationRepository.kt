package com.bme.projlab.domain.repository

import com.bme.projlab.domain.model.response.UsernameResponse

interface ModificationRepository {
    suspend fun modifyUsername(username: String): UsernameResponse
    suspend fun modifyPassword(password: String)
}