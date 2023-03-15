package com.example.templates

import com.example.plugins.daneinput
import io.ktor.server.html.*
import kotlinx.html.*

class TablicaTemplate: Template<HTML> {

    var navbar=TemplatePlaceholder<NavbarTemplate>()
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
                        th{+"Tekst"}
                        th{+"Haslo"}
                        th{+"Numeryczny"}
                        th{+"Check"}
                        th{+"Radio"}
                    }
                    for (inputss in daneinput) {
                        tr{
                            td{+inputss.title}
                            td{+inputss.body}
                            td{+"${inputss.liczba}"}
                            td{+"${inputss.checkpud}"}
                            td{+"${inputss.radpud}"}
                        }
                    }
                }
            }
        }
    }

}
