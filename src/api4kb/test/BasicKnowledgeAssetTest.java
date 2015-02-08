import static org.junit.Assert.*;

import org.junit.Test;

import cl2.CL;
import api4kbj.KnowledgeExpression;
import api4kbja.AbstractBasicKnowledgeExpression;
import api4kbjc.API4KB;
import api4kbjc.BasicKnowledgeAssetCanonical;

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
