package net.sf.gazpachoquest.questionnaires.resource;

import javax.inject.Inject;

import net.sf.gazpachoquest.api.QuestionnaireResource;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.StringAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.descriptor.api.Descriptors;
import org.jboss.shrinkwrap.descriptor.api.beans10.BeansDescriptor;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @see https://community.jboss.org/message/729492a
 * @see http://java.dzone.com/articles/injecting-string-resource
 * @author antoniomaria
 * 
 */
@RunWith(Arquillian.class)
@Ignore
public class QuestionnairResourceTest {

    @Inject
    @GazpachoResource
    private QuestionnaireResource questionnaireResource;

    @Deployment
    public static Archive<?> createTestArchive() {
        String beansDescriptor = Descriptors.create(BeansDescriptor.class).exportAsString();
        JavaArchive archive = ShrinkWrap.create(JavaArchive.class, "myarchive.jar")
                .addClasses(QuestionnaireResource.class, ResourceProducer.class, GazpachoResource.class)
                .addAsResource("resources/messages.properties")
                .addAsManifestResource(new StringAsset(beansDescriptor), "beans.xml");
        return archive;
    }

    @Test
    public void listTest() {
        Integer questionnaireId = 58;
        // QuestionnaireDTO questionnair =
        // questionnaireResource.getDefinition(questionnaireId);
        // System.out.println(questionnair.getId() + " " +
        // questionnair.getLanguageSettings().getTitle());
        System.out.println("fin");
    }
}
