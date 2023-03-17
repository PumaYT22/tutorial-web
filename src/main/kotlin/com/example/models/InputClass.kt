package com.example.models

import org.jetbrains.exposed.sql.Table





data class UserData(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String,
    val profileImage: String?,
    val street: String,
    val city: String,
    val postalCode: String,
    val dateOfBirth: String,
    val gender: Gender,
    val acceptTermsAndConditions: Boolean?,
    val referralSource: String?,
    val zapisImage: ByteArray?,

    )
//{
//    companion object {
//        private val idCounter = AtomicInteger()
//        fun newEntry(firstName: String,lastName: String,email: String,password: String,profileImage: String,street: String,
//                     city: String,postalCode: String,dateOfBirth: String,gender: Gender,acceptTermsAndConditions: Boolean,
//                     referralSource: String,zapisImage: ByteArray) = UserData(idCounter.getAndIncrement(),
//            firstName, lastName, email, password, profileImage, street, city, postalCode, dateOfBirth, gender, acceptTermsAndConditions, referralSource, zapisImage)
//        }
//    }

enum class Gender {
    MALE,
    FEMALE;

    companion object {
        fun fromString(value: String?): Gender? {
            return when (value?.toLowerCase()) {
                "male" -> MALE
                "female" -> FEMALE
                else -> null
            }
        }
    }
}


object UserDatas : Table("user_data") {
    val id = integer("id").autoIncrement()
    val firstName = varchar("first_name", 255)
    val lastName = varchar("last_name", 255)
    val email = varchar("email", 255)
    val password = varchar("password", 255)
    val profileImage = varchar("profile_image", 255).nullable()
    val street = varchar("street", 255)
    val city = varchar("city", 255)
    val postalCode = varchar("postal_code", 255)
    val dateOfBirth = varchar("date_of_birth", 255)
    val gender = enumerationByName("gender", 255, Gender::class)
    val acceptTermsAndConditions = bool("accept_terms_and_conditions")
    val referralSource = varchar("referral_source", 255).nullable()
    val zapisImage = binary("zapis_image").nullable()

    override val primaryKey = PrimaryKey(id)
}
