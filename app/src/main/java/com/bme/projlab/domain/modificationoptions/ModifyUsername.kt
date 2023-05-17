package com.bme.projlab.domain.modificationoptions

import com.bme.projlab.domain.repository.ModificationRepository

class ModifyUsername (private val repository: ModificationRepository) {
    suspend operator fun invoke(
        username: String
    ) = repository.modifyUsername(username)
}
