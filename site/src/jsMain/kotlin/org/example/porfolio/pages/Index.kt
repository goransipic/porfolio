package org.example.porfolio.pages

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.varabyte.kobweb.compose.css.AlignItems
import com.varabyte.kobweb.compose.css.JustifyItems
import com.varabyte.kobweb.compose.css.functions.LinearGradient
import com.varabyte.kobweb.compose.css.functions.linearGradient
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.core.Page
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
    Box(
        HomeStyle.toModifier()
            .fillMaxHeight()
            .backgroundImage(
                linearGradient(
                    dir = LinearGradient.Direction.ToRight,
                    from = if (colorMode.isLight) Res.Theme.GRADIENT_ONE.color
                    else Res.Theme.GRADIENT_ONE_DARK.color,
                    to = if (colorMode.isLight) Res.Theme.GRADIENT_TWO.color
                    else Res.Theme.GRADIENT_TWO_DARK.color
                )
            ),
        contentAlignment = if (breakPoint <= Breakpoint.SM) Alignment.TopCenter else Alignment.Center
    ) {
        ProfileCard(colorMode = colorMode)
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
