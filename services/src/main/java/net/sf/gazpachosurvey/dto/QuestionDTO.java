package net.sf.gazpachosurvey.dto;

import java.util.ArrayList;
import java.util.List;

import net.sf.gazpachosurvey.dto.support.AbstractIdentifiableDTO;
import net.sf.gazpachosurvey.dto.support.IdentifiableLocalizable;
import net.sf.gazpachosurvey.types.Language;
import net.sf.gazpachosurvey.types.QuestionType;

public class QuestionDTO extends AbstractIdentifiableDTO implements
        IdentifiableLocalizable<QuestionLanguageSettingsDTO> {
    private static final long serialVersionUID = 2663159055152157679L;

    private Boolean isRequired;

    private Language language;

    private QuestionLanguageSettingsDTO languageSettings;

    private List<QuestionOptionDTO> questionOptions;

    private List<QuestionDTO> subquestions;

    private QuestionType type;

    public QuestionDTO() {
        super();
    }

    public void addQuestionOption(final QuestionOptionDTO questionOption) {
        if (!getQuestionOptions().contains(questionOption)) {
            questionOptions.add(questionOption);
        }
    }

    public void addSubQuestion(final QuestionDTO subQuestion) {
        if (!getSubquestions().contains(subQuestion)) {
            subquestions.add(subQuestion);
        }
    }

    public Boolean getIsRequired() {
        return isRequired;
    }

    @Override
    public Language getLanguage() {
        return language;
    }

    @Override
    public QuestionLanguageSettingsDTO getLanguageSettings() {
        if (languageSettings == null) {
            languageSettings = new QuestionLanguageSettingsDTO();
        }
        return languageSettings;
    }

    public List<QuestionOptionDTO> getQuestionOptions() {
        if (questionOptions == null) {
            questionOptions = new ArrayList<>();
        }
        return questionOptions;
    }

    public List<QuestionDTO> getSubquestions() {
        if (subquestions == null) {
            subquestions = new ArrayList<QuestionDTO>();
        }
        return subquestions;
    }

    public QuestionType getType() {
        return type;
    }

    public Boolean isRequired() {
        return isRequired;
    }

    public void setIsRequired(final Boolean isRequired) {
        this.isRequired = isRequired;
    }

    @Override
    public void setLanguage(final Language language) {
        this.language = language;
    }

    @Override
    public void setLanguageSettings(final QuestionLanguageSettingsDTO languageSettings) {
        this.languageSettings = languageSettings;
    }

    public void setRequired(final Boolean isRequired) {
        this.isRequired = isRequired;
    }

    public void setType(final QuestionType type) {
        this.type = type;
    }

    public static interface Builder {
        Builder answers(List<QuestionOptionDTO> answers);

        QuestionDTO build();

        Builder id(Integer id);

        Builder isRequired(Boolean isRequired);

        Builder language(Language language);

        Builder languageSettings(QuestionLanguageSettingsDTO languageSettings);

        QuestionLanguageSettingsDTO.Builder languageSettingsStart();

        Builder subquestions(List<QuestionDTO> subquestions);

        Builder type(QuestionType type);
    }

    public static class BuilderImpl implements Builder {
        private List<QuestionOptionDTO> answers;
        private Integer id;
        private Boolean isRequired;
        private Language language;
        private QuestionLanguageSettingsDTO languageSettings;
        private List<QuestionDTO> subquestions;
        private QuestionType type;

        @Override
        public Builder answers(final List<QuestionOptionDTO> answers) {
            this.answers = answers;
            return this;
        }

        @Override
        public QuestionDTO build() {
            QuestionDTO questionDTO = new QuestionDTO();
            questionDTO.setId(id);
            questionDTO.isRequired = isRequired;
            questionDTO.type = type;
            questionDTO.language = language;
            questionDTO.subquestions = subquestions;
            questionDTO.questionOptions = answers;
            questionDTO.languageSettings = languageSettings;
            return questionDTO;
        }

        @Override
        public Builder id(final Integer id) {
            this.id = id;
            return this;
        }

        @Override
        public Builder isRequired(final Boolean isRequired) {
            this.isRequired = isRequired;
            return this;
        }

        @Override
        public Builder language(final Language language) {
            this.language = language;
            return this;
        }

        @Override
        public Builder languageSettings(final QuestionLanguageSettingsDTO languageSettings) {
            this.languageSettings = languageSettings;
            return this;
        }

        @Override
        public QuestionLanguageSettingsDTO.Builder languageSettingsStart() {
            return QuestionLanguageSettingsDTO.languageSettingsStart(this);
        }

        @Override
        public Builder subquestions(final List<QuestionDTO> subquestions) {
            this.subquestions = subquestions;
            return this;
        }

        @Override
        public Builder type(final QuestionType type) {
            this.type = type;
            return this;
        }
    }

    public static Builder with() {
        return new BuilderImpl();
    }

}
