package com.alif.daftarliga.model.webservice

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import java.net.URL

class ApiRepository {
    fun doRequestCoroutines(url: String): Deferred<String> = GlobalScope.async {
        URL(url).readText()
    }

    fun doRequest(url: String): String {
        return URL(url).readText()
    }
}