package com.deliveryhero.workshop.dc2020.localization

import android.content.Context
import com.deliveryhero.translation.annotation.GenerateTranslationKeys

@GenerateTranslationKeys(stringsPath = "localization/src/main/res/values/strings.xml")
class ResourceStringLocalizer(private val appContext: Context) : StringLocalizer {

    override fun getText(key: String): String {
        return getText(key) { appContext.getString(it) }
    }

    override fun getText(key: String, vararg args: String): String {
        return getText(key) { appContext.getString(it, *args) }
    }

    private inline fun getText(key: String, transformer: (resId: Int) -> String): String {
        val resId = appContext.resources.getIdentifier(key, "string", appContext.packageName)
        return transformer(resId)
    }
}
