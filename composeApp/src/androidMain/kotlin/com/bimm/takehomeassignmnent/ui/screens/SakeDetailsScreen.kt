package com.bimm.takehomeassignmnent.ui.screens

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import com.bimm.takehomeassignmnent.R
import com.bimm.takehomeassignmnent.extentions.formattedRating
import com.bimm.takehomeassignmnent.extentions.isValidImageUrl
import com.bimm.takehomeassignmnent.presentation.SakeLocationsSharedViewModel
import com.bimm.takehomeassignmnent.sakes.data.SakeLocation
import org.koin.androidx.compose.koinViewModel

@Composable
fun SakeDetailsScreen(
    onUpButtonClick: () -> Unit
) {

    Column {
        AppBar(onUpButtonClick)
        SakeLocationItemView()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AppBar(
    onUpButtonClick: () -> Unit,
) {
    TopAppBar(
        title = { Text(text = "Details") },
        navigationIcon = {
            IconButton(onClick = onUpButtonClick) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Detail Button",
                )
            }
        }
    )
}

@Composable
private fun SakeLocationItemView(
    sakeLocationsSharedViewModel: SakeLocationsSharedViewModel = koinViewModel()
) {
    val sakeSelected = sakeLocationsSharedViewModel.sakeSelected
    val context = LocalContext.current
    val uriHandler = LocalUriHandler.current

    Box {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = sakeSelected.name,
                style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 22.sp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(8.dp),
                contentAlignment = Alignment.Center
            ) {
                ImageItem(sakeSelected)
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = sakeSelected.description)
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = sakeSelected.formattedRating(),
                style = TextStyle(color = Color.Gray),
                modifier = Modifier.align(Alignment.End)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            color = Color.Green,
                            textDecoration = TextDecoration.Underline
                        )
                    ) {
                        append(sakeSelected.address)
                    }
                },
                modifier = Modifier.clickable {
                    openGoogleMapsAtCoordinates(
                        context,
                        sakeSelected.coordinates[0],
                        sakeSelected.coordinates[1]
                    )
                }
            )
            Spacer(modifier = Modifier.height(4.dp))
            Button(
                modifier = Modifier.align(Alignment.End),
                onClick = { uriHandler.openUri(sakeSelected.website) }
            ) {
                Text(
                    text = "Visit Website"
                )
            }
            Spacer(modifier = Modifier.height(4.dp))
        }
    }
}

@Composable
fun ImageItem(sakeSelected: SakeLocation) {
    val painter = rememberAsyncImagePainter(model = sakeSelected.picture)
    val state = painter.state

    if (sakeSelected.isValidImageUrl()) {
        Image(
            painter = painter,
            contentDescription = "Sake Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(12.dp))
        )
        if (state is AsyncImagePainter.State.Loading) {
            CircularProgressIndicator()
        }
        if (state is AsyncImagePainter.State.Error || state is AsyncImagePainter.State.Empty) {
            ErrorImage()
        }
    } else {
        ErrorImage()
    }
}

@Composable
fun ErrorImage() {
    Image(
        painter = painterResource(id = R.drawable.ic_launcher_background),
        contentDescription = "Error Image",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .fillMaxSize()
            .clip(RoundedCornerShape(12.dp))
    )
}

fun openGoogleMapsAtCoordinates(context: Context, lat: Double, lon: Double) {
    val uri = "geo:$lat,$lon?q=$lat,$lon(${Uri.encode("Place Name")})".toUri()
    val intent = Intent(Intent.ACTION_VIEW, uri)
    intent.setPackage("com.google.android.apps.maps")
    context.startActivity(intent)
}
