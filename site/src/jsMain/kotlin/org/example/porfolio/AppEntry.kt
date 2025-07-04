package org.example.porfolio

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.varabyte.kobweb.browser.storage.setItem
import com.varabyte.kobweb.browser.storage.createStorageKey
import com.varabyte.kobweb.browser.storage.getItem
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxHeight
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxSize
import com.varabyte.kobweb.compose.ui.modifiers.minHeight
import com.varabyte.kobweb.core.App
import com.varabyte.kobweb.core.AppGlobals
import com.varabyte.kobweb.core.isExporting
import com.varabyte.kobweb.silk.SilkApp
import com.varabyte.kobweb.silk.components.layout.Surface
import com.varabyte.kobweb.silk.init.InitSilk
import com.varabyte.kobweb.silk.init.InitSilkContext
import com.varabyte.kobweb.silk.init.registerStyleBase
import com.varabyte.kobweb.silk.style.common.SmoothColorStyle
import com.varabyte.kobweb.silk.style.toModifier
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import com.varabyte.kobweb.silk.theme.colors.palette.button
import com.varabyte.kobweb.silk.theme.colors.systemPreference
import kotlinx.browser.document
import kotlinx.browser.localStorage
import org.example.porfolio.styles.registerCustomStyles
import org.example.porfolio.util.Res
import org.jetbrains.compose.web.css.vh
import org.w3c.dom.HTMLElement

val COLOR_MODE_KEY = ColorMode.entries.createStorageKey("app:colorMode")

@InitSilk
fun initStyles(ctx: InitSilkContext) {
    ctx.stylesheet.registerCustomStyles()
    ctx.config.apply {
        initialColorMode = localStorage.getItem(COLOR_MODE_KEY) ?: ColorMode.systemPreference

        // Script which runs at load time that needs to be kept in sync with `initialColorMode` above. This code checks
        // if the user's local color mode preference is different from what was exported by Kobweb, replacing it if
        // different to prevent a flash of color after the page loads.
        if (AppGlobals.isExporting) {
            document.head!!.appendChild(
                document.createElement("script").apply {
                    textContent = """
                        {
                            const storedColor = localStorage.getItem('${COLOR_MODE_KEY.name}'); // 'LIGHT', 'DARK', or null
                            const desiredColor = storedColor
                                ? `silk-${'$'}{storedColor.toLowerCase()}`
                                : (window.matchMedia('(prefers-color-scheme: dark)').matches ? 'silk-dark' : 'silk-light');
                            const oppositeColor = desiredColor === 'silk-dark' ? 'silk-light' : 'silk-dark';
                            document.documentElement.classList.replace(oppositeColor, desiredColor);
                        }
                        """.trimIndent()
                })
        }
        if (AppGlobals.isExporting) {
            val htmlString = """
                <!-- Page loading spinner-->
                <div class="page-loading active">
                    <div class="page-loading-inner">
                        <div class="page-spinner"></div><span>Loading...</span>
                    </div>
                </div>
            """.trimIndent()
            document.body?.insertAdjacentHTML("afterbegin", htmlString);
            val element = document.getElementById("_kobweb-root") as? HTMLElement
            /*element?.apply {
               style.display = "none"
                style.position = "relative" // Required for z-index to work
                style.zIndex = "0"       // Bring element to front
            }*/

        }
        if(!AppGlobals.isExporting){
            document.head!!.appendChild(
                document.createElement("script").apply {
                    textContent = """
                        {
                            (function () {
                                window.onload = function () {
                                console.log("Hello, world!");
                                var preloader = document.querySelector('.page-loading');
                                preloader.classList.remove('active');
                                
                                setTimeout(function () {
                                    preloader.remove();
                                }, 2000);
                                };
                                })();
                            }
                        """.trimIndent()
                })
        }

    }

    ctx.stylesheet.registerStyleBase("html, body") { Modifier.fillMaxHeight() }
}

@App
@Composable
fun MyApp(content: @Composable () -> Unit) {
    SilkApp {
        val colorMode = ColorMode.current
        LaunchedEffect(colorMode) {
            localStorage.setItem(COLOR_MODE_KEY, colorMode)
        }
        Surface(SmoothColorStyle.toModifier().fillMaxSize()) {
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