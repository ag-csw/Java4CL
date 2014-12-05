package api4kb;

import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Element;

public final class API4KB {

	API4KB() {}
	protected final Logger LOG = LoggerFactory.getLogger(getClass());
	
	public static EncodingSystem<Element, InputStream> EncodingSystemXMLUTF8 =
			new EncodingSystem<Element, InputStream>() {

				@Override
				public InputStream code(Element t) {
					//TODO
					return null;
				}

				@Override
				public Element decode(InputStream s) {
					//TODO
					return null;
				}
		
	};

}
