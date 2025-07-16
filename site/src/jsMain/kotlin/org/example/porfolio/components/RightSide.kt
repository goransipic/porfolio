package org.example.porfolio.components

import androidx.compose.runtime.Composable
import org.example.porfolio.util.Res
import com.varabyte.kobweb.compose.css.ObjectFit
import com.varabyte.kobweb.compose.css.objectFit
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxSize
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.height
import com.varabyte.kobweb.compose.ui.modifiers.objectFit
import com.varabyte.kobweb.compose.ui.thenIf
import com.varabyte.kobweb.silk.components.graphics.Image
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.style.toModifier
import org.jetbrains.compose.web.css.DisplayStyle
import org.jetbrains.compose.web.css.display
import org.jetbrains.compose.web.css.height
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.css.width
import org.jetbrains.compose.web.dom.Img

val RightSideStyle = CssStyle {
    Breakpoint.LG {
        Modifier.height((Res.Dimens.MAX_CARD_HEIGHT - 24).px)
    }
}

@Composable
fun RightSide() {
    Box(
        modifier = RightSideStyle.toModifier()
            .fillMaxWidth()
    ) {
       /* Image(
            modifier = Modifier
                .fillMaxSize()
                .objectFit(ObjectFit.Cover),
            src = Res.Image.PROFILE_SPLIT
        )*/
        ResponsiveSrcSetImage()
    }
}

//488
@Composable
fun ResponsiveSrcSetImage() {
    Img(
        src = "/images/image_split_1.webp", // fallback
        attrs = {
            attr(
                "srcset", """
                    image_split_1.webp 488w,
                    image_split.webp 1000w
                """.trimIndent()
            ) // this 50vw is not correct its must be 100vw
            attr(
                "sizes", """
                    (max-width: 425px) 50vw,
                    488px
                """.trimIndent()
            )
            attr("alt", "Responsive image using srcset")
            style {
                width(100.percent)
                height(100.percent)
                display(DisplayStyle.Block)
                objectFit(ObjectFit.Cover)
            }
        }
    )
}
