package com.example.plugins




import com.example.models.InputClass
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.html.*
import io.ktor.server.http.content.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.util.*
import kotlinx.css.CssBuilder
import kotlinx.html.*



suspend inline fun ApplicationCall.respondCss(builder: CssBuilder.() -> Unit) {
    this.respondText(CssBuilder().apply(builder).toString(), ContentType.Text.CSS)
}




val daneinput = mutableListOf(InputClass.newEntry(
    "Przykład!",
    "ssdasdasdass",
    "2",
        true,
    true
))








fun Application.configureRouting() {


    routing {
        static("/static") {
            resources("files")
        }
        // ...


        get("/") {
            call.respondRedirect("inputy")
        }



        route("inputy") {

            //Zmienne
            val navLinks = listOf("inputy", "inputy/tablica")
            daneinput.add(InputClass.newEntry("Przykład", "Przykład","Przykład",true,true))

            get {
                call.respondHtml {
                    head {
                        title { +"Inputy" }
                        link(rel = "stylesheet", href = "static/style.css")
                    }
                    body {
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

                        div("kontener") {
                            h1 { +"Formularz" }
                            form(action = "/inputy", encType = FormEncType.applicationXWwwFormUrlEncoded, method = FormMethod.post){
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

            get("tablica") {
                call.respondHtml {
                    head {
                        title { +"Tablica" }
                        style {
                            +"""
                                    body {
                                        font-family: Arial, sans-serif;
                                        margin: 0;
                                        padding: 0;
                                    }
                                    .navbar {
                                        background-color: #222;
                                        color: #fff;
                                        display: flex;
                                        justify-content: space-between;
                                        align-items: center;
                                        padding: 10px 20px;
                                    }
                                    
                                    
                                    .navbar .nav {
                                        display: flex;
                                        list-style: none;
                                        margin: 0 auto;
                                        padding: 0;
                                    }
                                    
                                    .navbar .nav li {
                                        margin: 0 10px;
                                    }
                                    
                                    .navbar .nav li a {
                                        color: #fff;
                                        text-decoration: none;
                                        font-size: 1.1em;
                                        font-weight: bold;
                                        letter-spacing: 1px;
                                        padding: 10px;
                                        border-radius: 3px;
                                        transition: all 0.3s ease;
                                    }
                                    
                                    .navbar .nav li a:hover {
                                        background-color: #fff;
                                        color: #222;
                                    }

                                    table {
                                        margin-top: 2rem;
                                        width: 80%;
                                        border-collapse: collapse;
                                    }
                                    
                                    th, td {
                                        padding: 0.5rem;
                                        border: 1px solid #ccc;
                                    }
                                    
                                    th {
                                        background-color: #f2f2f2;
                                    }
                                """
                        }
                    }
                    body {
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



            post {
                val formParameters = call.receiveParameters()
                val title = formParameters.getOrFail("title")
                val body = formParameters.getOrFail("body")
                val liczba = formParameters.getOrFail("liczba")
                val checkpud = formParameters.getOrFail("checkpud")
                val radpud = formParameters.getOrFail("radpud")

                val newEntry = InputClass.newEntry(title, body,liczba,true,false)
                daneinput.add(newEntry)
                call.respondRedirect("/inputy")

            }



        }
    }
}


