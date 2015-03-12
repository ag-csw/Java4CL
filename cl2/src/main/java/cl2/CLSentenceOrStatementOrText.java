package cl2;

import functional.EqSet;

public abstract class CLSentenceOrStatementOrText extends CLExpression {

	abstract public CLSentenceOrStatementOrText insertComments(EqSet<CLCommentExpression> e);

}
