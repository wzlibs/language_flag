package com.wavez.language_setting.data_source.shared

import android.content.Context
import android.content.SharedPreferences

class LanguageSharedImpl(private val context: Context) : LanguageShared {

    companion object {
        private const val LANGUAGE_CONFIG = "language_config"
    }

    private val sharedPref: SharedPreferences by lazy {
        context.getSharedPreferences("language_config", Context.MODE_PRIVATE)
    }

    private val editor by lazy { sharedPref.edit() }

    override var languageConfig: String
        get() = sharedPref.getString(LANGUAGE_CONFIG, "") ?: ""
        set(value) {
            if (value.isEmpty()) return
            editor.putString(LANGUAGE_CONFIG, value).apply()
        }

}