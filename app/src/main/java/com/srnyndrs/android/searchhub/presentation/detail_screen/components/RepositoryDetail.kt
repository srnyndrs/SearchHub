package com.srnyndrs.android.searchhub.presentation.detail_screen.components

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
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
import com.srnyndrs.android.searchhub.domain.model.Repository
import com.srnyndrs.android.searchhub.domain.sample.SampleRepositoryProvider
import com.srnyndrs.android.searchhub.domain.util.convertToDate
import com.srnyndrs.android.searchhub.domain.util.getDateTime
import com.srnyndrs.android.searchhub.presentation.components.IconLabel
import com.srnyndrs.android.searchhub.ui.theme.SearchHubTheme

@Composable
fun RepositoryDetail(
    repository: Repository
) {

    val context = LocalContext.current

    // Intent to navigate repository's github page
    val repositoryIntent = remember { Intent(Intent.ACTION_VIEW, Uri.parse(repository.repositoryPath)) }

    // Intent to navigate repository's owner github page
    val userIntent = remember { Intent(Intent.ACTION_VIEW, Uri.parse(repository.owner.profilePath)) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(3.dp)
    ) {
        // Top repository info
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(3.dp),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.spacedBy(3.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Row(
                    modifier = Modifier.padding(start = 3.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .clip(RoundedCornerShape(5.dp))
                            .clickable {
                                context.startActivity(userIntent)
                            },
                        verticalAlignment = Alignment.Top,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        // Avatar image
                        Box(
                            modifier = Modifier
                                .size(DpSize(26.dp, 26.dp))
                                .clip(CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            // Coil image request with cache
                            val imageRequest = ImageRequest.Builder(context)
                                .data(repository.owner.avatarPath)
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
                                    .size(DpSize(26.dp, 26.dp))
                                    .clip(CircleShape),
                                painter = painter,
                                contentDescription = null
                            )
                        }
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(3.dp)
                        ) {
                            // Owner's name
                            Text(
                                text = repository.owner.name,
                                fontSize = 18.sp
                            )
                        }
                    }
                    // Slash decoration
                    Text(text = "/", fontSize = 18.sp, fontWeight = FontWeight.Normal)
                }
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(0.dp)
                ) {
                    // Repository title
                    Text(
                        modifier = Modifier
                            .padding(start = 3.dp)
                            .clip(RoundedCornerShape(5.dp))
                            .clickable {
                                context.startActivity(repositoryIntent)
                            },
                        text = repository.name,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        // Number of stars
                        IconLabel(
                            icon = Icons.Default.Star,
                            text = "stars",
                            value = repository.stars.toString()
                        )
                        // Number of forks
                        IconLabel(
                            icon = ImageVector.vectorResource(R.drawable.source_fork),
                            text = "forks",
                            value = repository.forks.toString()
                        )
                    }
                }

            }
        }
        HorizontalDivider(
            thickness = 1.dp,
            color = MaterialTheme.colorScheme.onSurface
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(3.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Create and update dates
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .requiredHeight(96.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconSection(
                    modifier = Modifier.weight(0.5f),
                    icon = ImageVector.vectorResource(R.drawable.calendar_blank),
                    label = "Created",
                    value = repository.createdAt.convertToDate().getDateTime()
                )
                VerticalDivider()
                IconSection(
                    modifier = Modifier.weight(0.5f),
                    icon = ImageVector.vectorResource(id = R.drawable.calendar_refresh),
                    label = "Updated",
                    value = repository.updatedAt.convertToDate().getDateTime()
                )
            }
            // Description
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    Icon(
                        modifier = Modifier.size(18.dp),
                        imageVector = Icons.Default.Info,
                        contentDescription = "Info icon"
                    )
                    Text(
                        text = "Description",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
                Text(
                    modifier = Modifier.padding(horizontal = 2.dp),
                    text = repository.description,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Justify,
                    fontWeight = FontWeight.W300
                )
            }
        }
    }
}

@PreviewLightDark
@Composable
fun RepositoryDetailPreview(
    @PreviewParameter(SampleRepositoryProvider::class) repositories: List<Repository>
) {
    SearchHubTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(3.dp)
        ) {
            RepositoryDetail(
                repository = repositories[0]
            )
        }
    }
}