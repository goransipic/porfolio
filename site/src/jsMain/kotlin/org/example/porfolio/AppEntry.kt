package org.example.porfolio

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxHeight
import com.varabyte.kobweb.compose.ui.modifiers.maxHeight
import com.varabyte.kobweb.core.App
import com.varabyte.kobweb.silk.SilkApp
import com.varabyte.kobweb.silk.components.layout.Surface
import com.varabyte.kobweb.silk.init.InitSilk
import com.varabyte.kobweb.silk.init.InitSilkContext
import com.varabyte.kobweb.silk.init.registerStyleBase
import com.varabyte.kobweb.silk.style.common.SmoothColorStyle
import com.varabyte.kobweb.silk.style.toModifier
import com.varabyte.kobweb.silk.theme.colors.palette.button
import org.example.porfolio.util.Res
import org.jetbrains.compose.web.css.vh


@InitSilk
fun initStyles(ctx: InitSilkContext) {
    ctx.stylesheet.registerStyleBase("html, body") { Modifier.fillMaxHeight() }
}

@App
@Composable
fun MyApp(content: @Composable () -> Unit) {
    SilkApp {
        Surface(SmoothColorStyle.toModifier().maxHeight(100.vh)) {
            content()
        }
    }
}

@InitSilk
fun overrideSilkTheme(context: InitSilkContext) {
    context.apply {
        theme.palettes.apply {
            light.apply {
                button.apply {
                    default = Res.Theme.BLUE.color
                    hover = Res.Theme.BLUE.color
                    pressed = Res.Theme.BLUE.color
                }
            }

            dark.apply {
                button.apply {
                    default = Res.Theme.LIGHT_BLUE.color
                    hover = Res.Theme.LIGHT_BLUE.color
                    pressed = Res.Theme.LIGHT_BLUE.color
                }
            }
        }
    }
}