package com.deliveryhero.translation.annotation

@Retention(AnnotationRetention.SOURCE)
@Target(AnnotationTarget.CLASS, AnnotationTarget.FILE)
annotation class GenerateTranslationKeys(
    val packageName: String = "com.deliveryhero.translation.generated",
    val className: String = "TranslationKeys",
    val stringsPath: String = "app/src/main/res/values/strings.xml"
)
