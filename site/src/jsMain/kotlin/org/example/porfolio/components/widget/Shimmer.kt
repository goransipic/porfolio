package org.example.porfolio.components.widget

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.varabyte.kobweb.compose.css.AnimationIterationCount
import com.varabyte.kobweb.compose.css.BackgroundPosition
import com.varabyte.kobweb.compose.css.BackgroundSize
import com.varabyte.kobweb.compose.css.CSSPosition
import com.varabyte.kobweb.compose.css.ObjectFit
import com.varabyte.kobweb.compose.css.Overflow
import com.varabyte.kobweb.compose.css.Transition
import com.varabyte.kobweb.compose.css.functions.LinearGradient
import com.varabyte.kobweb.compose.css.functions.linearGradient
import com.varabyte.kobweb.compose.dom.ref
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Color
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.animation
import com.varabyte.kobweb.compose.ui.modifiers.backgroundColor
import com.varabyte.kobweb.compose.ui.modifiers.backgroundImage
import com.varabyte.kobweb.compose.ui.modifiers.backgroundPosition
import com.varabyte.kobweb.compose.ui.modifiers.backgroundSize
import com.varabyte.kobweb.compose.ui.modifiers.borderRadius
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxSize
import com.varabyte.kobweb.compose.ui.modifiers.height
import com.varabyte.kobweb.compose.ui.modifiers.left
import com.varabyte.kobweb.compose.ui.modifiers.objectFit
import com.varabyte.kobweb.compose.ui.modifiers.opacity
import com.varabyte.kobweb.compose.ui.modifiers.overflow
import com.varabyte.kobweb.compose.ui.modifiers.position
import com.varabyte.kobweb.compose.ui.modifiers.top
import com.varabyte.kobweb.compose.ui.modifiers.transition
import com.varabyte.kobweb.compose.ui.modifiers.width
import com.varabyte.kobweb.silk.components.graphics.Image
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.style.animation.Keyframes
import com.varabyte.kobweb.silk.style.toModifier
import org.jetbrains.compose.web.css.AnimationTimingFunction
import org.jetbrains.compose.web.css.CSSLengthValue
import org.jetbrains.compose.web.css.Position
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.css.s

val ShimmerStyle = CssStyle {
    base {
        Modifier
            .backgroundImage(
                linearGradient(LinearGradient.Direction.ToRight) {
                    add(Color.rgb(0xeeeeee), 0.percent)
                    add(Color.rgb(0xdddddd), 20.percent)
                    add(Color.rgb(0xeeeeee), 40.percent)
                    add(Color.rgb(0xeeeeee), 100.percent)
                }
            )
            .backgroundSize(BackgroundSize.of(300.px, 200.px))
            .animation(
                Shimmer.toAnimation(
                    duration = 2.s,
                    iterationCount = AnimationIterationCount.Infinite
                ),
            )
    }

}

val Shimmer = Keyframes {
    0.percent {
        Modifier
            .backgroundPosition(BackgroundPosition.of(CSSPosition((-800).px, 0.px)))
    }
    100.percent {
        Modifier
            .backgroundPosition(BackgroundPosition.of(CSSPosition(800.px, 0.px)))
    }
}


@Composable
fun ShimmerImage(
    src: String,
    modifier: Modifier = Modifier,
    width: CSSLengthValue = 300.px,
    height: CSSLengthValue = 200.px,
    alt: String = ""
) {
    var loaded by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .width(width)
            .height(height)
            //.borderRadius(if (loaded) 0.px else 12.px)
            .position(Position.Relative)
            .overflow(Overflow.Hidden)
            //.backgroundColor(if (loaded) Colors.Transparent else Color.rgb(238, 238, 238)).then(modifier)
    ) {
        if (!loaded) {
            // Show shimmer background until image is loaded
            Box(
                modifier = ShimmerStyle.toModifier()
                    .backgroundColor(Colors.White)
                    .fillMaxSize()
            )
        }

        Image(
            src = src,
            alt = alt,
            modifier = Modifier
                .width(width)
                .height(height)
                .position(Position.Absolute)
                .top(0.px)
                .left(0.px)
                .opacity(if (loaded) 1 else 0)
                .objectFit(ObjectFit.Cover)
                .transition(
                    Transition.of(
                        "opacity",
                        duration = 0.3.s,
                        timingFunction = AnimationTimingFunction.EaseInOut
                    )
                ),
            ref = ref { element ->
                element.onload = {
                    loaded = true
                }
            }
        )
    }
}