package com.cristian.uberclonecliente.models

import com.beust.klaxon.*

private val klaxon = Klaxon()

data class Client (
    val nombre: String,
    val apellido: String,
    val telefono: String,
    val email: String,
    val image: String,
    val id: String
) {
    public fun toJson() = klaxon.toJsonString(this)

    companion object {
        public fun fromJson(json: String) = klaxon.parse<Client>(json)
    }
}