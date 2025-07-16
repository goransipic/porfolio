package org.example.porfolio.pages

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.varabyte.kobweb.compose.css.Cursor
import com.varabyte.kobweb.compose.css.ObjectFit
import com.varabyte.kobweb.compose.css.Overflow
import com.varabyte.kobweb.compose.css.TextAlign.Companion.Justify
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.backgroundColor
import com.varabyte.kobweb.compose.ui.modifiers.borderRadius
import com.varabyte.kobweb.compose.ui.modifiers.boxShadow
import com.varabyte.kobweb.compose.ui.modifiers.cursor
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxSize
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.gap
import com.varabyte.kobweb.compose.ui.modifiers.height
import com.varabyte.kobweb.compose.ui.modifiers.justifyContent
import com.varabyte.kobweb.compose.ui.modifiers.left
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.modifiers.objectFit
import com.varabyte.kobweb.compose.ui.modifiers.onClick
import com.varabyte.kobweb.compose.ui.modifiers.overflow
import com.varabyte.kobweb.compose.ui.modifiers.position
import com.varabyte.kobweb.compose.ui.modifiers.right
import com.varabyte.kobweb.compose.ui.modifiers.size
import com.varabyte.kobweb.compose.ui.modifiers.top
import com.varabyte.kobweb.compose.ui.modifiers.translateY
import com.varabyte.kobweb.compose.ui.modifiers.width
import com.varabyte.kobweb.compose.ui.styleModifier
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.silk.components.forms.Button
import com.varabyte.kobweb.silk.components.graphics.Image
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import org.jetbrains.compose.web.css.JustifyContent
import org.jetbrains.compose.web.css.Position
import org.jetbrains.compose.web.css.cssRem
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.Text

@Page
@Composable
fun AboutPage() {
    val colorMode = ColorMode.current
    val images = listOf("/image_split.webp", "/photo.jpg", "/image_split.webp")
    val slideWidth = 600 // px
    val slideHeight = 400
    var currentSlide by remember { mutableStateOf(0) }
    val totalSlides = images.size

    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            Modifier
                .width(slideWidth.px)
                .height(slideHeight.px)
                .overflow(Overflow.Hidden)
                .borderRadius(1.cssRem)
            //.boxShadow(blurRadius = 0.5.cssRem)
        ) {
            Row(
                Modifier
                    .width((slideWidth * totalSlides).px)
                    .height(slideHeight.px)
                    .styleModifier {
                        property("position", "relative")
                        property("left", "${-slideWidth * currentSlide}px")
                        property("transition", "left 0.5s ease")
                    }
            ) {
                images.forEach { image ->
                    Image(
                        src = image,
                        description = "Slide image",
                        modifier = Modifier
                            .width(slideWidth.px)
                            .height(slideHeight.px)
                            .objectFit(ObjectFit.Cover)
                    )
                }
            }

            // Prev Button
            Button(
                onClick = {
                    if (currentSlide > 0) currentSlide--
                },
                modifier = Modifier
                    .position(Position.Absolute).margin(left = 25.percent)
                    .left(0.px)
                    .top(50.percent)
                    .translateY((-50).percent)
            ) {
                Text("<")
            }

            // Next Button
            Button(
                onClick = {
                    if (currentSlide < totalSlides - 1) currentSlide++
                },
                modifier = Modifier
                    .position(Position.Absolute).margin(right = 25.percent)
                    .right(0.px)
                    .top(50.percent)
                    .translateY((-50).percent)
            ) {
                Text(">")
            }
        }

        // Pagination Dots
        Row(
            Modifier
                .margin(top = 1.cssRem)
                .gap(0.5.cssRem),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            repeat(totalSlides) { index ->
                val isActive = index == currentSlide
                Box(
                    Modifier
                        .size(if (isActive) 1.cssRem else 0.85.cssRem)
                        .borderRadius(50.percent)
                        .backgroundColor(if (isActive) Colors.LightBlue else Colors.LightGray)
                        .cursor(Cursor.Pointer)
                        .styleModifier {
                            property("transition", "all 0.3s ease")
                            property("transform", if (isActive) "scale(1.2)" else "scale(1)")
                        }
                        .onClick { currentSlide = index }
                )
            }
        }
    }

}