package com.andreypoltev.emfebruary2024.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.andreypoltev.emfebruary2024.R

//@Composable
@Composable
fun AddToCartButton() {

    val size = 32.dp

    Box(
        modifier = Modifier
            .size(size)
            .clip(RoundedCornerShape(topStart = size / 4, bottomEnd = size / 4))
            .background(colorResource(id = R.color.background_pink))
    ) {

        IconButton(
            onClick = { },
            modifier = Modifier.align(Alignment.BottomEnd)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.add_to_cart),
                contentDescription = null, Modifier.size(24.dp), tint = Color.White
            )
        }

    }

//
//    val customShape = object : Shape {
//        override fun createOutline(size: Size, layoutDirection: LayoutDirection, density: Density): Outline {
//            return Outline.Rounded(
//                topLeft = RoundedCornerShape(50.dp).createCornerSize(size, layoutDirection, density),
//                topRight = RoundedCornerShape(0.dp).createCornerSize(size, layoutDirection, density),
//                bottomRight = RoundedCornerShape(50.dp).createCornerSize(size, layoutDirection, density),
//                bottomLeft = RoundedCornerShape(0.dp).createCornerSize(size, layoutDirection, density)
//            ).toOutline(size, layoutDirection, density)
//        }
//    }
//
}

//class RoundedSquareShape : Shape {
//    override fun createOutline(
//        size: androidx.compose.ui.geometry.Size,
//        layoutDirection: LayoutDirection,
//        density: Density
//    ): Outline {
//        CornerRadius(
//
//        )
//        val cornerRadius = CornerRadius(
//            topStart = size.width / 4f,
//            topEnd = 0f,
//            bottomStart = 0f,
//            bottomEnd = size.width / 4f
//        )
//        return Outline.Generic(Path.makeRoundedRect(0f, 0f, size.width, size.height, cornerRadius, null))
//    }
//}