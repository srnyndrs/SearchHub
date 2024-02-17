package com.srnyndrs.android.searchhub.ui.composables

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.ImageLoader
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.CachePolicy
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.srnyndrs.android.searchhub.R
import com.srnyndrs.android.searchhub.data.model.Owner

@Composable
fun OwnerCard(
    owner: Owner?
) {
    val context = LocalContext.current
    val intent = remember { Intent(Intent.ACTION_VIEW, Uri.parse(owner?.htmlURL)) }

    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        )
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 12.dp, vertical = 3.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Avatar image
                Box(
                    modifier = Modifier
                        .size(DpSize(32.dp, 32.dp))
                        .clip(CircleShape)
                        .border(1.dp, Color.Black, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    val imageRequest = ImageRequest.Builder(context)
                        .data(owner?.avatarURL)
                        .diskCachePolicy(CachePolicy.ENABLED)
                        .memoryCachePolicy(CachePolicy.ENABLED)
                        .transformations(CircleCropTransformation())
                        .build()
                    val imageLoader =
                        ImageLoader.Builder(context).respectCacheHeaders(false).build()
                    val painter = rememberAsyncImagePainter(
                        imageRequest,
                        imageLoader
                    )
                    if (painter.state !is AsyncImagePainter.State.Success) {
                        CircularProgressIndicator(modifier = Modifier.fillMaxSize().padding(2.dp))
                    }
                    Image(
                        modifier = Modifier
                            .size(DpSize(32.dp, 32.dp))
                            .clip(CircleShape),
                        painter = painter,
                        contentDescription = null
                    )
                }
                Spacer(modifier = Modifier.padding(horizontal = 3.dp))
                // Owner name
                Text(text = owner?.login ?: "", fontSize = 16.sp)
            }
            IconButton(
                onClick = { context.startActivity(intent) }) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.external_link),
                    contentDescription = null
                )
            }
        }
    }
}