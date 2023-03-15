package com.example.templates

import io.ktor.server.html.*
import kotlinx.html.*


class NavbarTemplate: Template<FlowContent> {

    //Zmienne
    val navLinks = listOf("inputy", "tablica")
    override fun FlowContent.apply() {
        div("navbar") {
            ul("nav") {
                navLinks.forEach { link ->
                    li {
                        a(href = "/${link}") {
                            +link
                        }
                    }
                }
            }
        }
    }
}