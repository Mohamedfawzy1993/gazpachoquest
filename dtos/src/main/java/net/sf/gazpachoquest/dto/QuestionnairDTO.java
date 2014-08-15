/*******************************************************************************
 * Copyright (c) 2014 antoniomariasanchez at gmail.com.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 * 
 * Contributors:
 *     antoniomaria - initial API and implementation
 ******************************************************************************/
package net.sf.gazpachoquest.dto;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import net.sf.gazpachoquest.dto.support.AbstractIdentifiableDTO;
import net.sf.gazpachoquest.types.Language;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel(value = "QuestionnairDTO", description = "Questionnair resource representation")
public class QuestionnairDTO extends AbstractIdentifiableDTO {

    private static final long serialVersionUID = 1067156345771109606L;

    @ApiModelProperty(value = "Default language", required = true)
    private Language language;

    @ApiModelProperty(value = "Others languages supported", required = false)
    private final Set<Language> supportedLanguages = new HashSet<Language>();

    private QuestionnairDefinitionLanguageSettingsDTO languageSettings;

    private Boolean welcomeVisible;

    private Boolean progressVisible;

    private Boolean questionGroupInfoVisible;

    public QuestionnairDTO() {
        super();
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public Set<Language> getSupportedLanguages() {
        return Collections.unmodifiableSet(supportedLanguages);
    }

    public void addSupportedLanguage(Language language) {
        supportedLanguages.add(language);
    }

    public QuestionnairDefinitionLanguageSettingsDTO getLanguageSettings() {
        return languageSettings;
    }

    public void setLanguageSettings(QuestionnairDefinitionLanguageSettingsDTO languageSettings) {
        this.languageSettings = languageSettings;
    }

    public Boolean isWelcomeVisible() {
        return welcomeVisible;
    }

    public void setWelcomeVisible(Boolean welcomeVisible) {
        this.welcomeVisible = welcomeVisible;
    }

    public Boolean isProgressVisible() {
        return progressVisible;
    }

    public void setProgressVisible(Boolean progressVisible) {
        this.progressVisible = progressVisible;
    }

    public Boolean isQuestionGroupInfoVisible() {
        return questionGroupInfoVisible;
    }

    public void setQuestionGroupInfoVisible(Boolean questionGroupInfoVisible) {
        this.questionGroupInfoVisible = questionGroupInfoVisible;
    }

    public static Builder with() {
        return new Builder();
    }

    public static class Builder {
        private Integer id;
        private Language language;
        private QuestionnairDefinitionLanguageSettingsDTO languageSettings;
        private Boolean welcomeVisible;
        private Boolean progressVisible;
        private Boolean questionGroupInfoVisible;

        public Builder id(Integer id) {
            this.id = id;
            return this;
        }

        public Builder language(Language language) {
            this.language = language;
            return this;
        }

        public Builder welcomeVisible(Boolean welcomeVisible) {
            this.welcomeVisible = welcomeVisible;
            return this;
        }

        public Builder progressVisible(Boolean progressVisible) {
            this.progressVisible = progressVisible;
            return this;
        }

        public Builder questionGroupInfoVisible(Boolean questionGroupInfoVisible) {
            this.questionGroupInfoVisible = questionGroupInfoVisible;
            return this;
        }

        public Builder languageSettings(QuestionnairDefinitionLanguageSettingsDTO languageSettings) {
            this.languageSettings = languageSettings;
            return this;
        }

        public QuestionnairDTO build() {
            QuestionnairDTO questionnairDTO = new QuestionnairDTO();
            questionnairDTO.setId(id);
            questionnairDTO.language = language;
            questionnairDTO.languageSettings = languageSettings;
            questionnairDTO.welcomeVisible = welcomeVisible;
            questionnairDTO.progressVisible = progressVisible;
            questionnairDTO.questionGroupInfoVisible = questionGroupInfoVisible;
            return questionnairDTO;
        }
    }
}
