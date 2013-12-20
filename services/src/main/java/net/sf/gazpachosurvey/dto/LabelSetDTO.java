package net.sf.gazpachosurvey.dto;

import java.util.ArrayList;
import java.util.List;

import net.sf.gazpachosurvey.dto.support.AbstractIdentifiableDTO;
import net.sf.gazpachosurvey.types.Language;

public class LabelSetDTO extends AbstractIdentifiableDTO {

    private static final long serialVersionUID = -7715245634090394574L;

    private String name;

    private Language language;

    private List<LabelDTO> labels;

    public LabelSetDTO() {
        super();
    }

    public List<LabelDTO> getLabels() {
        if (labels == null) {
            labels = new ArrayList<>();
        }
        return labels;
    }

    public void addLabel(LabelDTO label) {
        if (!getLabels().contains(label)) {
            labels.add(label);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public static Builder with() {
        return new Builder();
    }

    public static class Builder {
        private String name;
        private Language language;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder language(Language language) {
            this.language = language;
            return this;
        }

        public LabelSetDTO build() {
            return new LabelSetDTO(this);
        }
    }

    private LabelSetDTO(Builder builder) {
        name = builder.name;
        language = builder.language;
    }

}
