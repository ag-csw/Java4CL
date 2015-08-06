package api4kbj;

import elevation.Liftable;
import elevation.Lowerable;
import api4kb.doc.annotation.OntologyClass;

@OntologyClass(value = "http://www.omg.org/spec/API4KB/API4KBTerminology/KnowledgeEncoding")
/**
 * Interface of knowledge encodings, which are liftable and lowerable knowledge
 * resources at the {@link KnowledgeSourceLevel.ENCODING} abstract level.
 * 
 * @author taraathan
 *
 */
public interface KnowledgeEncoding extends KnowledgeResource, Liftable,
		Lowerable {

	@Override
	public default KnowledgeSourceLevel level() {
		return KnowledgeSourceLevel.ENCODING;
	}

	boolean usesFormat(final KRRFormat format);

}
