package org.example.porfolio.components

import com.varabyte.kobweb.compose.foundation.layout.LayoutScopeMarker
import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.css.AlignItems
import com.varabyte.kobweb.compose.css.CSSLengthNumericValue
import com.varabyte.kobweb.compose.css.JustifyItems
import com.varabyte.kobweb.compose.css.StyleVariable
import com.varabyte.kobweb.compose.dom.ElementRefScope
import com.varabyte.kobweb.compose.dom.registerRefScope
import com.varabyte.kobweb.compose.style.toClassName
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.attrsModifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.styleModifier
import com.varabyte.kobweb.compose.ui.thenIf
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.style.toModifier
import org.jetbrains.compose.web.css.CSSColorValue
import org.jetbrains.compose.web.css.DisplayStyle
import org.jetbrains.compose.web.css.fr
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.Div
import org.w3c.dom.HTMLElement

@LayoutScopeMarker
@Immutable // TODO(#554): Remove annotation after upstream fix
interface BoxScope {
    fun Modifier.align(alignment: Alignment) = attrsModifier {
        classes("${alignment.toClassName()}-self")
    }
}

internal object BoxScopeInstance : BoxScope

object BoxDefaults {
    val ContentAlignment: Alignment = Alignment.TopStart
}

/**
 * Add classes that tell the browser to display this element as a column.
 *
 * This method is public as there may occasionally be cases where users could benefit from using this, but in general
 * you shouldn't reach for this unless you know what you're doing.
 *
 * NOTE: This modifier sets attribute properties and can therefore not be used within CssStyles.
 */
fun Modifier.boxClasses(contentAlignment: Alignment = BoxDefaults.ContentAlignment) =
    this.classNames("kobweb-box", contentAlignment.toClassName())

@Composable
fun BoxCostume(
    modifier: Modifier = Modifier,
    contentAlignment: Alignment? = null,
    ref: ElementRefScope<HTMLElement>? = null,
    content: @Composable BoxScope.() -> Unit = {}
) {
    val other = if (contentAlignment != null) {
        Modifier.boxClasses(contentAlignment)
    } else {
        BoxCostumeStyle.toModifier().classNames("kobweb-box").setVariable(PlaceItemsLG, "center")
    }
    Div(attrs = modifier.then(other).toAttrs()) {
        registerRefScope(ref)
        BoxScopeInstance.content()
    }
}

val PlaceItemsZero by StyleVariable<String>()
val PlaceItemsSM by StyleVariable<String>()
val PlaceItemsMD by StyleVariable<String>()
val PlaceItemsLG by StyleVariable<String>()

val BoxCostumeStyle = CssStyle {
    Breakpoint.ZERO {
        Modifier.thenIf(PlaceItemsZero.value() != "") {
            Modifier.styleModifier { property("place-items", PlaceItemsZero.value()) }
        }
    }
    Breakpoint.SM {
        Modifier.styleModifier {
            property("place-items", PlaceItemsSM.value())
        }
    }
    Breakpoint.MD {
        Modifier.styleModifier {
            property("place-items", PlaceItemsMD.value())
        }
    }
    Breakpoint.LG {
        Modifier.styleModifier {
            property("place-items", PlaceItemsLG.value())
        }
    }
}

enum class BoxAlignment(val cssValue: String) {
    Center("center"),
    TopCenter("start center"),
    TopLeft("start start"),
    BottomRight("end end"),
    MiddleLeft("center start"),
    MiddleRight("center end"),
    BottomCenter("end center")
}
