package com.example.api

import com.example.models.Gender
import com.example.models.UserData

interface DAOFacade {
    suspend fun allUser(): List<UserData>
    suspend fun user(id: Int): UserData?
    suspend fun addNewUser(firstName: String, lastName: String, email: String, password: String, profileImage: String, street: String,
                           city: String, postalCode: String, dateOfBirth: String, gender: Gender, acceptTermsAndConditions: Boolean,
                           referralSource: String, zapisImage: ByteArray): UserData?

}