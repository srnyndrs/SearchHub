package com.srnyndrs.android.searchhub.ui.composables

import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.CachePolicy
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.google.gson.Gson
import com.srnyndrs.android.searchhub.R
import com.srnyndrs.android.searchhub.data.model.Owner
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OwnerCard(
    owner: Owner?
) {
    val context = LocalContext.current
    val intent = remember { Intent(Intent.ACTION_VIEW, Uri.parse(owner?.htmlURL)) }

    Card(
        modifier = Modifier
            .padding(top = 12.dp, bottom = 12.dp),
        onClick = {
            context.startActivity(intent)
        },
        colors = CardDefaults.cardColors(
            containerColor = Color.Gray,
        )
    ) {
        Row(
            modifier = Modifier.padding(6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Avatar image
            Box(
                modifier = Modifier
                    .size(DpSize(64.dp, 64.dp))
                    .clip(CircleShape)
                    .border(3.dp, Color.Black, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                val imageRequest = ImageRequest.Builder(context)
                    .data(owner?.avatarURL) // Work away to get profile picture
                    .diskCachePolicy(CachePolicy.ENABLED)
                    .memoryCachePolicy(CachePolicy.ENABLED)
                    .transformations(CircleCropTransformation())
                    .build()
                val imageLoader =
                    ImageLoader.Builder(context).respectCacheHeaders(false).build()
                val painter = rememberAsyncImagePainter(imageRequest, imageLoader)
                Image(
                    modifier = Modifier
                        .size(DpSize(64.dp, 64.dp))
                        .clip(CircleShape),
                    painter = painter,
                    contentDescription = null
                )
            }
            Spacer(modifier = Modifier.padding(6.dp))
            Column(
                modifier = Modifier.padding(3.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Owner name
                Text(text = owner?.login ?: "", fontSize = 20.sp)
            }
        }
    }
}