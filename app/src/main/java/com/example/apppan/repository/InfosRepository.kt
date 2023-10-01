package com.example.apppan.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class InfosRepository {


    private val webViewUrl = MutableLiveData<String>()

    fun fetchWebViewUrl() {
        val headers = mapOf(
            "Accept" to "text/html",
            "Accept-Language" to "fr-FR",
            "X-AP-Key" to "uD4Muli8nO6nzkSlsNM3d1Pm",
            "X-AP-DeviceUID" to "Documentation"
        )

        val url = "https://test-pgt-dev.apnl.ws/html"
        val urlWithHeaders = buildString {
            append(url)
            append("?")
            headers.forEach { (key, value) ->
                append("$key=$value&")
            }
        }

        webViewUrl.postValue(urlWithHeaders)
    }

    fun getWebViewUrl(): LiveData<String> {
        return webViewUrl
    }
}