package com.srnyndrs.android.searchhub.ui.composables

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.srnyndrs.android.searchhub.R
import com.srnyndrs.android.searchhub.data.model.Repository
import com.srnyndrs.android.searchhub.data.shared.MockData
import com.srnyndrs.android.searchhub.data.util.convertToDate
import com.srnyndrs.android.searchhub.data.util.getDate
import com.srnyndrs.android.searchhub.data.util.getTime

@Composable
fun RepositoryDetail(
    repository: Repository
) {
    val context = LocalContext.current

    // Intent to navigate repository's page
    val intent = remember { Intent(Intent.ACTION_VIEW, Uri.parse(repository.htmlURL)) }

    Column(
        modifier = Modifier.padding(horizontal = 20.dp)
    ) {
        // Repository card
        Card(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 12.dp, end = 12.dp, bottom = 24.dp, top = 12.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Repository Name
                    Text(
                        modifier = Modifier.weight(0.9f),
                        text = repository.name ?: "[repository name]",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                    // External link to repository
                    Spacer(modifier = Modifier.padding(horizontal = 3.dp))
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
                Spacer(modifier = Modifier.padding(vertical = 3.dp))
                Divider(thickness = 1.dp)
                Spacer(modifier = Modifier.padding(vertical = 3.dp))
                // Description
                Text(text = repository.description ?: "No description provided")
            }
        }
        Spacer(modifier = Modifier.padding(vertical = 12.dp))
        // Owner's card
        OwnerCard(owner = repository.owner)
        Spacer(modifier = Modifier.padding(vertical = 12.dp))
        // Property cards in grid
        Column(
            modifier = Modifier
                .height(IntrinsicSize.Max)
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Number of stars
                PropertyCard(
                    icon = Icons.Default.Star,
                    label = "Stars",
                    values = listOf((repository.stargazersCount ?: 0).toString())
                )
                // Number of forks
                PropertyCard(
                    icon = ImageVector.vectorResource(R.drawable.source_fork),
                    label = "Forks",
                    values = listOf((repository.forksCount ?: 0).toString())
                )
            }
            Spacer(modifier = Modifier.padding(vertical = 6.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Date of creation
                val createDate = repository.createdAt?.convertToDate()
                PropertyCard(
                    icon = ImageVector.vectorResource(R.drawable.calendar_blank),
                    label = "Created",
                    values = listOf(
                        createDate.getDate(),
                        createDate.getTime()
                    )
                )
                // Date of last update
                val lastUpdate = repository.updatedAt?.convertToDate()
                PropertyCard(
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

@Preview
@Composable
fun RepositoryDetailPreview() {
    RepositoryDetail(repository = MockData.repository)
}