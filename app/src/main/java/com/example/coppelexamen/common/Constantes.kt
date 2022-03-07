package com.example.coppelexamen.common

import com.example.coppelexamen.data.api.ApiService
import java.math.BigInteger
import java.security.MessageDigest
import java.sql.Timestamp

class Constantes {
    companion object{
        const val HEROAPI_BASE_URL="https://gateway.marvel.com"
        const val API_KEY= "c555a69d442c71cb9aff6da17a7ea225"
        const val API_KEY_PRIV="2edc9003cf00c747d911edcb6dad76c896d4a06c"
        const val limit = "20"
        val timeStamp = Timestamp(System.currentTimeMillis()).time.toString()
        //val timeStamp = "1"
        const val API_KEY_PARAM="api_key"
        const val URL_PARAM_LANGUAGE="es-ES"

        fun hash():String{
            val input= "$timeStamp$API_KEY_PRIV$API_KEY"
            val md = MessageDigest.getInstance("MD5")
            return BigInteger(1,md.digest(input.toByteArray())).toString(16).padStart(32,'0')
        }

    }
}