package net.sf.gazpachosurvey.services;

import static org.fest.assertions.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.sf.gazpachosurvey.dto.AnswerDTO;
import net.sf.gazpachosurvey.dto.LabelDTO;
import net.sf.gazpachosurvey.dto.LabelSetDTO;
import net.sf.gazpachosurvey.dto.PageDTO;
import net.sf.gazpachosurvey.dto.ParticipantDTO;
import net.sf.gazpachosurvey.dto.QuestionDTO;
import net.sf.gazpachosurvey.dto.SurveyDTO;
import net.sf.gazpachosurvey.dto.SurveyRunningDTO;
import net.sf.gazpachosurvey.types.Language;
import net.sf.gazpachosurvey.types.QuestionType;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("postgres")
@ContextConfiguration(locations = { "classpath:/jpa-context.xml", "classpath:/services-context.xml" })
/*
 * @TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
 * DirtiesContextTestExecutionListener.class,
 * TransactionalTestExecutionListener.class, DbUnitTestExecutionListener.class
 * })
 * 
 * @DatabaseSetup("SurveyRepositoryTest-dataset.xml")
 */public class SurveyServiceTest {

    @Autowired
    private SurveyService surveyService;

    @Autowired
    private PageService pageService;

    @Autowired
    private LabelSetService labelSetService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private ParticipantService participantService;

    @Autowired
    private SurveyRunningService surveyRunningService;

    @Test
    public void addSurveyTest() {
        SurveyDTO survey = SurveyDTO
                .with()
                .language(Language.EN)
                .surveyLanguageSettingsStart()
                .title("Slightly Trickier Sample Survey")
                .description(
                        "<p>This is a <strong><em>sample survey</em></strong> designed for testing GazpachoSurvey.</p><p>One of the first things you'll need to do, in order to make this work properly, is to fix up the &quot;reference to previous answers&quot; codes in the first two questions of Group 2.  These questions contain (INSERTANS:1X2X3) code that needs the numbers changed to match questions 1 and 3 of Group 1. In order to find this number out, browse to question 1 of Group 1, and then copy the code from the URL bar in your web browser, starting from the number after &quot;SID=&quot;</p><p><strong><em>IE:</em></strong> 29975&amp;gid=2&amp;qid=4</p><p>Then, replace the &quot;&amp;gid=&quot; with an X, and replace the &quot;&amp;qid=&quot; with an X. So you should have:</p><p>29975X2X4</p><p>Then, you can replace the number in the curly brackets of question 1, group 2, so that it says:</p><p>(INSERTANS:29975X2X4)</p><p>Do the same with the other codes in the questions.</p><p>More information on using the answers to previous questions in your questions is available in the documentation.</p>")
                .welcomeText("Thank you for taking the time to participate in this survey.")
                .surveyLanguageSettingsEnd().build();

        survey = surveyService.save(survey);
        assertThat(survey.getId()).isNotNull();

        PageDTO page = PageDTO.with().pageLanguageSettingsStart().title("Page 1").pageLanguageSettingsEnd().build();
        PageDTO page1 = pageService.addPage(survey.getId(), page);
        assertThat(page1.getId()).isNotNull();

        page = PageDTO.with().pageLanguageSettingsStart().title("Page 2").pageLanguageSettingsEnd().build();
        PageDTO page2 = pageService.addPage(survey.getId(), page);
        assertThat(page2.getId()).isNotNull();

        page = PageDTO.with().pageLanguageSettingsStart().title("Page 3").pageLanguageSettingsEnd().build();
        PageDTO page3 = pageService.addPage(survey.getId(), page);
        assertThat(page3.getId()).isNotNull();

        LabelSetDTO labelSet = LabelSetDTO.with().language(Language.EN).name("Feelings").build();
        labelSet = labelSetService.save(labelSet);

        LabelDTO label = LabelDTO.with().title("Agree strongly").build();
        labelSetService.addLabel(labelSet.getId(), label);

        label = LabelDTO.with().title("Agree somewhat").build();
        labelSetService.addLabel(labelSet.getId(), label);

        label = LabelDTO.with().title("Neither agree nor disagree").build();
        labelSetService.addLabel(labelSet.getId(), label);

        label = LabelDTO.with().title("Disagree somewhat").build();
        labelSetService.addLabel(labelSet.getId(), label);

        label = LabelDTO.with().title("Disagree strongly").build();
        labelSetService.addLabel(labelSet.getId(), label);

        LabelSetDTO winner = labelSetService.findOne(1);

        // 1
        QuestionDTO question = QuestionDTO.with().type(QuestionType.S).language(Language.EN).title("What is your name?").isRequired(true)
                .build();
        Integer questionId = questionService.addQuestion(page1.getId(), question);
        // 2
        question = QuestionDTO.with().type(QuestionType.N).language(Language.EN).title("And for our records, specifically how old are you?")
                .isRequired(true).build();
        questionId = questionService.addQuestion(page1.getId(), question);

        // 3
        question = QuestionDTO.with().type(QuestionType.L).language(Language.EN).title("What is your age group?").isRequired(true).build();
        
        question.addAnswer( AnswerDTO.with().title("0-14 years").build());
        question.addAnswer( AnswerDTO.with().title("15-19 years").build());
        question.addAnswer( AnswerDTO.with().title("20-24 years").build());
        question.addAnswer( AnswerDTO.with().title("25-29 years").build());
        question.addAnswer( AnswerDTO.with().title("30-34 years").build());
        question.addAnswer( AnswerDTO.with().title("35-39 years").build());
        question.addAnswer( AnswerDTO.with().title("40-44 years").build());
        question.addAnswer( AnswerDTO.with().title("45-49 years").build());
        question.addAnswer( AnswerDTO.with().title("50-54 years").build());
        question.addAnswer( AnswerDTO.with().title("55-59 years").build());
        question.addAnswer( AnswerDTO.with().title("60-64 years").build());
        question.addAnswer( AnswerDTO.with().title("65-69 years").build());
        question.addAnswer( AnswerDTO.with().title("70-74 years").build());
        question.addAnswer( AnswerDTO.with().title("75-79 years").build());
        question.addAnswer( AnswerDTO.with().title("80 and over").build());
        questionId = questionService.addQuestion(page1.getId(), question);

        // 4
        question = QuestionDTO.with().type(QuestionType.T).language(Language.EN)
                .title("Please tell us a little about yourself. What was your first job, and did you enjoy it?")
                .isRequired(true).build();
        questionId = questionService.addQuestion(page2.getId(), question);

        // 5
        question = QuestionDTO.with().type(QuestionType.L).language(Language.EN).
                title("Given your extraordinary age, how do you find using this survey tool?").isRequired(true)
                .build();
        question.addAnswer( AnswerDTO.with().title("Very difficult to read, my eyesight is dim")
                .build());
        question.addAnswer( AnswerDTO.with().title("Easy, my carer is doing it all for me").build());
        question.addAnswer(
                AnswerDTO.with().title("How <b><i>dare</i></b> you insult me like that! I'm YOUNG").build());

        questionId = questionService.addQuestion(page2.getId(), question);
        
        // 6
        question = QuestionDTO
                .with()
                .type(QuestionType.L).
language(Language.EN)
                .title("<font size='+2'><br />&nbsp;<br />Which of these ads makes you want to find out more?<br />&nbsp;<br /></font>")
                .isRequired(true).build();
        questionId = questionService.addQuestion(page3.getId(), question);

        // 7
        question = QuestionDTO
                .with().
                type(QuestionType.L)
                .language(Language.EN)
                .title("Please have a good look at this ad, and then complete the questions below.<br /><img src='http://www.aptigence.com.au/images/lawyer1.jpg' border='1'>")
                .isRequired(true).build();

        List<QuestionDTO> subquestions = new ArrayList<>();
        subquestions.add(QuestionDTO.with().title("This ad suggests the lawyer is on my side, not his own").build());
        subquestions.add(QuestionDTO.with()
                .title("This ad suggests that the lawyer is interested in a life of frugal community service").build());
        subquestions.add(QuestionDTO.with().title("This ad would be enough to get me to hire this lawyer").build());
        subquestions.add(QuestionDTO.with().title("This ad gives me confidence in the lawyers experience").build());
        question.setSubquestions(subquestions);

        question.setPage(page3);
        question.setSurvey(survey);
        
        question.addAnswer(AnswerDTO.with().title("Agree strongly").build());
        question.addAnswer(AnswerDTO.with().title("Agree somewhat").build());
        question.addAnswer(AnswerDTO.with().title("Neither agree nor disagree").build());
        question.addAnswer(AnswerDTO.with().title("Disagree somewhat").build());
        question.addAnswer(AnswerDTO.with().title("Agree strongly").build());
        question.addAnswer(AnswerDTO.with().title("Disagree strongly").build());
        
        question = questionService.save(question);

        ParticipantDTO tyrion = ParticipantDTO.with().firstname("Tyrion").lastname("Lannister")
                .email("tyrion.lannister@kingslanding.net").build();
         tyrion = participantService.save(tyrion);

        ParticipantDTO jon = ParticipantDTO.with().firstname("Jon").lastname("Snow").email("jon.snow@nightswatch.net")
                .build();

        ParticipantDTO arya = ParticipantDTO.with().firstname("Arya").lastname("Stark")
                .email("arya.stark@winterfell.net").build();

        ParticipantDTO catelyn = ParticipantDTO.with().firstname("Catelyn").lastname("Stark")
                .email("catelyn.stark@winterfell.net").build();

        Set<ParticipantDTO> participants = new HashSet<>();
        participants.add(tyrion);
        participants.add(arya);
        participants.add(catelyn);
        participants.add(jon);
        SurveyRunningDTO surveyRunning = SurveyRunningDTO.with().name("my first running").participants(participants)
                .build();

        surveyRunningService.save(surveyRunning);

        System.out.println("fin" + winner.getName());

    }
    
    @Test
    public void findOneTest(){
       SurveyDTO survey = surveyService.findOne(1);
       
       System.out.println("Questions " + survey.getQuestions());

       survey = surveyService.findOne(1, "SurveyWithQuestions");
       
       System.out.println("Questions " + survey.getQuestions());
    }

}
