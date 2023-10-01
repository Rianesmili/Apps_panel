package com.example.apppan.feature.inscription

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InscriptionView(viewModel: InscriptionViewModel) {

    val coroutineScope = rememberCoroutineScope()

    val nom by viewModel.name.observeAsState("")
    val email by viewModel.email.observeAsState("")
    val telephone by viewModel.telephone.observeAsState("")

    Column(
        modifier = androidx.compose.ui.Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        TextField(
            value = nom,
            onValueChange = { viewModel.updateNom(it) },
            label = { Text("Nom") },
            modifier = androidx.compose.ui.Modifier.fillMaxWidth()
        )
        TextField(
            value = email,
            onValueChange = { viewModel.updateEmail(it) },
            label = { Text("Email") },
            modifier = androidx.compose.ui.Modifier.fillMaxWidth()
        )
        TextField(
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            value = telephone,
            onValueChange = { viewModel.updateTelephone(it) },
            label = { Text("Téléphone") },
            modifier = androidx.compose.ui.Modifier.fillMaxWidth()
        )

        Spacer(modifier = androidx.compose.ui.Modifier.weight(1f))

        Button(
            onClick = {
                coroutineScope.launch {
                    viewModel.registerUser()
                }
            },
            modifier = androidx.compose.ui.Modifier
                .fillMaxWidth()
        ) {
            Text("S'inscrire")
        }
    }
}
