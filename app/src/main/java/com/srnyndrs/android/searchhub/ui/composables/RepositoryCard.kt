package com.srnyndrs.android.searchhub.ui.composables

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.srnyndrs.android.searchhub.R
import com.srnyndrs.android.searchhub.data.model.Repository
import com.srnyndrs.android.searchhub.data.shared.Constants
import com.srnyndrs.android.searchhub.data.util.convertToDate
import com.srnyndrs.android.searchhub.data.util.getDateTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RepositoryCard(
    repository: Repository,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(vertical = 6.dp),
        onClick = onClick
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            // Repository name
            Text(text = repository.name ?: "Unknown", fontWeight = FontWeight.Bold)
            Divider(thickness = 1.dp)
            Spacer(modifier = Modifier.padding(vertical = 2.dp))
            // Description
            val description = repository.description ?: "No description provided"
            Text(
                // Display according to description character limit
                text = if(description.length > Constants.DESCRIPTION_CHAR_LIMIT) {
                            description.chunked(Constants.DESCRIPTION_CHAR_LIMIT)[0].plus("...")
                        } else {
                            description
                        }
            )
            Spacer(modifier = Modifier.padding(vertical = 2.dp))
            // Number of stars
            IconLabel(
                icon = Icons.Default.Star,
                value = repository.stargazersCount?.toString() ?: "Unknown"
            )
            Spacer(modifier = Modifier.padding(vertical = 2.dp))
            // Date of the last update
            IconLabel(
                icon = ImageVector.vectorResource(R.drawable.calendar_refresh),
                value =  repository.updatedAt?.convertToDate().getDateTime()
            )
        }
    }
}

@Preview
@Composable
fun RepositoryCardPreview() {
    RepositoryCard(
        repository = Repository(
            name = "docker",
            description = "Official repository for docker",
            stargazersCount = 5,
            updatedAt = "2024-02-18T10:00:00Z"
        ),
        onClick = { Log.d("RepositoryCard", "Card clicked") }
    )
}