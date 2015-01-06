package elevation;

import api4kbj.FocusedImmutableEnvironment;
import api4kbj.KnowledgeSourceLevel;

public interface SelfLifting extends Liftable, SelfElevating {

	Lifter lifter();

	Lowerable lift();

	default Lowerable lift(KnowledgeSourceLevel level) {
		return lift(this.defaultEnvironment(), level);
	}

	default Lowerable lift(FocusedImmutableEnvironment e,
			KnowledgeSourceLevel level) {
		return lifter().lift(this, e, level);
	}

}
