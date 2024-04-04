package com.srnyndrs.android.searchhub.presentation.detail_screen.components

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.srnyndrs.android.searchhub.R
import com.srnyndrs.android.searchhub.domain.util.convertToDate
import com.srnyndrs.android.searchhub.domain.util.getDate
import com.srnyndrs.android.searchhub.domain.util.getTime
import com.srnyndrs.android.searchhub.domain.model.Owner
import com.srnyndrs.android.searchhub.domain.model.Repository
import com.srnyndrs.android.searchhub.ui.theme.SearchHubTheme

@Composable
fun RepositoryDetail(
    repository: Repository
) {

    val context = LocalContext.current
    // Intent to navigate repository's page
    val intent = remember { Intent(Intent.ACTION_VIEW, Uri.parse(repository.repositoryPath)) }

    Column(
        modifier = Modifier.padding(horizontal = 12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // Repository card
        Card(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 12.dp, end = 12.dp, bottom = 24.dp, top = 12.dp),
                verticalArrangement = Arrangement.spacedBy(3.dp)
            ) {
                // Name with link
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(3.dp)
                ) {
                    // Repository Name
                    Text(
                        modifier = Modifier.weight(0.9f),
                        text = repository.name ?: "[repository name]",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                    // External link to repository
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
                HorizontalDivider(thickness = 1.dp)
                // Description
                Text(text = repository.description ?: "No description provided")
            }
        }
        // Owner's card
        OwnerCard(owner = repository.owner)
        // Property cards in grid
        Column(
            modifier = Modifier
                .height(IntrinsicSize.Max)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.5f),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // Number of stars
                PropertyCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(0.5f),
                    icon = Icons.Default.Star,
                    label = "Stars",
                    values = listOf(repository.stars.toString() ?: "Unknown")
                )
                // Number of forks
                PropertyCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(0.5f),
                    icon = ImageVector.vectorResource(R.drawable.source_fork),
                    label = "Forks",
                    values = listOf(repository.forks.toString() ?: "Unknown")
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.5f),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // Date of creation
                val createDate = repository.createdAt.convertToDate()
                PropertyCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(0.5f),
                    icon = ImageVector.vectorResource(R.drawable.calendar_blank),
                    label = "Created",
                    values = listOf(
                        createDate.getDate(),
                        createDate.getTime()
                    )
                )
                // Date of last update
                val lastUpdate = repository.updatedAt.convertToDate()
                PropertyCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(0.5f),
                    icon = ImageVector.vectorResource(R.drawable.calendar_refresh),
                    label = "Updated",
                    values = listOf(
                        lastUpdate.getDate(),
                        lastUpdate.getTime()
                    )
                )
            }
        }

    }
}

@PreviewLightDark
@Composable
fun RepositoryDetailPreview() {
    SearchHubTheme {
        Surface {
            RepositoryDetail(repository = Repository(
                name = "k8s",
                owner = Owner(
                    name = "srnyndrs",
                    profilePath = "",
                    avatarPath = ""
                ),
                description = "This repository is about k8s",
                repositoryPath = "",
                stars = 5,
                forks = 5,
                createdAt = "2002",
                updatedAt = ""
            ))
        }
    }
}