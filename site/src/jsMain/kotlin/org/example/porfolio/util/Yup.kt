@file:JsModule("yup")
@file:JsNonModule

package org.example.porfolio.util
import kotlin.js.Promise

@JsName("object")
external fun yupObject(schema: dynamic): ObjectSchema<dynamic>
external fun setLocale(locale: YupLocale)
external fun string(): StringSchema
external fun number(): NumberSchema
external fun date(): DateSchema

external interface ObjectSchema<T> {
    fun validate(value: dynamic, options : dynamic): Promise<T>
    fun cast(value: dynamic): T
}

external interface StringSchema {
    fun required(): StringSchema
    fun email(): StringSchema
    fun url(): StringSchema
    fun oneOf(values: Array<String>): StringSchema
    fun nullable(): StringSchema
    fun defined() : StringSchema
}

external interface NumberSchema {
    fun required(): NumberSchema
    fun positive(): NumberSchema
    fun integer(): NumberSchema
}

external interface DateSchema {
    fun default(factory: () -> dynamic): DateSchema
}

external interface MixedLocale {
    var required: String
    var notType: String
    var oneOf: String
    var notOneOf: String
}

external interface StringLocale {
    var email: String
    var url: String
    var min: String
    var max: String
    var required: String
}

external interface NumberLocale {
    var min: String
    var max: String
    var integer: String
    var positive: String
    var negative: String
}

external interface DateLocale {
    var min: String
    var max: String
}

external interface YupLocale {
    var mixed: MixedLocale
    var string: StringLocale
    var number: NumberLocale
    var date: DateLocale
}