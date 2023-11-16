package com.wavez.language_setting.repositories

import android.content.Context
import com.wavez.language_setting.model.Language
import com.wavez.language_setting.data_source.languages.LanguageDataSource
import com.wavez.language_setting.data_source.shared.LanguageShared

class SettingLanguageRepositoryImpl(
    private val context: Context,
    private val languageDataSource: LanguageDataSource,
    private val languageShared: LanguageShared
) : SettingLanguageRepository {
    override fun getLanguages(): List<Language> {
        val languageConfig = languageConfig
        val resourcesLanguages = languageDataSource.languages
        val foundLanguage = resourcesLanguages.find { it.language == languageConfig }
        val languages = ArrayList<Language>()
        if (foundLanguage == null) {
            val defaultLanguage = if (languageConfig.isNotEmpty()) {
                languageDataSource.defaultLanguage.copy(language = languageConfig)
            } else {
                languageDataSource.defaultLanguage
            }
            languages.add(defaultLanguage)
            languages.addAll(resourcesLanguages)
        } else {
            languages.addAll(resourcesLanguages)
            languages.remove(foundLanguage)
            languages.add(0, foundLanguage)
        }
        return languages
    }

    override var languageConfig: String
        get() {
            val languageConfig = languageShared.languageConfig
            if (languageConfig.isEmpty()) {
                try {
                    return context.resources.configuration.locales.get(0).language
                } catch (ignore: Exception) {
                }
            }
            return ""
        }
        set(value) {
            languageShared.languageConfig = value
        }
}