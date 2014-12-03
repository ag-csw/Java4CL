package cl2;

import api4kb.ImmutableEnvironment;
import api4kb.KRRDialect;
import api4kb.DialectIncompatibleException;
import api4kb.KRRLanguage;
import api4kb.KnowledgeAsset;
import api4kb.KnowledgeManifestation;

public class CLNameExpression implements CLExpression, CLName {

	public CLNameExpression() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public CLCommentExpression comment() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CLPrefixExpression[] prefixes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String symbol() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <T> KnowledgeManifestation<?> manifest(KRRDialect<T> dialect)
			throws DialectIncompatibleException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void clearManifest(KRRDialect<?> dialect) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public KnowledgeAsset conceptualize(ImmutableEnvironment e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void clearConceptualize(ImmutableEnvironment environment) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public KRRLanguage getLanguage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void clearManifest() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clearAsset() {
		// TODO Auto-generated method stub
		
	}

}
