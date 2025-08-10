package org.example.porfolio.components

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.css.*
import com.varabyte.kobweb.compose.foundation.layout.*
import com.varabyte.kobweb.compose.style.toClassName
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.attrsModifier
import com.varabyte.kobweb.compose.ui.graphics.Color
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.styleModifier
import com.varabyte.kobweb.silk.components.forms.*
import com.varabyte.kobweb.silk.components.layout.Surface
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.style.animation.Keyframes
import com.varabyte.kobweb.silk.style.base
import com.varabyte.kobweb.silk.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.style.toModifier
import com.varabyte.kobweb.silk.theme.breakpoint.rememberBreakpoint
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import kotlinx.browser.window
import kotlinx.coroutines.delay
import org.example.porfolio.components.widget.ShimmerImage
import org.example.porfolio.styles.ButtonStyle
import org.example.porfolio.styles.SocialIconStyle
import org.example.porfolio.util.Res
import org.jetbrains.compose.web.css.*
import com.varabyte.kobweb.silk.components.forms.Button


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
            ShimmerImage(
                modifier = Modifier.margin(right = 12.px),
                width = 20.px, height = 20.px,
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
            horizontalArrangement = if (breakpoint <= Breakpoint.SM)
                Arrangement.Center else Arrangement.Start
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
        var isLoaded by remember { mutableStateOf(false) }

        LaunchedEffect(Unit) {
            delay(2000)
            isLoaded = true
        }
        val modifierisLoading =
            if (isLoaded) Modifier.height(40.px) else PlaceholderStyle.toModifier().fillMaxWidth().height(40.px)
                .borderRadius(4.px) // ✅ Rounded corners

        /*Row(modifier = PlaceholderGlowStyle.toModifier().fillMaxWidth()) {
            SpanText("Data", modifier = modifierisLoading)
        }*/
        /*Row(modifier = SpinnerBorderStyle.toModifier()) {
            SpanText("Data", modifier = VisuallyHiddenStyle.toModifier())
        }

        Row(modifier = SpinnerGrowStyle.toModifier()) {
            SpanText("Data", modifier = VisuallyHiddenStyle.toModifier())
        }*/
        //Switch(checked = isLoaded, onCheckedChange = { isLoaded = it }, variant = Foo1, size = FOO)
        //Button()
    }
}

val PlaceholderGlowStyle = CssStyle {
    cssRule(" .placeholder") {
        Modifier
            .styleModifier {
                property("-webkit-animation", "placeholder-glow 2s ease-in-out infinite")
            }
            .animation(
                PlaceholderGlow.toAnimation(
                    duration = 2.s,
                    timingFunction = AnimationTimingFunction.EaseInOut,
                    iterationCount = AnimationIterationCount.Infinite
                )
            )
    }
}
val PlaceholderStyle = CssStyle.base {
    Modifier
        .display(DisplayStyle.InlineBlock)
        .minHeight(1.em)
        .verticalAlign(VerticalAlign.Middle)
        .cursor(Cursor.Wait)
        .backgroundColor(BackgroundColor.CurrentColor)
        .opacity(0.5)
}

val BgWarningStyle = CssStyle.base {
    Modifier
        .backgroundColor(Color.rgba(255, 193, 7, 1f))
}

val PlaceholderGlow = Keyframes {
    50.percent {
        Modifier
            .opacity(0.2)
    }
}

val SpinnerBorderStyle = CssStyle.base {
    Modifier
        .display(DisplayStyle.InlineBlock)
        .verticalAlign((-0.125).em)
        .styleModifier {
            property("border", ".25em solid currentColor")
        }
        .borderRight { color(Colors.Transparent) }
        .borderRadius(50.percent)
        .styleModifier {
            property("-webkit-animation", ".75s linear infinite spinner-border")
        }
        .animation(
            SpinnerBorder.toAnimation(
                duration = 0.75.s,
                timingFunction = AnimationTimingFunction.Linear,
                iterationCount = AnimationIterationCount.Infinite
            )
        )
        .size(2.cssRem)
}
val SpinnerBorder = Keyframes {
    100.percent {
        Modifier
            .transform { rotate(360.deg) }
    }
}

val VisuallyHiddenStyle = CssStyle.base {
    Modifier
        .position(Position.Absolute)
        .padding(0.px)
        .margin((-1).px)
        .overflow(Overflow.Hidden)
        //.clip(Clip.rect(0.px, 0.px, 0.px, 0.px))
        .whiteSpace(WhiteSpace.NoWrap)
        .border(0.px)
        .size(1.px)
}

val SpinnerGrowStyle = CssStyle.base {
    Modifier
        .display(DisplayStyle.InlineBlock)
        .verticalAlign((-0.125).em)
        .backgroundColor(Colors.Blue)
        .borderRadius(50.percent)
        .opacity(0)
        .styleModifier {
            property("-webkit-animation", ".75s linear infinite spinner-grow")
        }
        .animation(
            SpinnerGrow.toAnimation(
                duration = 0.75.s,
                timingFunction = AnimationTimingFunction.Linear,
                iterationCount = AnimationIterationCount.Infinite
            )
        )
        .size(2.cssRem)
}
val SpinnerGrow = Keyframes {
    0.percent {
        Modifier
            .transform { scale(0) }
    }
    50.percent {
        Modifier
            .opacity(1)
            .styleModifier { property("transform", "none") }
    }
}

