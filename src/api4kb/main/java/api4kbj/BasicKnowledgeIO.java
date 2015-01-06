package api4kbj;

import java.io.InputStream;

import functional.IO;

public interface BasicKnowledgeIO extends KnowledgeIO, BasicKnowledgeResource {

	// getter for item
	// TODO this needs work
	InputStream input();

	// getter for format
	KRRFormat format();

	<R extends InputStream> IO<R> build(Class<R> clazz);

}