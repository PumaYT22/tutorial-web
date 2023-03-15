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
                    encType = FormEncType.applicationXWwwFormUrlEncoded,
                    method = FormMethod.post
                ) {
                    label {
                        htmlFor = "title"
                        +"Pole tekstowe:"
                    }
                    input {
                        type = InputType.text
                        id = "title"
                        name = "title"
                    }
                    br
                    label {
                        htmlFor = "body"
                        +"Hasło:"
                    }
                    input {
                        type = InputType.password
                        id = "body"
                        name = "body"
                    }
                    br
                    label {
                        htmlFor = "liczba"
                        +"Liczba:"
                    }
                    input {
                        type = InputType.number
                        id = "liczba"
                        name = "liczba"
                        min = "0"
                        max = "100"
                    }
                    br
                    label {
                        htmlFor = "checkpud"
                        +"Checkbox:"
                    }
                    input {
                        type = InputType.checkBox
                        id = "checkpud"
                        name = "checkpud"
                    }
                    br
                    label {
                        htmlFor = "radpud"
                        +"Radio:"
                    }
                    input {
                        type = InputType.radio
                        id = "radpud"
                        name = "radpud"
                    }
                    submitInput { value = "Utwórz" }
                }
            }
        }
    }

}
