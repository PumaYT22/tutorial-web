package com.example.templates


import com.example.models.UserDatas
import io.ktor.server.html.*
import kotlinx.html.*
import java.io.File

class TablicaTemplate: Template<HTML> {

    var navbar=TemplatePlaceholder<NavbarTemplate>()
    val uploadsDir = File("uploads")


    override fun HTML.apply() {

        head {
            title { +"Tablica" }
            link(rel = "stylesheet", href = "static/style.css")
        }
        body {
            insert(NavbarTemplate(),navbar)
            div("kontener") {
                table{
                    tr{
                        th{+"Id"}
                        th{+"Imie"}
                        th{+"Nazwisko"}
                        th{+"E-mail"}
                        th{+"Hasło"}
                        th{+"Zdj Profiler"}
                        th{+"Ulica"}
                        th{+"Miasto"}
                        th{+"Kod pocztowy"}
                        th{+"Data urodzenia"}
                        th{+"Płeć"}
                        th{+"Regulamin"}
                        th{+"Skąd wie"}
                    }
                    for (inputss in arrayListOf<UserDatas>()) {
                        tr{
                            td{inputss.id}
                            td{inputss.firstName}
                            td{inputss.lastName}
                            td{inputss.email}
                            td{inputss.password}
                            td{ img {
                                src = "${uploadsDir}/${inputss.profileImage}"
                            }
                            }
                            td{inputss.street}
                            td{inputss.city }
                            td{inputss.postalCode }
                            td{inputss.dateOfBirth }
                            td{+"${inputss.gender}"}
                            td{+"${inputss.acceptTermsAndConditions}"}
                            td{+"${inputss.referralSource}"}
                        }
                    }
                }
            }
        }
    }

}
