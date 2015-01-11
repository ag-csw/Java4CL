package elevation;

import api4kbj.FocusedImmutableLanguageEnvironment;
import api4kbj.KnowledgeSourceLevel;

public interface SelfLowering extends SelfElevating, Lowerable {

	Lowerer lowerer();

	Liftable lower(Object... args);

	default Liftable lower(KnowledgeSourceLevel level, Object... args) {
		return lower(this.defaultEnvironment(), level, args);
	}

	default Liftable lower(FocusedImmutableLanguageEnvironment e,
			KnowledgeSourceLevel level, Object... args) {
		return lowerer().lower(this, e, level, args);
	}

}
