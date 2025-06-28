package org.example.porfolio.components

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.backgroundColor
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.modifiers.maxWidth
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.styleModifier
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.style.toAttrs
import com.varabyte.kobweb.silk.style.toModifier
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.Div


@Composable
fun Container(content: @Composable () -> Unit) {
    Div(
        attrs = ContainerStyle.toModifier().padding(12.px).toAttrs()

    ) {
        content()

    }
}

val ContainerStyle = CssStyle {
    base {
        Modifier
            .fillMaxWidth()
            .styleModifier {
                property("margin", "auto")
                //property("padding-left", "0px")
                //property("padding-right", "0px")
            } // Correct way to apply margin: auto
    }
    Breakpoint.SM {
        Modifier.maxWidth(540.px)
    }
    Breakpoint.MD {
        Modifier.maxWidth(720.px)
    }
    Breakpoint.LG {
        Modifier.maxWidth(960.px)
    }
    Breakpoint.XL {
        Modifier.maxWidth(1140.px)
    }
}