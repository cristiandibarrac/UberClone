package com.cristian.uberclonecliente.providers

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ClientProviders {
    val db = Firebase.firestore.collection("Clients")

    fun create(){}
}