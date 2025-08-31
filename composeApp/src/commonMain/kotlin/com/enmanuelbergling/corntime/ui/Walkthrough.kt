package com.enmanuelbergling.corntime.ui

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.enmanuelbergling.corntime.core.ui.components.walkthrough.WalkThrough
import com.enmanuelbergling.corntime.core.ui.components.walkthrough.model.IndicatorStyle
import com.enmanuelbergling.corntime.core.ui.components.walkthrough.model.WalkScrollStyle
import com.enmanuelbergling.corntime.core.ui.components.walkthrough.model.WalkStep
import corntime.composeapp.generated.resources.*
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun OnboardingScreen(
    onFinish: () -> Unit,
) {
    val pagerState = rememberPagerState { ONBOARDING_STEPS.count() }
    val scope = rememberCoroutineScope()

    Scaffold { paddingValues ->

        WalkThrough(
            steps = ONBOARDING_STEPS.map { it.toWalkStep() },
            pagerState = pagerState,
            modifier = Modifier.padding(paddingValues),
            bottomButton = {
                Button(
                    onClick = {
                        scope.launch {
                            if (pagerState.canScrollForward) {
                                pagerState.animateScrollToPage(
                                    pagerState.currentPage + 1,
                                    animationSpec = tween(500)
                                )

                            } else {
                                onFinish()
                            }
                        }
                    },
                    modifier = Modifier.fillMaxWidth(.7f),
                    contentPadding = PaddingValues(vertical = 8.dp)
                ) {
                    AnimatedContent(
                        targetState = pagerState.canScrollForward,
                        label = "text button animation"
                    ) { forward ->
                        if (forward) {
                            Text(text = stringResource(Res.string.next))
                        } else {
                            Text(text = stringResource(Res.string.get_started))
                        }
                    }
                }
            },
            skipButton = {
                TextButton(onClick = onFinish) {
                    Text(text = stringResource(Res.string.skip))
                }
            },
            scrollStyle = WalkScrollStyle.Normal,
            indicatorStyle = IndicatorStyle.Step()
        )
    }
}

val ONBOARDING_STEPS = listOf(
    OnboardingRes(
        Res.string.title_explore_movies,
        Res.string.step_explore_movies,
        Res.drawable.undraw_netflix,
    ),
    OnboardingRes(
        Res.string.title_filter_movies,
        Res.string.step_filter_movies,
        Res.drawable.undraw_searching,
    ),
    OnboardingRes(
        Res.string.title_search,
        Res.string.step_search,
        Res.drawable.undraw_mobile_search,
    ),
    OnboardingRes(
        Res.string.title_watchlist,
        Res.string.step_watchlist,
        Res.drawable.undraw_online_video,
    ),
    OnboardingRes(
        Res.string.title_shortcut,
        Res.string.step_shortcut,
        Res.drawable.undraw_to_the_stars,
    ),
    OnboardingRes(
        Res.string.title_actors,
        Res.string.step_actors,
        Res.drawable.undraw_awards,
    ),
)

/**
 * Like a [WalkStep] but using resources
 * */
data class OnboardingRes(
    val title: StringResource,
    val description: StringResource,
     val image: DrawableResource,
) {
    @Composable
    fun toWalkStep() = WalkStep(
        image,
        stringResource(title),
        stringResource(description),
    )
}