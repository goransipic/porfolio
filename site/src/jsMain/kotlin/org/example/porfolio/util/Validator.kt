@file:JsModule("validator")
@file:JsNonModule

package org.example.porfolio.util

@JsName("default")
external object Validator {
    fun isEmail(input: String): Boolean
    fun isAscii(str: String): Boolean
    fun toDate(input: String): dynamic
    fun isBase64(str: String, options: Base64Options = definedExternally): Boolean
}

external interface Base64Options {
    var urlSafe: Boolean?
    var padding: Boolean?
}

//@JsName("default")
//external fun isEmail(input: String): Boolean


