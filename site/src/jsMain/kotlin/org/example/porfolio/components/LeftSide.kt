package org.example.porfolio.components

import androidx.compose.runtime.Composable
import org.example.porfolio.styles.ButtonStyle
import org.example.porfolio.styles.SocialIconStyle
import org.example.porfolio.util.Res
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.css.TextAlign
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.foundation.layout.columnClasses
import com.varabyte.kobweb.compose.foundation.layout.rowClasses
import com.varabyte.kobweb.compose.style.toClassName
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.attrsModifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.core.AppGlobals
import com.varabyte.kobweb.silk.components.forms.Button
import com.varabyte.kobweb.silk.components.forms.ButtonSize
import com.varabyte.kobweb.silk.components.graphics.Image
import com.varabyte.kobweb.silk.components.layout.Surface
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.style.toModifier
import com.varabyte.kobweb.silk.theme.breakpoint.rememberBreakpoint
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import kotlinx.browser.window
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.px

val LeftSideStyle = CssStyle {

    (Breakpoint.ZERO..Breakpoint.SM) {
        Modifier.columnClasses(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        )
    }

    Breakpoint.MD {
        Modifier.columnClasses(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center
        )
    }

}

val SpanStyle = CssStyle {
    (Breakpoint.ZERO..Breakpoint.SM) {
        Modifier.textAlign(TextAlign.Center)
    }
    Breakpoint.MD {
        Modifier.textAlign(TextAlign.Start)
    }
}

val SurfaceStyle = CssStyle {
    (Breakpoint.ZERO..Breakpoint.SM) {
        Modifier.align(
            Alignment.CenterHorizontally
        )
    }
    Breakpoint.MD {
        Modifier.align(Alignment.Start)
    }
}

val RowStyle = CssStyle {
    Breakpoint.ZERO {
        Modifier.rowClasses(Arrangement.Start)
    }

    (Breakpoint.ZERO..Breakpoint.SM) {
        Modifier.rowClasses(
            horizontalArrangement = Arrangement.Center
        )
    }
}

fun Modifier.align(alignment: Alignment.Horizontal) = attrsModifier {
    classes("${alignment.toClassName()}-self")
}

@Composable
fun LeftSide(
    colorMode: ColorMode
) {
    val breakpoint = rememberBreakpoint()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 50.px),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = if (breakpoint <= Breakpoint.SM)
            Alignment.CenterHorizontally else Alignment.Start
        ) {
        SpanText(
            text = Res.String.NAME,
            modifier = SpanStyle.toModifier()
                .margin(bottom = 12.px)
                .fontFamily(Res.String.ROBOTO_CONDENSED)
                .color(if (colorMode.isLight) Colors.Black else Colors.White)
                .fontSize(50.px)
                .fontWeight(FontWeight.Bold)
        )
        SpanText(
            text = Res.String.PROFESSION,
            modifier = Modifier
                .margin(bottom = 24.px)
                .fontFamily(Res.String.ROBOTO_REGULAR)
                .color(if (colorMode.isLight) Colors.Black else Colors.White)
                .fontSize(18.px)
        )
        SpanText(
            text = AppGlobals.getValue("version"),
            modifier = Modifier
                .margin(bottom = 24.px)
                .fontFamily(Res.String.ROBOTO_REGULAR)
                .color(if (colorMode.isLight) Colors.Black else Colors.White)
                .fontSize(18.px)
        )
        Surface(
            modifier = SurfaceStyle.toModifier()
                .height(4.px)
                .width(40.px)
                .margin(bottom = 24.px)
                .background(
                    if (colorMode.isLight) Res.Theme.BLUE.color
                    else Res.Theme.LIGHT_BLUE.color
                )

        ) {}
        SpanText(
            modifier = SpanStyle.toModifier()
                .fontFamily(Res.String.ROBOTO_REGULAR)
                .fontSize(14.px)
                .color(if (colorMode.isLight) Colors.Black else Colors.White)
                .opacity(50.percent)
                .lineHeight(2)
                .margin(bottom = 36.px),
            text = Res.String.ABOUT_ME
        )
        Button(
            modifier = ButtonStyle.toModifier()
                .margin(bottom = 25.px),
            size = ButtonSize.LG,
            onClick = { window.location.href = Res.String.MY_EMAIL }
        ) {
            Image(
                modifier = Modifier.margin(right = 12.px),
                src = if (colorMode.isLight) Res.Icon.EMAIL_LIGHT
                else Res.Icon.EMAIL_DARK
            )
            SpanText(
                modifier = Modifier
                    .fontSize(14.px)
                    .color(
                        if (colorMode.isLight) Colors.White
                        else Res.Theme.GRADIENT_ONE_DARK.color
                    )
                    .fontWeight(FontWeight.Bold)
                    .fontFamily(Res.String.ROBOTO_REGULAR),
                text = Res.String.BUTTON_TEXT
            )
        }

        Row(
            modifier = RowStyle.toModifier()
                .fillMaxWidth()
                .gap(12.px),

            ) {
            SocialIcon.entries.filter {
                if (colorMode.isLight) !it.name.contains("Light")
                else it.name.contains("Light")
            }.forEach {
                IconButton(
                    modifier = SocialIconStyle.toModifier(),
                    colorMode = colorMode,
                    icon = it.icon,
                    link = it.link
                )
            }
        }
    }
}