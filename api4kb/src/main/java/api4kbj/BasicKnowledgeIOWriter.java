package api4kbj;

import api4kbc.KRRFileStoreConfiguration;
import functional.FileWriter;

public interface BasicKnowledgeIOWriter extends KnowledgeIO, BasicKnowledgeResource {

	KRRFileStoreConfiguration config();

	@Override
	default boolean usesStoreConfiguration(final KRRStoreConfiguration config){
		return config().equals(config);
	}

	FileWriter writer();

}