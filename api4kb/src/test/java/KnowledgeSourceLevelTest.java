import static org.junit.Assert.*;

import org.junit.Test;

import api4kbj.KnowledgeSourceLevel;

public class KnowledgeSourceLevelTest {

	@Test
	public void levelsShouldHaveSpecificOrdinals() {
		assertEquals("The ordinal of ASSET should be 3", 3,
				KnowledgeSourceLevel.ASSET.ordinal());
		assertEquals("The ordinal of EXPRESSION should be 2", 2,
				KnowledgeSourceLevel.EXPRESSION.ordinal());
		assertEquals("The ordinal of MANIFESTATION should be 1", 1,
				KnowledgeSourceLevel.MANIFESTATION.ordinal());
		assertEquals("The ordinal of ENCODING should be 0", 0,
				KnowledgeSourceLevel.ENCODING.ordinal());
	}

	@Test
	public void successorAndPredecessorMethodsShouldBeInverses() {
		for (KnowledgeSourceLevel level1 : KnowledgeSourceLevel.values()) {
			if (level1.hasSucc())
				assertEquals("pred() is not the right inverse of succ().",
						level1, level1.succ().pred());
			if (level1.hasPred())
				assertEquals("pred() is not the left inverse of succ()."
						+ level1, level1, level1.pred().succ());

		}

	}

	@Test
	public void compareToReturnsTheDifferenceOfOrdinals() {
		for (KnowledgeSourceLevel level1 : KnowledgeSourceLevel.values()) {
			for (KnowledgeSourceLevel level2 : KnowledgeSourceLevel.values()) {
				assertEquals(
						"CompareTo output is not the ordinal difference. ",
						level1.ordinal() - level2.ordinal(),
						level1.compareTo(level2));
			}
		}
	}

	@Test
	public void successorShouldIncrementOrdinalByOne() {
		for (KnowledgeSourceLevel level1 : KnowledgeSourceLevel.values()) {
			for (KnowledgeSourceLevel level2 : KnowledgeSourceLevel.values()) {
				if ((level1.ordinal() - level2.ordinal()) == 1) {
					assertEquals(
							"Successor is not the level with ordinal incremented by 1",
							level1, level2.succ());
				} else {
					if (level2.hasSucc()) {
						assertFalse(
								"Successor is a level that does not have ordinal incremented by 1",
								level1.equals(level2.succ()));
					}
				}

			}
		}
	}

	public void predecessorShouldDecrementOrdinalByOne() {
		for (KnowledgeSourceLevel level1 : KnowledgeSourceLevel.values()) {
			for (KnowledgeSourceLevel level2 : KnowledgeSourceLevel.values()) {
				if ((level1.ordinal() - level2.ordinal()) == -1) {
					assertEquals(
							"Predecessor is not the level with ordinal decremented by 1",
							level1, level2.pred());
				} else {
					if (level2.hasPred()) {
						assertFalse(
								"Predecessor is a level that does not have ordinal decremented by 1",
								level1.equals(level2.pred()));
					}
				}
			}

		}

	}

}
