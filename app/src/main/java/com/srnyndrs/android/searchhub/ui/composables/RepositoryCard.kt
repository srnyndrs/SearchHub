package com.srnyndrs.android.searchhub.ui.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.srnyndrs.android.searchhub.data.model.Repository
import com.srnyndrs.android.searchhub.data.util.formatDate
import com.srnyndrs.android.searchhub.ui.screens.detail.DetailScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RepositoryCard(
    repository: Repository, onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(vertical = 6.dp),
        onClick = onClick,
        /*colors = CardDefaults.cardColors(
            containerColor = Color.DarkGray,
        )*/
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(12.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            // Name
            Text(text = repository.name ?: "Unknown", fontWeight = FontWeight.Bold)
            // Description
            val description = repository.description ?: "No description provided"
            Text(
                text = if(description.length > 40) { description.chunked(40)[0].plus(" ...")} else { description }
            )
            // Stars
            IconLabel(
                icon = Icons.Default.Star,
                label = "Stars",
                value = (repository.stargazersCount ?: 0).toString()
            )
            Spacer(modifier = Modifier.padding(horizontal = 12.dp))
            // Last update
            IconLabel(
                icon = Icons.Default.Refresh, // TODO
                label = "Updated",
                value =  repository.updatedAt?.formatDate()?.first ?: "--.--"
            )
        }
    }
}