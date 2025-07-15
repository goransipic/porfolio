package org.example.porfolio.components

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.silk.components.layout.SimpleGrid
import com.varabyte.kobweb.silk.components.layout.numColumns
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.style.toModifier
import com.varabyte.kobweb.silk.style.until
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import org.example.porfolio.util.Res
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.px

@Composable
fun ProfileCard(colorMode: ColorMode) {

    SimpleGrid(
        numColumns = numColumns(base = 1, md = 2),
        modifier = ProfileCard.toModifier()
            .boxShadow(
                color = Colors.Black.copy(alpha = 10),
                blurRadius = 50.px,
                spreadRadius = 50.px
            )
            .padding(all = 12.px)
            .borderRadius(r = Res.Dimens.BORDER_RADIUS.px)
            .background(
                if (colorMode.isLight) Colors.White else
                    Res.Theme.DARK_BLUE.color
            )
    ) {
        LeftSide(colorMode = colorMode)
        RightSide()
    }
}

val ProfileCard = CssStyle {

    until(Breakpoint.MD){
        Modifier.fillMaxWidth(100.percent)
    }
    (Breakpoint.MD){
        Modifier.fillMaxWidth(Res.Dimens.MAX_CARD_WIDTH.px)
    }
    (Breakpoint.LG){
        Modifier.height(Res.Dimens.MAX_CARD_HEIGHT.px)
    }
}