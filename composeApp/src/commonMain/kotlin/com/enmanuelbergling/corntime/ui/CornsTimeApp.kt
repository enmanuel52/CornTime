package com.enmanuelbergling.corntime.ui

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.rememberSplineBasedDecay
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.AnchoredDraggableState
import androidx.compose.foundation.gestures.DraggableAnchors
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.anchoredDraggable
import androidx.compose.foundation.gestures.animateTo
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import com.enmanuelbergling.corntime.core.model.user.UserDetails
import com.enmanuelbergling.corntime.core.ui.core.LocalSharedTransitionScope
import com.enmanuelbergling.corntime.core.ui.core.dimen
import com.enmanuelbergling.corntime.core.ui.theme.CornTimeTheme
import com.enmanuelbergling.corntime.navigation.CtiNavHost
import com.enmanuelbergling.corntime.navigation.TopDestination
import corntime.composeapp.generated.resources.Res
import corntime.composeapp.generated.resources.logout
import corntime.composeapp.generated.resources.menu
import corntime.composeapp.generated.resources.offline_message
import corntime.composeapp.generated.resources.power_outline
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import kotlin.math.roundToInt

enum class NewDrawerState {
    Open, Closed
}

val NewDrawerWidth = 200.dp

@OptIn(ExperimentalSharedTransitionApi::class, ExperimentalFoundationApi::class)
@Composable
fun CornsTimeApp(
    state: CornTimeAppState = rememberCornTimeAppState(),
    userDetails: UserDetails?,
    onLogout: () -> Unit,
) {
    val scope = rememberCoroutineScope()

    val snackBarHostState = remember { SnackbarHostState() }

    val density = LocalDensity.current

    val drawerWidthPx = with(density) { NewDrawerWidth.toPx() }

    val decayAnimationSpec = rememberSplineBasedDecay<Float>()

    val draggableAnchors = DraggableAnchors {
        NewDrawerState.Open at drawerWidthPx
        NewDrawerState.Closed at 0f
    }

    val draggableState = remember {
        AnchoredDraggableState(
            initialValue = NewDrawerState.Closed,
            positionalThreshold = { distance: Float -> distance * 0.6f },
            velocityThreshold = { with(density) { 80.dp.toPx() } },
            decayAnimationSpec = decayAnimationSpec,
            snapAnimationSpec = spring(Spring.DampingRatioLowBouncy, Spring.StiffnessLow),
            anchors = draggableAnchors
        )
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackBarHostState) },
        contentWindowInsets = WindowInsets(0)
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
        ) {
            DrawerContent(
                onDrawerDestination = { drawerDestination ->
                    state.navigateToDrawerDestination(drawerDestination)
                    scope.launch {
                        draggableState.animateTo(NewDrawerState.Closed)
                    }
                },
                isSelected = { route -> state.matchRoute(route = route) },
                userDetails = userDetails,
                onCloseDrawer = {
                    scope.launch {
                        draggableState.animateTo(NewDrawerState.Closed)
                    }
                },
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .width(NewDrawerWidth)
                    .padding(MaterialTheme.dimen.medium),
                onLogout = onLogout
            )

            SharedTransitionLayout {
                CompositionLocalProvider(value = LocalSharedTransitionScope provides this) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .graphicsLayer {
                                translationX = draggableState.requireOffset()

                                val fraction = draggableState.requireOffset() / drawerWidthPx
                                val scale = lerp(1f, .8f, fraction)

                                scaleX = scale
                                scaleY = scale

                                val percent = fraction
                                    .times(5)
                                    .roundToInt()
                                    .coerceAtLeast(0)

                                shape = RoundedCornerShape(percent)
                                clip = true
                            }
                            .anchoredDraggable(
                                state = draggableState,
                                orientation = Orientation.Horizontal,
                                enabled = state.mainDrawerEnabled
                            )
                    ) {
                        Surface {
                            CtiNavHost(
                                state = state,
                                onOpenDrawer = {
                                    scope.launch {
                                        draggableState.animateTo(NewDrawerState.Open)
                                    }
                                },
                            )
                        }

                        if (draggableState.currentValue == NewDrawerState.Open) {
                            Box(modifier = Modifier
                                .fillMaxSize()
                                .clickable(null, null) {
                                    scope.launch {
                                        draggableState.animateTo(NewDrawerState.Closed)
                                    }
                                }
                            )
                        }
                    }
                }
            }
        }
    }


    val offlineMessage = stringResource(Res.string.offline_message)
    LaunchedEffect(key1 = state.isOnline) {
        println("isOnline: ${state.isOnline}")
        if (!state.isOnline) {
            snackBarHostState.showSnackbar(
                offlineMessage,
                duration = SnackbarDuration.Indefinite,
                withDismissAction = true
            )
        } else {
            snackBarHostState.currentSnackbarData?.dismiss()
        }
    }
}

