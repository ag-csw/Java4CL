/**
 * 
 */
package api4kba;

import java.io.ByteArrayOutputStream;

import functional.FileWriter;
import api4kbj.BasicKnowledgeIOWriter;
import api4kbj.BasicKnowledgeEncoding;
import api4kbc.KRRFileStoreConfiguration;


/**
 * @author taraathan
 *
 */
public abstract class AbstractBasicKnowledgeIOWriter implements BasicKnowledgeIOWriter {


	public AbstractBasicKnowledgeIOWriter(final KRRFileStoreConfiguration config,
			BasicKnowledgeEncoding ke) {
		super();
		this.config = config;
		this.ke = ke;
	}

	private KRRFileStoreConfiguration config;
	private BasicKnowledgeEncoding ke;

	@Override
	public KRRFileStoreConfiguration config() {
		return config;
	}

	public FileWriter writer(){
		byte[] bytes = ke.toByteArray();
		return new FileWriter(config().path(), bytes);
	}


}
