package org.example.porfolio.styles

import com.varabyte.kobweb.compose.css.AnimationIterationCount
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.css.TextAlign
import com.varabyte.kobweb.compose.css.Transition
import com.varabyte.kobweb.compose.css.TransitionTimingFunction
import com.varabyte.kobweb.compose.css.VerticalAlign
import com.varabyte.kobweb.compose.css.Visibility
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Color
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.styleModifier
import com.varabyte.kobweb.silk.init.SilkStylesheet
import com.varabyte.kobweb.silk.init.registerStyleBase
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.style.animation.Keyframes
import com.varabyte.kobweb.silk.style.animation.toAnimation
import com.varabyte.kobweb.silk.style.base
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import org.jetbrains.compose.web.css.AnimationTimingFunction
import org.jetbrains.compose.web.css.DisplayStyle
import org.jetbrains.compose.web.css.LineStyle
import org.jetbrains.compose.web.css.Position
import org.jetbrains.compose.web.css.cssRem
import org.jetbrains.compose.web.css.deg
import org.jetbrains.compose.web.css.em
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.css.s

val Spinner = Keyframes {
    100.percent {
        Modifier
            .styleModifier { property("-webkit-transform", "rotate(360deg)") }
            .transform { rotate(360.deg) }
    }
}

fun SilkStylesheet.registerCustomStyles() {
    registerStyleBase(".page-loading") {
        Modifier
            .position(Position.Absolute)
            .top(0.px)
            .right(0.px)
            .bottom(0.px)
            .left(0.px)
            .styleModifier {
                property("-webkit-transition", "all .4s .2s ease-in-out")
            }
            .transition(Transition.of("all", 0.4.s, TransitionTimingFunction.EaseInOut, 0.2.s))
            .backgroundColor(Color.rgb(0x1f1b2d))
            .opacity(0)
            .visibility(Visibility.Hidden)
            .zIndex(9999)
            .fillMaxSize()
    }
    registerStyle(".page-loading.active") {
        base {
            Modifier
                .opacity(1)
                .visibility(Visibility.Visible)
        }
        cssRule(" > .page-loading-inner") {
            Modifier
                .opacity(1)
        }
    }
    registerStyle(".page-loading-inner") {
        base {
            Modifier
                .position(Position.Absolute)
                .top(50.percent)
                .left(0.px)
                .fillMaxWidth()
                .textAlign(TextAlign.Center)
                .styleModifier {
                    property("-webkit-transform", "translateY(-50%)")
                    property("-webkit-transition", "opacity .2s ease-in-out")
                }
                .transform { translateY((-50).percent) }
                .transition(Transition.of("opacity", 0.2.s, TransitionTimingFunction.EaseInOut))
                .opacity(0)
        }
        cssRule(" > span") {
            Modifier
                .display(DisplayStyle.Block)
                .fontSize(1.cssRem)
                .fontWeight(FontWeight.Normal)
                .color(Color.rgb(0xffffff))
        }
    }
    registerStyleBase(".page-spinner") {
        Modifier
            .display(DisplayStyle.InlineBlock)
            .verticalAlign(VerticalAlign.TextBottom)
            .border(0.15.em, LineStyle.Solid, Color.rgb(0x9691a4))
            .borderRight { color(Colors.Transparent) }
            .borderRadius(50.percent)
            .styleModifier {
                property("-webkit-animation", "spinner .75s linear infinite")
            }
            .animation(
                Spinner.toAnimation(colorMode = ColorMode.DARK,
                    duration = 0.75.s,
                    timingFunction = AnimationTimingFunction.Linear,
                    iterationCount = AnimationIterationCount.Infinite
                )
            )
            .size(2.75.cssRem)
            .margin(bottom = 0.75.cssRem)
    }
    //registerKeyframes("spinner") { Spinner }
}
