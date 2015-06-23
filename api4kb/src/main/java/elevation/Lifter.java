package elevation;

import api4kbj.KnowledgeSourceLevel;

public interface Lifter {

	Lowerable lift(Liftable kr,
			KnowledgeSourceLevel level,
			Object... args);


}