package com.challenge.domain.usecase

import com.challenge.data.repository.PropertyRepository
import javax.inject.Inject

class GetPropertiesUseCase @Inject constructor(
    private val propertyRepository: PropertyRepository
) {
    suspend operator fun invoke() = propertyRepository.getProperties()
}