package com.bme.projlab.domain.modificationoptions

import com.bme.projlab.domain.repository.ModificationRepository

class ModifyPassword (private val repository: ModificationRepository) {
    suspend operator fun invoke(
        password: String
    ) = repository.modifyPassword(password)
}