import static org.junit.Assert.*;
import graphenvironment.GraphImmutableLanguageEnvironment;
import graphenvironment.GraphImmutableLanguageEnvironment.*;

import org.junit.Test;

import api4kbj.AbstractBasicKnowledgeExpression;
import api4kbj.AbstractKRRLanguage;
import api4kbj.KRRLanguage;
import api4kbj.KRRLogic;
import api4kbj.KnowledgeExpression;
import api4kbj.KnowledgeSourceLevel;
import api4kbj.LanguageMapping;


public class GraphImmutableLanguageEnvironmentTest {

	@Test
	public void test() {
		AbstractKRRLanguage lang = new AbstractKRRLanguage(
				"Language One", new KRRLogic() {

					@Override
					public String name() {
						return "Logic A";
					}
				}) {

					@Override
					public Class<? extends KnowledgeExpression> asClass() {
						return KnowledgeExpression.class;
					}
		};
		
		LanguageMapping<KnowledgeExpression, KnowledgeExpression> idMap = new LanguageMapping<KnowledgeExpression, KnowledgeExpression>(){

			@Override
			public KRRLanguage startLanguage() {
				return lang;
			}

			public KRRLanguage endLanguage(){
				return lang;
			}


			@Override
			public KnowledgeExpression f(KnowledgeExpression arg) {
				return arg;
			}

			@Override
			public Class<KnowledgeExpression> startClass() {
				return KnowledgeExpression.class;
			}

			@Override
			public Class<KnowledgeExpression> endClass() {
				return KnowledgeExpression.class;
			}


		};
		
		GraphImmutableLanguageEnvironment env = new GraphImmutableLanguageEnvironment(lang);
		
		assertTrue(env.containsMember(lang));
		
		Builder builder = GraphImmutableLanguageEnvironment.init(lang);
		builder.addMapping(idMap);
		GraphImmutableLanguageEnvironment env2 = builder.build();

		assertTrue(env2.containsMapping(idMap));
		
		KnowledgeExpression expression = new AbstractBasicKnowledgeExpression(lang){};
		
		assertEquals(env2.apply(expression, lang), expression);

	
	}

}
