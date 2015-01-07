import static org.junit.Assert.*;

import org.junit.Test;

import api4kbj.KnowledgeSourceLevel;

public class KnowledgeSourceLevelTest {

	@Test
	public void testOrdinal() {
		assertEquals("The ordinal of ASSET is incorrect", 4, KnowledgeSourceLevel.ASSET.ordinal());
		assertEquals("The ordinal of EXPRESSION is incorrect", 3, KnowledgeSourceLevel.EXPRESSION.ordinal());
		assertEquals("The ordinal of MANIFESTATION is incorrect", 2, KnowledgeSourceLevel.MANIFESTATION.ordinal());
		assertEquals("The ordinal of ENCODING is incorrect", 1, KnowledgeSourceLevel.ENCODING.ordinal());
		assertEquals("The ordinal of IO is incorrect", 0, KnowledgeSourceLevel.IO.ordinal());
	}
	
	@Test
	public void testSuccPred() {
		for (KnowledgeSourceLevel level1 : KnowledgeSourceLevel.values()) {
			if(level1.hasSucc()) assertEquals("pred() is not the right inverse of succ().", level1, level1.succ().pred());
			if(level1.hasPred()) assertEquals("pred() is not the left inverse of succ()." + level1, level1, level1.pred().succ());
			for (KnowledgeSourceLevel level2 : KnowledgeSourceLevel.values()) {
				assertEquals("CompareTo output is not the ordinal difference. ",level1.ordinal() - level2.ordinal(), level1.compareTo(level2));
				if ((level1.ordinal() - level2.ordinal()) == 1) {
					assertEquals("Successor is not the level with ordinal incremented by 1", level1, level2.succ());
				} else {
					if (level2.hasSucc()) {
						assertFalse("Successor is a level that does not have ordinal incremented by 1", level1.equals(level2.succ()));
					}
				}
				if ((level1.ordinal() - level2.ordinal()) == -1) {
					assertEquals("Predecessor is not the level with ordinal decremented by 1", level1, level2.pred());
				} else {
					if (level2.hasPred()) {
						assertFalse("Predecessor is a level that does not have ordinal decremented by 1", level1.equals(level2.pred()));
					}
				}
			}

		}
		
	}
	


}
