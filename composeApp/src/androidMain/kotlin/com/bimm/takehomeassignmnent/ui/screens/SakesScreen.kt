package com.bimm.takehomeassignmnent.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bimm.takehomeassignmnent.extentions.formattedRating
import com.bimm.takehomeassignmnent.presentation.SakeLocationsSharedViewModel
import com.bimm.takehomeassignmnent.sakes.data.SakeLocation
import org.koin.androidx.compose.koinViewModel

@Composable
fun SakesScreen(
    onGotoSakeDetailsClick: () -> Unit,
    sakeLocationsSharedViewModel: SakeLocationsSharedViewModel = koinViewModel(),
) {
    Column {
        AppBar()
        SakeListView(sakeLocationsSharedViewModel, onGotoSakeDetailsClick)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AppBar() {
    TopAppBar(
        title = { Text(text = "Sake Locations") }
    )
}

@Composable
private fun SakeListView(
    viewModel: SakeLocationsSharedViewModel,
    onGotoSakeDetailsClick: () -> Unit
) {
    Box {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(viewModel.sakeLocationState.value) { sakeLocation ->
                SakeItemView(
                    sakeLocation = sakeLocation,
                    onGotoSakeDetailsClick = onGotoSakeDetailsClick
                )
            }
        }
    }
}

@Composable
private fun SakeItemView(
    sakeLocation: SakeLocation,
    sakeLocationsSharedViewModel: SakeLocationsSharedViewModel = koinViewModel(),
    onGotoSakeDetailsClick: () -> Unit
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        onClick = {
            sakeLocationsSharedViewModel.setSakeSelected(sakeLocation)
            onGotoSakeDetailsClick.invoke()
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = "Name: ${sakeLocation.name}",
                style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 22.sp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Address: ${sakeLocation.address}")
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = sakeLocation.formattedRating(),
                style = TextStyle(color = Color.Gray),
                modifier = Modifier.align(Alignment.End)
            )
            Spacer(modifier = Modifier.height(4.dp))
        }
    }
}