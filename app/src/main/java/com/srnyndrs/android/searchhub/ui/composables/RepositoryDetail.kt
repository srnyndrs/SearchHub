package com.srnyndrs.android.searchhub.ui.composables

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.srnyndrs.android.searchhub.data.model.Repository
import com.srnyndrs.android.searchhub.data.util.formatDate

@Composable
fun RepositoryDetail(
    repository: Repository
) {
    val context = LocalContext.current
    val intent = remember { Intent(Intent.ACTION_VIEW, Uri.parse(repository.htmlURL)) }
    Column {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            // Repository name and Link
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    // Repository Name
                    Text(text = repository.name ?: "Unknown", fontSize = 20.sp)
                    //
                    IconButton(onClick = { context.startActivity(intent) }) {
                        Icon(
                            imageVector = Icons.Default.ExitToApp, // TODO
                            contentDescription = null
                        )
                    }
                }
                Spacer(modifier = Modifier.padding(vertical = 6.dp))
                // Description
                Text(text = repository.description ?: "No description provided")
                // Owner's card
                OwnerCard(owner = repository.owner)
            }
        }
        Spacer(modifier = Modifier.padding(vertical = 12.dp))
        // Fields
        Row(
            modifier = Modifier
                .height(IntrinsicSize.Max)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Column(modifier = Modifier.fillMaxHeight()) {
                // Number of stars
                PropertyCard(
                    icon = Icons.Default.Star,
                    label = "Stars",
                    value = (repository.stargazersCount ?: 0).toString()
                )
                // Number of forks
                PropertyCard(
                    icon = Icons.Default.Share, // TODO
                    label = "Forks",
                    value = (repository.forksCount ?: 0).toString()
                )
            }
            Column(modifier = Modifier.fillMaxHeight()) {
                // Date of creation
                PropertyCard(
                    icon = Icons.Default.DateRange, // TODO
                    label = "Created",
                    value = repository.createdAt?.formatDate()?.first ?: "yyyy-mm-dd",
                    // TODO
                    //secondValue = repository.createdAt?.formatDate()?.second ?: "HH:mm"
                )
                // Date of last update
                PropertyCard(
                    icon = Icons.Default.Refresh, // TODO
                    label = "Updated",
                    value = repository.updatedAt?.formatDate()?.first ?: "yyyy-mm-dd",
                    // TODO
                    //secondValue = repository.updatedAt?.formatDate()?.second ?: "HH:mm"
                )
            }
        }

    }
}