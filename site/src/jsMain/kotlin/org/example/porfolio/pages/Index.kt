package org.example.porfolio.pages

import androidx.compose.runtime.Composable
import androidx.compose.runtime.StableMarker
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.varabyte.kobweb.compose.css.AlignItems
import com.varabyte.kobweb.compose.css.JustifyItems
import com.varabyte.kobweb.compose.css.functions.LinearGradient
import com.varabyte.kobweb.compose.css.functions.linearGradient
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.core.AppGlobals
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.style.toModifier
import com.varabyte.kobweb.silk.theme.breakpoint.rememberBreakpoint
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import org.example.porfolio.components.ProfileCard
import org.example.porfolio.components.ThemeSwitchButton
import org.example.porfolio.util.Res
import org.jetbrains.compose.web.css.DisplayStyle
import org.jetbrains.compose.web.css.fr
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.px


val HomeStyle = CssStyle {
    base {
        Modifier
            .display(DisplayStyle.Grid)
            .gridTemplateColumns {
                minmax(0.px, 1.fr)
            }
            .gridTemplateRows {
                minmax(0.px, 1.fr)
            }
    }

    cssRule(Breakpoint.MD.toCSSMediaQuery(), ".kobweb-align") {
        Modifier.placeItems(AlignItems.Center, JustifyItems.Center)
    }

    cssRule((Breakpoint.ZERO..Breakpoint.SM).toCSSMediaQuery(), ".kobweb-align") {
        Modifier.placeItems(AlignItems.Start, JustifyItems.Center)
    }
}

@Page
@Composable
fun HomePage() {
    var colorMode by ColorMode.currentState
    ThemeSwitchButton(
        colorMode = colorMode,
        onClick = {
            colorMode = colorMode.opposite
        }
    )
    val breakPoint = rememberBreakpoint()
    Column(
        Modifier
            .fillMaxHeight()
            .backgroundImage(
                linearGradient(
                    if (colorMode.isLight) Res.Theme.GRADIENT_ONE.color
                    else Res.Theme.GRADIENT_ONE_DARK.color,
                    if (colorMode.isLight) Res.Theme.GRADIENT_TWO.color
                    else Res.Theme.GRADIENT_TWO_DARK.color,
                   LinearGradient.Direction.ToRight
                )
            ),
        verticalArrangement = if (breakPoint <= Breakpoint.SM) Arrangement.Top else Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ProfileCard(colorMode = colorMode)
        SpanText(
            text = "Date created : ${AppGlobals.getValue("version")}",
            modifier = Modifier
                .margin(top = 24.px)
                .padding(bottom = if(breakPoint <= Breakpoint.SM) 8.px else 0.px)
                .fontFamily(Res.String.ROBOTO_REGULAR)
                .color(if (colorMode.isLight) Colors.Black else Colors.White)
                .opacity(50.percent)
                .fontSize(18.px)
        )

    }
    /*BoxCostume(
        modifier = Modifier
            .fillMaxHeight()
            .backgroundImage(
                linearGradient(
                    dir = LinearGradient.Direction.ToRight,
                    from = if (colorMode.isLight) Res.Theme.GRADIENT_ONE.color
                    else Res.Theme.GRADIENT_ONE_DARK.color,
                    to = if (colorMode.isLight) Res.Theme.GRADIENT_TWO.color
                    else Res.Theme.GRADIENT_TWO_DARK.color
                )
            )) {
        ProfileCard(colorMode = colorMode)
    }*/
}
