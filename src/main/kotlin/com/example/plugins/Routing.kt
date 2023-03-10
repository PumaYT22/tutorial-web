package com.example.plugins


import com.example.models.Article
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


val articles = mutableListOf(Article.newEntry(
    "The drive to develop!",
    "...it's what keeps me going."
))





fun Application.configureRouting() {

    routing {
        static("/static") {
            resources("files")
        }
        // ...


        get("/") {
           call.respondRedirect("articles")
        }



        route("articles") {
            get {
//
                    call.respondHtml {
                                head {
                                    title { +"Async World" }
                                    link(rel = "stylesheet", href = "static/style.css")
                                }
                                body {

                                    //articles.add(Article.newEntry("title", "body"))

                                    div("kontener"){
                                        img{src ="static/ktor_logo.png"; alt = "obrazek" }
                                        h1 (classes = "Header"){ +"Kotlin" }
                                        p{
                                            a{ href="/articles/new"; +"Stwórz artykuł"}
                                        }
                                        p{ i{ +"Artykuły" } }
                                        hr{}

                                        for (artykul in articles) {
                                            h3 {+artykul.title }
                                            p{ +artykul.body }
                                            a{ href="/articles/${artykul.id}/edit";+"Edytuj Artykuł" }
                                            hr{}
                                        }
                                    }
                                }
                    }
            }


            get("new") {
                    call.respondHtml {
                        head {
                            title { +"Async World" }
                            link(rel = "stylesheet", href = "static/style.css")
                        }
                            body {
                                div("kontener") {
                                    form(action = "/articles", encType = FormEncType.applicationXWwwFormUrlEncoded, method = FormMethod.post)
                                    {
                                        p { +"Tytuł:";textInput(name = "title") }
                                        p { +"Wiadomość:";textInput(name = "body") }
                                        p { submitInput { value = "Utwórz" } }
                                    }
                                }
                                a{ href="/"+"Wróć do strony głównej" }
                            }
                    }
            }



            post {
                // Save an article
                val formParameters = call.receiveParameters()
                val title = formParameters.getOrFail("title")
                val body = formParameters.getOrFail("body")
                val newEntry = Article.newEntry(title, body)
                articles.add(newEntry)
                call.respondRedirect("/articles/${newEntry.id}")
            }


            get("{id}") {
                // Show an article with a specific id
                val id = call.parameters.getOrFail<Int>("id").toInt()
                val pokaz= (articles.find { it.id == id })
                call.respondHtml {
                    head {
                        title { +"Async World" }
                        link(rel = "stylesheet", href = "static/style.css")
                    }
                    body {
                        h3 {
                            if (pokaz != null) {
                                +"${pokaz.title}"
                            }
                        }
                        p{
                            if (pokaz != null) {
                                +"${pokaz.body}"
                            }
                        }
                        hr{}
                        p{
                            a{href="/articles/${id}/edit";+"Edytuj Artykuł"}
                        }
                        a{ href="/";+"Wróć do strony głównej" }
                    }
                }
            }



            get("{id}/edit") {
                val id = call.parameters.getOrFail<Int>("id").toInt()
                val wartosc= (articles.find { it.id == id })
                val b=0;

                call.respondHtml {
                    body {
                        div {
                            form(action = "/articles/${id}", encType = FormEncType.applicationXWwwFormUrlEncoded, method = FormMethod.post)
                            {
                                p {
                                    +"Tytuł:";textInput(name = "title") { if (wartosc != null) { value = "${wartosc.title}" }; }
                                    p { +"Wiadomość:";textInput(name = "body") { if (wartosc != null) { value = "${wartosc.body}" }; } }
                                    p { submitInput(name="_action") { value = "update" } }
                                }
                            }
                                form(action = "/articles/${id}", encType = FormEncType.applicationXWwwFormUrlEncoded, method = FormMethod.post)
                                {
                                    p { submitInput(name="_action") { value = "delete" } }
                                }
                            }

                            a { href = "/" + "Wróć do strony głównej" }
                        }
                    }
                }



            post("{id}") {
                val id = call.parameters.getOrFail<Int>("id").toInt()
                val formParameters = call.receiveParameters()
                when (formParameters.getOrFail("_action")) {
                    "update" -> {
                        val index = articles.indexOf(articles.find { it.id == id })
                        val title = formParameters.getOrFail("title")
                        val body = formParameters.getOrFail("body")
                        articles[index].title = title
                        articles[index].body = body
                        call.respondRedirect("/articles/$id")
                    }
                    "delete" -> {
                        articles.removeIf { it.id == id }
                        call.respondRedirect("/articles")
                    }
                }
            }
        }
    }
}

