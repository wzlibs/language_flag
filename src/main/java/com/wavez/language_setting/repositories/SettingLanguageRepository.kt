package com.wavez.language_setting.repositories

import com.wavez.language_setting.model.Language

interface SettingLanguageRepository {
    fun getLanguages(): List<Language>
    var languageConfig: String
}