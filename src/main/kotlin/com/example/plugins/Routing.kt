package com.example.plugins




import com.example.models.InputClass
import com.example.templates.InputyTemplate
import com.example.templates.TablicaTemplate
import com.example.templates.W_NavbarTemplate
import io.ktor.server.application.*
import io.ktor.server.html.*
import io.ktor.server.http.content.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.util.*



//Zmienne
val daneinput= mutableListOf<InputClass>()



fun Application.configureRouting() {


    routing {
        //daneinput.add(InputClass.newEntry("Przykład", "Przykład","Przykład",true,false))

        static("/static") {
            resources("files")
        }

        get("/") {
            call.respondHtmlTemplate(W_NavbarTemplate()){}
        }

        get("/inputy") {
            call.respondHtmlTemplate(InputyTemplate()){}
        }

        get("/tablica") {
            call.respondHtmlTemplate(TablicaTemplate()){}
        }

        post("/inputy") {
            val formParameters = call.receiveParameters()
            val title = formParameters.getOrFail("title")
            val body = formParameters.getOrFail("body")
            val liczba = formParameters.getOrFail("liczba")
            val checkpud = formParameters.getOrFail("checkpud").toBoolean()
            val radpud = formParameters.getOrFail("radpud").toBoolean()

            val newEntry = InputClass.newEntry(title, body,liczba,true,false)
            daneinput.add(newEntry)
            call.respondRedirect("/inputy")
        }
    }
}


