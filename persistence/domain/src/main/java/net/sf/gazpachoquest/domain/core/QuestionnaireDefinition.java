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
package net.sf.gazpachoquest.domain.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.MapKeyColumn;
import javax.persistence.MapKeyEnumerated;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import net.sf.gazpachoquest.domain.core.embeddables.QuestionnaireDefinitionLanguageSettings;
import net.sf.gazpachoquest.domain.i18.QuestionnaireDefinitionTranslation;
import net.sf.gazpachoquest.domain.permission.QuestionnaireDefinitionPermission;
import net.sf.gazpachoquest.domain.support.AbstractSecurizableLocalizable;
import net.sf.gazpachoquest.jpa.converter.EntityStatusConverter;
import net.sf.gazpachoquest.jpa.converter.RandomizationStrategyTypeConverter;
import net.sf.gazpachoquest.jpa.converter.RenderingModeConverter;
import net.sf.gazpachoquest.types.EntityStatus;
import net.sf.gazpachoquest.types.Language;
import net.sf.gazpachoquest.types.MailMessageTemplateType;
import net.sf.gazpachoquest.types.RandomizationStrategy;
import net.sf.gazpachoquest.types.RenderingMode;

@Entity
@XmlRootElement
@XmlType(propOrder = { "language", "languageSettings", "questionGroups", "mailTemplates", "translations",
        "welcomeVisible", "progressVisible", "questionGroupInfoVisible", "randomizationStrategy", "questionsPerPage",
        "renderingMode" })
