package com.example.templates

import io.ktor.server.html.*
import kotlinx.html.*

class W_NavbarTemplate: Template<HTML> {

    //Zmienne
    var navbar=TemplatePlaceholder<NavbarTemplate>()
    override fun HTML.apply() {
        head {
            title { +"Inputy" }
            link(rel = "stylesheet", href = "static/style.css")
        }
        body {
            insert(NavbarTemplate(),navbar)
        }
    }
}