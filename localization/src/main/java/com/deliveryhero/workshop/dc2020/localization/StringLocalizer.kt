package com.deliveryhero.workshop.dc2020.localization

interface StringLocalizer {

    fun getText(key: String): String

    fun getText(key: String, vararg args: String): String

    companion object : StringLocalizer {

        private var default: StringLocalizer =
            Default

        fun setDefault(localizer: StringLocalizer) {
            default = localizer
        }

        override fun getText(key: String) = default.getText(key)

        override fun getText(key: String, vararg args: String) = default.getText(key, *args)
    }

    private object Default : StringLocalizer {

        override fun getText(key: String) = key

        override fun getText(key: String, vararg args: String) = key
    }
}
