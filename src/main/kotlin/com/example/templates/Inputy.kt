package com.example.templates

import io.ktor.server.html.*
import kotlinx.html.*

class InputyTemplate: Template<HTML> {

    var navbar=TemplatePlaceholder<NavbarTemplate>()
    override fun HTML.apply() {

        head {
            title { +"Inputy" }
            link(rel = "stylesheet", href = "static/style.css")
        }
        body {
            insert(NavbarTemplate(),navbar)
            div("kontener") {
                h1 { +"Formularz" }
                form(
                    action = "/inputy",
                    encType = FormEncType.multipartFormData,
                    method = FormMethod.post
                ) {
                    label { +"Imię:" }
                    input(InputType.text) {
                        name = "firstName"
                        required = true
                    }

                    label { +"Nazwisko:" }
                    input(InputType.text) {
                        name = "lastName"
                        required = true
                    }

                    label { +"E-mail:" }
                    input(InputType.email) {
                        name = "email"
                        required = true
                    }

                    label { +"Hasło:" }
                    input(InputType.password) {
                        name = "password"
                        required = true
                    }

                    label { +"Zdjęcie profilowe:" }
                    input(InputType.file) {
                        name = "profileImage"
                    }

                    label { +"Adres:" }
                    input(InputType.text) {
                        name = "street"
                        placeholder = "Ulica"
                    }
                    input(InputType.text) {
                        name = "city"
                        placeholder = "Miasto"
                    }
                    input(InputType.text) {
                        name = "postalCode"
                        placeholder = "Kod pocztowy"
                    }

                    label { +"Data urodzenia:" }
                    dateInput {
                        name="dateOfBirth"
                    }

                    label { +"Płeć:" }

                    label { +"Mężczyzna" }
                    input(InputType.radio) {
                        name = "gender"
                        value = "MALE"
                        required = true
                    }

                    label { +"Kobieta" }
                    input(InputType.radio) {
                        name = "gender"
                        value = "FEMALE"
                        required = true
                    }


                    label {
                        checkBoxInput() {
                            name = "acceptTermsAndConditions"
                            required = true
                        }
                        +"Akceptuję regulamin"
                    }

                    label { +"Skąd wiesz o naszej stronie?" }
                    select {
                        name = "referralSource"
                        option {
                            value = "wyszukiwarka"
                            +"Wyszukiwarka internetowa"
                        }
                        option {
                            value = "socialMedia"
                            +"Media społecznościowe"
                        }
                        option {
                            value = "znajomy"
                            +"Przyjaciel"
                        }
                        option {
                            value = "inne"
                            +"Inne"
                        }
                    }
                    submitInput { value = "Utwórz" }
                }
            }
        }
    }

}
