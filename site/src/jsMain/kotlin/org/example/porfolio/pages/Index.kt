package org.example.porfolio.pages

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.varabyte.kobweb.compose.css.functions.LinearGradient
import com.varabyte.kobweb.compose.css.functions.linearGradient
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.backgroundImage
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxHeight
import com.varabyte.kobweb.compose.ui.modifiers.minHeight
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.silk.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.theme.breakpoint.rememberBreakpoint
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import kotlinx.browser.localStorage
import org.example.porfolio.COLOR_MODE_KEY
import org.example.porfolio.components.ProfileCard
import org.example.porfolio.components.ThemeSwitchButton
import org.example.porfolio.util.Res
import org.jetbrains.compose.web.css.vh

@Page
@Composable
fun HomePage() {
    var colorMode by ColorMode.currentState

    LaunchedEffect(colorMode) {
        localStorage.setItem(COLOR_MODE_KEY.name, colorMode.name)
    }

    ThemeSwitchButton(
        colorMode = colorMode,
        onClick = {
            colorMode = colorMode.opposite
            localStorage.setItem(COLOR_MODE_KEY.name, colorMode.name)
        }
    )
    val breakPoint = rememberBreakpoint()
    Box(
        Modifier
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
}
