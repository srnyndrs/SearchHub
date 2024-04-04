package com.srnyndrs.android.searchhub.presentation.home_screen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.srnyndrs.android.searchhub.R
import com.srnyndrs.android.searchhub.domain.model.Owner
import com.srnyndrs.android.searchhub.domain.model.Repository
import com.srnyndrs.android.searchhub.domain.util.Constants
import com.srnyndrs.android.searchhub.domain.util.convertToDate
import com.srnyndrs.android.searchhub.domain.util.getDateTime
import com.srnyndrs.android.searchhub.ui.theme.SearchHubTheme

@Composable
fun RepositoryCard(
    repository: Repository?,
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
            Text(text = repository?.name ?: "Unknown", fontWeight = FontWeight.Bold)
            HorizontalDivider(thickness = 1.dp)
            Spacer(modifier = Modifier.padding(vertical = 2.dp))
            // Description
            val description = repository?.description ?: "No description provided"
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
                value = repository?.stars?.toString() ?: "Unknown"
            )
            Spacer(modifier = Modifier.padding(vertical = 2.dp))
            // Date of the last update
            IconLabel(
                icon = ImageVector.vectorResource(R.drawable.calendar_refresh),
                value =  repository?.updatedAt?.convertToDate().getDateTime()
            )
        }
    }
}

@PreviewLightDark
@Composable
fun RepositoryCardPreview() {
    SearchHubTheme {
        Surface {
            RepositoryCard(
                repository = Repository(
                    name = "k8s",
                    owner = Owner(
                        name = "srnyndrs",
                        profilePath = "",
                        avatarPath = ""
                    ),
                    description = "",
                    repositoryPath = "",
                    stars = 5,
                    forks = 5,
                    createdAt = "",
                    updatedAt = ""
                ),
                onClick = {}
            )
        }
    }
}