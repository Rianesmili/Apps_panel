package com.example.apppan.feature.actualites

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.example.apppan.data.Actu

@Composable
fun ActualitesList(actualites: List<Actu>) {
    LazyColumn {
        items(actualites) { actu ->
            ActuItem(actu = actu)
        }
    }
}
