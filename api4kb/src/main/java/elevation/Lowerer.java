package elevation;


import api4kbj.KnowledgeResource;
import api4kbj.KnowledgeSourceLevel;

public interface Lowerer {

	Liftable lower(KnowledgeResource kr, KnowledgeSourceLevel level,
			Object... args);


}