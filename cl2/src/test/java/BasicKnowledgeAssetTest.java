import static org.junit.Assert.*;

import org.junit.Test;

import cl2.CL;
import cl2.CLCommentExpression;
import api4kbc.API4KB;
import api4kbc.CanonicalBasicKnowledgeAsset;

public class BasicKnowledgeAssetTest {

	@Test
	public void assetShouldConceptualizeItsCanonicalExpression() {
		CLCommentExpression expression = new CLCommentExpression("This is a comment");
		CanonicalBasicKnowledgeAsset asset = new CanonicalBasicKnowledgeAsset(
				CL.CL_DEFAULT_ENVIRONMENT, expression);
		assertTrue(API4KB.conceptualizes(asset, expression));
	}

}