val OnSurfaceLight = Color(0xFF231918)

@Composable
fun DrawerContent(
    onDrawerDestination: (TopDestination) -> Unit,
    onCloseDrawer: () -> Unit,
    isSelected: @Composable (route: Any) -> Boolean,
    userDetails: UserDetails?,
    onLogout: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.dimen.medium)
    ) {
        CompositionLocalProvider(value = LocalContentColor provides OnSurfaceLight) {

            Text(
                text = stringResource(Res.string.menu), style = MaterialTheme.typography.displayMedium,
                color = LocalContentColor.current.copy(alpha = .8f)
            )

            Spacer(modifier = Modifier.height(MaterialTheme.dimen.mediumSmall))

            TopDestination.entries
                .filterNot { it.loginRequired && userDetails == null }
                .forEach { destination ->
                    val selected = isSelected(destination.route)
                    NavDrawerItem(
                        label = stringResource(destination.label),
                        selected = selected,
                        onClick = { onDrawerDestination(destination) },
                        iconRes = if (selected) destination.selectedIconRes else destination.unselectedIconRes,
                        modifier = Modifier.fillMaxWidth()
                    )
                }

            NavDrawerItem(
                label = stringResource( Res.string.logout),
                selected = false,
                iconRes = Res.drawable.power_outline,
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    onLogout()
                    onCloseDrawer()
                },
            )
        }
    }
}

@Composable
fun NavDrawerItem(
    label: String,
    selected: Boolean,
    iconRes: DrawableResource,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    val titleLarge = MaterialTheme.typography.titleLarge
    val titleMedium = MaterialTheme.typography.titleMedium

    val typography by remember(selected) {
        derivedStateOf {
            if (selected) titleLarge else titleMedium
        }
    }

    val density = LocalDensity.current

    val iconSize by animateDpAsState(
        targetValue = with(density) { typography.fontSize.toDp() },
        label = "icon size animation",
    )

    val colorAnimation by animateColorAsState(
        targetValue = if (selected) LocalContentColor.current
        else LocalContentColor.current.copy(alpha = .6f),
        label = "color animation",
    )

    Row(
        modifier = modifier.clickable(
            interactionSource = null,
            indication = null,
            onClick = onClick,
        ),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            painter = painterResource(iconRes),
            contentDescription = label,
            tint = colorAnimation,
            modifier = Modifier.size(iconSize)
        )

        Spacer(modifier = Modifier.width(MaterialTheme.dimen.mediumSmall))

        Text(
            text = label,
            fontWeight = FontWeight.SemiBold,
            color = colorAnimation,
            style = typography
        )
    }
}

@Preview
@Composable
private fun DrawerContentPrev() {
    CornTimeTheme {
        DrawerContent(
            onDrawerDestination = {},
            isSelected = { true },
            userDetails = null,
            modifier = Modifier
                .width(NewDrawerWidth)
                .padding(12.dp),
            onCloseDrawer = {},
            onLogout = {},
        )
    }
}