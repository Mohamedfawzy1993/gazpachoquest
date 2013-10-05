package net.sf.gazpachosurvey.domain.support;

import net.sf.gazpachosurvey.types.Language;

public interface Translation<LS extends LanguageSettings> {
    LS getLanguageSettings();

    void setLanguageSettings(LS languageSettings);

    Language getLanguage();

    void setLanguage(Language language);

}