package com.srnyndrs.android.searchhub.presentation.home_screen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.srnyndrs.android.searchhub.R
import com.srnyndrs.android.searchhub.domain.model.Repository
import com.srnyndrs.android.searchhub.domain.sample.SampleRepositoryProvider
import com.srnyndrs.android.searchhub.domain.util.Constants
import com.srnyndrs.android.searchhub.domain.util.convertToDate
import com.srnyndrs.android.searchhub.domain.util.getDate
import com.srnyndrs.android.searchhub.presentation.components.IconLabel
import com.srnyndrs.android.searchhub.ui.theme.SearchHubTheme

@Composable
fun RepositoryCard(
    repository: Repository,
    onClick: () -> Unit
) {
    OutlinedCard(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        onClick = onClick
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(0.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(0.dp),
                horizontalAlignment = Alignment.Start
            ) {
                // Repository name
                Text(
                    modifier = Modifier.padding(start = 3.dp),
                    text = repository.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                // Stars and update date
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    IconLabel(
                        icon = Icons.Default.Star,
                        value = repository.stars.toString()
                    )
                    IconLabel(
                        icon = ImageVector.vectorResource(R.drawable.calendar_refresh),
                        value = repository.updatedAt.convertToDate().getDate()
                    )
                }
            }

            HorizontalDivider(
                thickness = 1.dp,
                color = MaterialTheme.colorScheme.onTertiaryContainer
            )

            // Description
            Text(
                // Display according to description character limit
                text = if(repository.description.length > Constants.DESCRIPTION_CHAR_LIMIT) {
                    repository.description.chunked(Constants.DESCRIPTION_CHAR_LIMIT)[0].plus("...")
                } else {
                    repository.description
                },
                fontStyle = FontStyle.Italic,
                textAlign = TextAlign.Justify
            )
        }
    }
}

@PreviewLightDark
@Composable
fun RepositoryCardPreview(
    @PreviewParameter(SampleRepositoryProvider::class) repositories: List<Repository>
) {
    SearchHubTheme {
        Surface(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 3.dp, horizontal = 6.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                repositories.forEach { repository ->
                    RepositoryCard(
                        repository = repository,
                        onClick = {}
                    )
                }
            }
        }
    }
}