package com.wavez.language_setting.data_source.languages

import com.wavez.language_setting.model.Language

interface LanguageDataSource {

    val languages: List<Language>

    val defaultLanguage: Language

}