package functional;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;


public class FileWriter extends IOWriter {
	
	public FileWriter(Path path, byte[] bytes) {
		super();
		this.path = path;
		this.bytes = bytes;
	}

	private Path path;
	private byte[] bytes;
	
	@Override
	public void write() throws IOException{
	  FileOutputStream w = new FileOutputStream(path.toFile());
	  //TODO only appropriate for small files to write entire byte array at once
	  w.write(bytes);
	}

}
