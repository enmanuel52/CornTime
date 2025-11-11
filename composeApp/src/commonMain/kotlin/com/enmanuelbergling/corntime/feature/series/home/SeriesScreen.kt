package com.enmanuelbergling.corntime.feature.series.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import corntime.composeapp.generated.resources.Res
import corntime.composeapp.generated.resources.bars_bottom_left
import corntime.composeapp.generated.resources.series
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SeriesScreen(
    onOpenDrawer: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource( Res.string.series)) },
                navigationIcon = {
                    IconButton(onClick = onOpenDrawer) {
                        Icon(
                            painter = painterResource(Res.drawable.bars_bottom_left), contentDescription = "Sandwich menu icon"
                        )
                    }
                },
            )
        },
        contentWindowInsets = WindowInsets.statusBars,
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "Series Screen")
        }

    }
}