public class QuestionnaireDefinition
        extends
        AbstractSecurizableLocalizable<QuestionnaireDefinitionPermission, QuestionnaireDefinitionTranslation, QuestionnaireDefinitionLanguageSettings> {

    private static final long serialVersionUID = 2560468772707058412L;

    @Convert(converter = EntityStatusConverter.class)
    @XmlTransient
    private EntityStatus status;

    @Embedded
    private QuestionnaireDefinitionLanguageSettings languageSettings;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Language language;

    @OneToMany(mappedBy = "questionnaireDefinition", orphanRemoval = true, fetch = FetchType.LAZY)
    @XmlTransient
    private final Set<Questionnaire> questionnaires = new HashSet<Questionnaire>();

    @OneToMany(mappedBy = "questionnaireDefinition", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @MapKeyEnumerated(EnumType.STRING)
    @MapKeyColumn(name = "language", insertable = false, updatable = false)
    private final Map<Language, QuestionnaireDefinitionTranslation> translations = new HashMap<Language, QuestionnaireDefinitionTranslation>();

    @OneToMany(mappedBy = "questionnaireDefinition", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @OrderColumn(name = "order_in_questionnaire")
    @XmlElementWrapper(name = "question-groups")
    @XmlElement(name = "question-group")
    private final List<QuestionGroup> questionGroups = new ArrayList<QuestionGroup>();

    @OneToMany(mappedBy = "questionnaireDefinition", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @MapKeyEnumerated(EnumType.STRING)
    @MapKeyColumn(name = "type", insertable = false, updatable = false)
    private final Map<MailMessageTemplateType, MailMessageTemplate> mailTemplates = new HashMap<MailMessageTemplateType, MailMessageTemplate>();

    @Column(nullable = false)
    private Boolean welcomeVisible;

    @Column(nullable = false)
    private Boolean progressVisible;

    @Column(nullable = false)
    private Boolean questionGroupInfoVisible;

    @Convert(converter = RandomizationStrategyTypeConverter.class)
    @Column(nullable = false)
    private RandomizationStrategy randomizationStrategy;

    private Integer questionsPerPage;

    @Convert(converter = RenderingModeConverter.class)
    @Column(nullable = false)
    private RenderingMode renderingMode;

    public QuestionnaireDefinition() {
        super();
    }

    @Override
    public Map<Language, QuestionnaireDefinitionTranslation> getTranslations() {
        return Collections.unmodifiableMap(translations);
    }

    public List<QuestionGroup> getQuestionGroups() {
        return Collections.unmodifiableList(questionGroups);
    }

    public void addQuestionGroup(QuestionGroup questionGroup) {
        questionGroups.add(questionGroup);
        questionGroup.setQuestionnairDefinition(this);
    }

    @Override
    public QuestionnaireDefinitionLanguageSettings getLanguageSettings() {
        return languageSettings;
    }

    @Override
    public void setLanguageSettings(QuestionnaireDefinitionLanguageSettings languageSettings) {
        this.languageSettings = languageSettings;
    }

    @Override
    public Language getLanguage() {
        return language;
    }

    @Override
    public void setLanguage(Language language) {
        this.language = language;
    }

    public EntityStatus getStatus() {
        return status;
    }

    public void setStatus(EntityStatus status) {
        this.status = status;
    }

    public Map<MailMessageTemplateType, MailMessageTemplate> getMailTemplates() {
        return Collections.unmodifiableMap(mailTemplates);
    }

    public void addTranslation(Language language, QuestionnaireDefinitionTranslation translation) {
        translation.setQuestionnairDefinition(this);
        translations.put(language, translation);
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

    public RandomizationStrategy getRandomizationStrategy() {
        return randomizationStrategy;
    }

    public void setRandomizationStrategy(RandomizationStrategy randomizationStrategy) {
        this.randomizationStrategy = randomizationStrategy;
    }

    public Integer getQuestionsPerPage() {
        return questionsPerPage;
    }

    public void setQuestionsPerPage(Integer questionsPerPage) {
        this.questionsPerPage = questionsPerPage;
    }

    public void updateInverseRelationships() {
        for (Entry<Language, QuestionnaireDefinitionTranslation> entry : translations.entrySet()) {
            QuestionnaireDefinitionTranslation translation = entry.getValue();
            translation.setQuestionnairDefinition(this);
            translation.setLanguage(entry.getKey());
        }

        for (Entry<MailMessageTemplateType, MailMessageTemplate> entry : mailTemplates.entrySet()) {
            MailMessageTemplate mailMessageTemplate = entry.getValue();
            mailMessageTemplate.setType(entry.getKey());
            mailMessageTemplate.setQuestionnairDefinition(this);
            mailMessageTemplate.updateInverseRelationships();
        }

        for (QuestionGroup questionGroup : questionGroups) {
            questionGroup.setQuestionnairDefinition(this);
            questionGroup.updateInverseRelationships();
        }
    }

    public RenderingMode getRenderingMode() {
        return renderingMode;
    }

    public void setRenderingMode(RenderingMode renderingMode) {
        this.renderingMode = renderingMode;
    }

    public static Builder with() {
        return new Builder();
    }

    public static class Builder {
        private Integer id;
        private EntityStatus status;
        private QuestionnaireDefinitionLanguageSettings languageSettings;
        private Language language;
        private Boolean welcomeVisible;
        private Boolean progressVisible;
        private Boolean questionGroupInfoVisible;
        private RandomizationStrategy randomizationStrategy;
        private Integer questionsPerPage;
        private RenderingMode renderingMode;

        public Builder id(Integer id) {
            this.id = id;
            return this;
        }

        public Builder status(EntityStatus status) {
            this.status = status;
            return this;
        }

        public Builder questionsPerPage(Integer questionsPerPage) {
            this.questionsPerPage = questionsPerPage;
            return this;
        }

        public Builder languageSettings(QuestionnaireDefinitionLanguageSettings languageSettings) {
            this.languageSettings = languageSettings;
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

        public Builder randomizationStrategy(RandomizationStrategy randomizationStrategy) {
            this.randomizationStrategy = randomizationStrategy;
            return this;
        }

        public Builder questionGroupInfoVisible(Boolean questionGroupInfoVisible) {
            this.questionGroupInfoVisible = questionGroupInfoVisible;
            return this;
        }

        public Builder renderingMode(RenderingMode renderingMode) {
            this.renderingMode = renderingMode;
            return this;
        }

        public QuestionnaireDefinition build() {
            QuestionnaireDefinition questionnaireDefinition = new QuestionnaireDefinition();
            questionnaireDefinition.setId(id);
            questionnaireDefinition.status = status;
            questionnaireDefinition.languageSettings = languageSettings;
            questionnaireDefinition.language = language;
            questionnaireDefinition.welcomeVisible = welcomeVisible;
            questionnaireDefinition.progressVisible = progressVisible;
            questionnaireDefinition.questionGroupInfoVisible = questionGroupInfoVisible;
            questionnaireDefinition.randomizationStrategy = randomizationStrategy;
            questionnaireDefinition.questionsPerPage = questionsPerPage;
            questionnaireDefinition.renderingMode = renderingMode;
            return questionnaireDefinition;
        }
    }
}