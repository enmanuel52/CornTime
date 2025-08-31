package com.enmanuelbergling.corntime

import android.graphics.RuntimeShader
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.withInfiniteAnimationFrameMillis
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.CacheDrawScope
import androidx.compose.ui.draw.DrawResult
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.ShaderBrush
import androidx.compose.ui.graphics.lerp
import com.enmanuelbergling.corntime.core.ui.shaders.HotPlasmaShader
import kotlin.math.ceil


const val ROWS = 3.2f
const val COLUMNS = 2.4f

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
private fun CacheDrawScope.drawShader(
    backgroundShader: String,
    shaderTime: Float,
): DrawResult {
    val runtimeShader = RuntimeShader(backgroundShader)
    val shaderBrush = ShaderBrush(runtimeShader)

    runtimeShader.setFloatUniform(
        "resolution", size.width / COLUMNS, size.height / ROWS
    )

    //the sum is to make sure of filling the entire space
    val rectSize = Size(
        width = size.width / COLUMNS + 2f,
        height = size.height / ROWS + 2f,
    )

    return onDrawWithContent {
        runtimeShader.setFloatUniform(
            "time", shaderTime
        )

        repeat(ceil(ROWS).toInt()) { row ->
            repeat(ceil(COLUMNS).toInt()) { column ->
                drawRect(
                    brush = shaderBrush,
                    size = rectSize,
                    topLeft = Offset(
                        x = size.width * column / COLUMNS,
                        y = size.height * row / ROWS,
                    ),
                )
            }
        }

        drawContent()
    }
}

const val SPEED = 1.1f

@Composable
fun Background(modifier: Modifier = Modifier) {
    val containerColor by animateColorAsState(
        targetValue = lerp(
            start = MaterialTheme.colorScheme.surface,
            stop = MaterialTheme.colorScheme.primaryContainer,
            fraction = .5f//draggableState.requireOffset() / drawerWidthPx
        ),
        label = "container color animation",
    )

    val shaderTime by produceState(0f) {
        while (true) {
            withInfiniteAnimationFrameMillis {
                value = it / 1000f * SPEED
            }
        }
    }

    Modifier
        .fillMaxSize()
        .drawWithCache {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            drawShader(HotPlasmaShader, shaderTime)
        } else {
            onDrawWithContent {
                drawRect(containerColor)
                drawContent()
            }
        }
    }
}