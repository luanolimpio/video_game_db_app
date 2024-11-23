package com.example.videogamedbapp.core.extensions

import java.text.NumberFormat

val Int.toFormat: String
    get() {
        return NumberFormat.getInstance().format(this)
    }