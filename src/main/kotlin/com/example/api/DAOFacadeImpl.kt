package com.example.api

import com.example.api.DatabaseFactory.dbQuery
import com.example.models.Gender
import com.example.models.UserData
import com.example.models.UserDatas
import kotlinx.coroutines.runBlocking
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll

class DAOFacadeImpl : DAOFacade {
    private fun resultRowToUser(row: ResultRow) = UserData(
        id = row[UserDatas.id],
        firstName = row[UserDatas.firstName],
        lastName = row[UserDatas.lastName],
        email = row[UserDatas.email],
        password = row[UserDatas.password],
        profileImage = row[UserDatas.profileImage],
        street = row[UserDatas.street],
        city = row[UserDatas.city],
        postalCode = row[UserDatas.postalCode],
        dateOfBirth = row[UserDatas.dateOfBirth],
        gender = row[UserDatas.gender],
        acceptTermsAndConditions = row[UserDatas.acceptTermsAndConditions],
        referralSource = row[UserDatas.referralSource],
        zapisImage = row[UserDatas.zapisImage]
    )

    override suspend fun allUser(): List<UserData> = dbQuery {
        UserDatas.selectAll().map(::resultRowToUser)
    }

    override suspend fun user(id: Int): UserData?  = dbQuery  {
        UserDatas
            .select { UserDatas.id eq id }
            .map(::resultRowToUser)
            .singleOrNull()
    }

    override suspend fun addNewUser(
        firstName: String,
        lastName: String,
        email: String,
        password: String,
        profileImage: String,
        street: String,
        city: String,
        postalCode: String,
        dateOfBirth: String,
        gender: Gender,
        acceptTermsAndConditions: Boolean,
        referralSource: String,
        zapisImage: ByteArray
    ): UserData? = dbQuery {
        val insertStatement = UserDatas.insert {
            it[UserDatas.firstName] = firstName
            it[UserDatas.lastName] = lastName
            it[UserDatas.email] = email
            it[UserDatas.password] = password
            it[UserDatas.profileImage] = profileImage
            it[UserDatas.street] = street
            it[UserDatas.city] = city
            it[UserDatas.postalCode] = postalCode
            it[UserDatas.dateOfBirth] = dateOfBirth
            it[UserDatas.gender] = gender
            it[UserDatas.acceptTermsAndConditions] = acceptTermsAndConditions
            it[UserDatas.referralSource] = referralSource
            it[UserDatas.zapisImage] = zapisImage
        }
        insertStatement.resultedValues?.singleOrNull()?.let(::resultRowToUser)
    }


}

val dao: DAOFacade = DAOFacadeImpl().apply {
    runBlocking {
        if(allUser().isEmpty()) {
            addNewUser("Jacek","Placek","pumaxyt@gmail.com",
                "123","zdj.png","ulica","kolbuszowa","002"
            ,"01-01-2020",Gender.MALE,true,"ok",ByteArray(5))
        }
    }
}