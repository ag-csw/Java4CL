import static org.junit.Assert.*;

import org.junit.Test;

import cl2.CL;
import api4kbj.API4KB;
import api4kbj.AbstractBasicKnowledgeExpression;
import api4kbj.BasicKnowledgeAssetCanonical;
import api4kbj.KnowledgeExpression;

public class BasicKnowledgeAssetTest {

	@Test
	public void assetShouldConceptualizeItsCanonicalExpression() {
		KnowledgeExpression expression = new AbstractBasicKnowledgeExpression(
				CL.LANG) {
		};
		BasicKnowledgeAssetCanonical asset = new BasicKnowledgeAssetCanonical(
				CL.CL_DEFAULT_ENVIRONMENT, expression);
		assertTrue(API4KB.conceptualizes(asset, expression));
	}

}
