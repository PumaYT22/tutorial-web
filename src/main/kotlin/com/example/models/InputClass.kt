package com.example.models

import java.util.concurrent.atomic.AtomicInteger

class InputClass
private constructor(val id: Int, var title: String, var body: String,var liczba: String,var checkpud: Boolean,var radpud: Boolean) {
    companion object {

        private val idCounter = AtomicInteger()

        fun newEntry(title: String, body: String, liczba: String, checkpud: Boolean, radpud: Boolean) = InputClass(idCounter.getAndIncrement(), title, body, liczba,checkpud,radpud)


    }

}


