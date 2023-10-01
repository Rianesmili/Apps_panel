package com.example.apppan.feature.home

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.apppan.feature.actualites.ActualitesList
import com.example.apppan.feature.actualites.ActualitesViewModel
import com.example.apppan.feature.inscription.InscriptionView
import com.example.apppan.feature.inscription.InscriptionViewModel
import com.example.apppan.feature.info.InfosView
import com.example.apppan.feature.info.InfosViewModel
import androidx.compose.runtime.livedata.observeAsState


@Composable
fun HomeView(
    actualitesViewModel: ActualitesViewModel = hiltViewModel(),
    infosViewModel: InfosViewModel = hiltViewModel(),
    inscriptionViewModel: InscriptionViewModel = hiltViewModel()
) {

    val actualites = actualitesViewModel.actualitesLiveData.value
    val loadingState by actualitesViewModel.loadingState.observeAsState()


    var selectedTabIndex by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // TabBar
        TabRow(
            selectedTabIndex = selectedTabIndex,
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex]),
                    height = 2.dp,
                    color = MaterialTheme.colorScheme.primary
                )
            },
            divider = {},
        ) {
            Tab(
                selected = selectedTabIndex == 0,
                onClick = { selectedTabIndex = 0 },
                text = {
                    Text(text = "Actus")
                }
            )
            Tab(
                selected = selectedTabIndex == 1,
                onClick = { selectedTabIndex = 1 },
                text = {
                    Text(text = "Infos")
                }
            )
            Tab(
                selected = selectedTabIndex == 2,
                onClick = { selectedTabIndex = 2 },
                text = {
                    Text(text = "Inscription")
                }
            )
        }
        when (selectedTabIndex) {
            0 -> {
                actualites?.let {
                    ActualitesList(actualites = it)
                }
                /*
                when (loadingState) {
                    actualitesViewModel.LoadingState.LOADING -> ProgressIndicator()
                    actualitesViewModel.LoadingState.LOADED -> {
                    actualites?.let {
                        ActualitesList(actualites = it)
                    }
                    }
                    else -> {
                        Log.e("LoadingState", "Erreur de chargement de actu")
                    }
                }
                 */
            }

            1 -> {
                InfosView(viewModel = infosViewModel)
            }

            2 -> {
                InscriptionView(viewModel = inscriptionViewModel)
            }
        }

    }
}