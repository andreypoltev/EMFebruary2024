package com.andreypoltev.emfebruary2024.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.andreypoltev.emfebruary2024.R
import kotlin.math.roundToInt

@Composable
fun DiscountTag(discount: Int) {

    val radiusInDp = 4f
    val density = LocalContext.current.resources.displayMetrics.density
    val radiusInPixels = (radiusInDp * density).roundToInt()

    Box(
        modifier = Modifier
            .clip(object : Shape {
                override fun createOutline(
                    size: Size,
                    layoutDirection: LayoutDirection,
                    density: Density
                ): Outline {
                    return Outline.Rounded(
                        roundRect = RoundRect(
                            left = 0f,
                            top = size.height / 8,
                            right = size.width,
                            bottom = size.height * 7 / 8,
                            radiusX = radiusInPixels.toFloat(), // Adjust the radius as needed
                            radiusY = radiusInPixels.toFloat()  // Adjust the radius as needed
                        )
                    )

                }
            })
            .background(colorResource(id = R.color.background_pink))
    ) {
        Text(
            modifier = Modifier.padding(horizontal = 6.dp),
            text = "-${discount}%",
            fontSize = 9.sp,
            color = colorResource(id = R.color.text_white)
//                            style = TextStyle(background = colorResource(id = R.color.background_pink))
        )
    }

}