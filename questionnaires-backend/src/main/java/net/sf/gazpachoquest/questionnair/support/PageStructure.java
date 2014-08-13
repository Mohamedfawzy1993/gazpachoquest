package net.sf.gazpachoquest.questionnair.support;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.sf.gazpachoquest.domain.core.QuestionGroup;

public class PageStructure {

    private PageMetadataStructure metadata;

    private final List<QuestionGroup> questionGroups = new ArrayList<>();

    public PageStructure() {
        super();
    }

    public List<Integer> getQuestionsId() {
        List<Integer> allQuestionsId = new ArrayList<>();
        for (QuestionGroup questionGroup : questionGroups) {
            List<Integer> questionIds = questionGroup.getQuestionsId();
            allQuestionsId.addAll(questionIds);
        }
        return Collections.unmodifiableList(allQuestionsId);
    }

    public void addQuestionGroup(QuestionGroup questionGroup) {
        questionGroups.add(questionGroup);
    }

    public PageMetadataStructure getMetadata() {
        return metadata;
    }

    public void setMetadata(PageMetadataStructure metadata) {
        this.metadata = metadata;
    }

    public List<QuestionGroup> getQuestionGroups() {
        return Collections.unmodifiableList(questionGroups);
    }

}
