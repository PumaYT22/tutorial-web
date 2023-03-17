package com.example.plugins




import com.example.api.dao
import com.example.models.Gender
import com.example.templates.InputyTemplate
import com.example.templates.NavbarTemplate
import com.example.templates.W_NavbarTemplate
import io.ktor.http.content.*
import io.ktor.server.application.*
import io.ktor.server.html.*
import io.ktor.server.http.content.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.html.*
import java.io.File


fun Application.configureRouting() {


    routing {
        //daneinput.add(InputClass.newEntry("Przykład", "Przykład","Przykład",true,false))

        static("/static"){
            resources("files")
        }

        get("/") {
            call.respondHtmlTemplate(W_NavbarTemplate()){}
        }

        get("/inputy") {
            call.respondHtmlTemplate(InputyTemplate()){}
        }

//        get("/tablica") {
//            call.respondHtmlTemplate(TablicaTemplate()){}
//        }

        get("/tablica") {
            val daneUsers = dao.allUser()
            var navbar=TemplatePlaceholder<NavbarTemplate>()
            call.respondHtml {
                head {
                    title { +"Tablica" }
                    link(rel = "stylesheet", href = "static/style.css")
                }
                body {
                    insert(NavbarTemplate(), navbar)
                    div("kontener") {
                        table {
                            tr {
                                th { +"Id" }
                                th { +"Imie" }
                                th { +"Nazwisko" }
                                th { +"E-mail" }
                                th { +"Hasło" }
                                th { +"Zdj Profiler" }
                                th { +"Ulica" }
                                th { +"Miasto" }
                                th { +"Kod pocztowy" }
                                th { +"Data urodzenia" }
                                th { +"Płeć" }
                                th { +"Regulamin" }
                                th { +"Skąd wie" }
                            }
                            for (inputss in daneUsers) {
                                tr {
                                    td { +inputss.id }
                                    td { +inputss.firstName }
                                    td { +inputss.lastName }
                                    td { +inputss.email }
                                    td { +inputss.password }
                                    td {
                                        img {
                                            src = "uploads/${inputss.profileImage}"
                                        }
                                    }
                                    td { +inputss.street }
                                    td { +inputss.city }
                                    td { +inputss.postalCode }
                                    td { +inputss.dateOfBirth }
                                    td { +"${inputss.gender}" }
                                    td { +"${inputss.acceptTermsAndConditions}" }
                                    td { +"${inputss.referralSource}" }
                                }
                            }
                        }
                    }
                }
            }
        }

        post("/inputy") {
            val multipart = call.receiveMultipart()

            var firstName: String? = null
            var lastName: String? = null
            var email: String? = null
            var password: String? = null
            var profileImage:String? = null
            var street: String? = null
            var city: String? = null
            var postalCode: String? = null
            var dateOfBirth: String? = null
            var gender: Gender? = null
            var acceptTermsAndConditions: Boolean? = null
            var referralSource: String? = null
            var zapisImage: ByteArray?=null

            multipart.forEachPart { part ->
                when (part) {
                    is PartData.FormItem -> {
                        when (part.name) {
                            "firstName" -> firstName = part.value
                            "lastName" -> lastName = part.value
                            "email" -> email = part.value
                            "password" -> password = part.value
                            "street" -> street = part.value
                            "city" -> city = part.value
                            "postalCode" -> postalCode = part.value
                            "dateOfBirth" -> dateOfBirth = part.value
                            "gender" -> gender = Gender.fromString(part.value)
                            "acceptTermsAndConditions" -> acceptTermsAndConditions = part.value.toBoolean()
                            "referralSource" -> referralSource = part.value
                        }
                    }
                    is PartData.FileItem -> {
                        profileImage = part.originalFileName as String
                        val fileBytes = part.streamProvider().readBytes()
                        zapisImage = part.streamProvider().readBytes()
                        File(/* pathname = */ "uploads/$profileImage").writeBytes(fileBytes)
                    }
                    else -> {

                    }

                }
                part.dispose()
            }


            dao.addNewUser(firstName!!, lastName!!, email!!, password!!,profileImage!!
                , street!!, city!!, postalCode!!, dateOfBirth!!, gender!!,
                acceptTermsAndConditions!!, referralSource!!,zapisImage!!)


            call.respondRedirect("/inputy")
        }

    }
}


