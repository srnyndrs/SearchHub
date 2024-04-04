package com.srnyndrs.android.searchhub.presentation.detail_screen.components

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
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
import androidx.compose.ui.tooling.preview.PreviewLightDark
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
import com.srnyndrs.android.searchhub.domain.model.Owner
import com.srnyndrs.android.searchhub.ui.theme.SearchHubTheme

@Composable
fun OwnerCard(
    owner: Owner?
) {
    val context = LocalContext.current

    // Intent to navigate to the owner's github page
    val intent = remember { Intent(Intent.ACTION_VIEW, Uri.parse(owner?.profilePath)) }

    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 3.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier.weight(0.9f),
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
                    // Coil image request with cache
                    val imageRequest = ImageRequest.Builder(context)
                        .data(owner?.avatarPath)
                        .diskCachePolicy(CachePolicy.ENABLED)
                        .memoryCachePolicy(CachePolicy.ENABLED)
                        .transformations(CircleCropTransformation())
                        .build()
                    val imageLoader = ImageLoader.Builder(context)
                            .respectCacheHeaders(false)
                            .build()
                    val painter = rememberAsyncImagePainter(imageRequest, imageLoader)
                    // Loading animation until picture loaded
                    if (painter.state !is AsyncImagePainter.State.Success) {
                        CircularProgressIndicator(modifier = Modifier
                            .fillMaxSize()
                            .padding(2.dp)
                        )
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
                // Owner's name
                Text(text = owner?.name ?: "[username]", fontSize = 16.sp)
            }
            // External link to owner's github page
            IconButton(
                modifier = Modifier.weight(0.1f),
                onClick = { context.startActivity(intent) }
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.external_link),
                    contentDescription = null
                )
            }
        }
    }
}

@PreviewLightDark
@Composable
fun OwnerCardPreview() {
    SearchHubTheme {
        Surface {
            OwnerCard(owner = Owner(
                name = "srnyndrs",
                profilePath = "",
                avatarPath = ""
            ))
        }
    }
}