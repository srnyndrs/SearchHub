package com.srnyndrs.android.searchhub.ui.composables

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.srnyndrs.android.searchhub.data.model.Repository
import java.text.SimpleDateFormat

@Composable
fun RepositoryDetail(
    repository: Repository
) {
    val context = LocalContext.current
    val intent = remember { Intent(Intent.ACTION_VIEW, Uri.parse(repository.htmlURL)) }
    Column {
        // Owner's card
        OwnerCard(owner = repository.owner)
        // Repository name and Link
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.height(24.dp)
        ) {
            Text(text = repository.name ?: "Unknown", fontSize = 20.sp)
            IconButton(onClick = { context.startActivity(intent) }) {
                Icon(
                    imageVector = Icons.Default.ExitToApp, // TODO
                    contentDescription = null
                )
            }
        }
        Spacer(modifier = Modifier.padding(vertical = 6.dp))
        // Description
        Text(text = repository.description ?: "Unknown")
        // Number of stars
        Spacer(modifier = Modifier.padding(vertical = 12.dp))
        IconLabel(
            icon = Icons.Default.Star,
            label = "Stars",
            value = (repository.stargazersCount ?: 0).toString()
        )
        //Text(text = (repository.stargazersCount ?: "Unknown").toString())
        // Number of forks
        IconLabel(
            icon = Icons.Default.Share, // TODO
            label = "Forks",
            value = (repository.forksCount ?: 0).toString()
        )
        // Date of creation
        IconLabel(
            icon = Icons.Default.DateRange, // TODO
            label = "Created",
            value = repository.createdAt ?: "--.--"
        )
        // Date of last update
        IconLabel(
            icon = Icons.Default.Refresh, // TODO
            label = "Updated",
            value =  repository.updatedAt ?: "--.--"
        )
    }
}