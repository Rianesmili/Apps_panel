package com.example.apppan.feature.info

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState

@Composable
fun InfosView(viewModel: InfosViewModel) {

    val webViewUrl by viewModel.webViewUrl.observeAsState()

    webViewUrl?.let {
        WebViewContent(it)
    }
}