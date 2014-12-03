package api4kb;

import org.w3c.dom.Element;

import com.sun.org.apache.bcel.internal.util.ByteSequence;

public final class API4KB {

	API4KB() {}
	
	public static EncodingSystem<Element, ByteSequence> EncodingSystemXMLUTF8 =
			new EncodingSystem<Element, ByteSequence>() {

				@Override
				public ByteSequence code(Element t) {
					//TODO
					return null;
				}

				@Override
				public Element decode(ByteSequence s) {
					//TODO
					return null;
				}
		
	};

}
