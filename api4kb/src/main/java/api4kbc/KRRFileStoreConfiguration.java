package api4kbc;

import java.nio.charset.Charset;
import java.nio.file.Path;

import api4kbj.KRRFormat;
import api4kbj.KRRStoreConfiguration;

public class KRRFileStoreConfiguration implements KRRStoreConfiguration {


	public KRRFileStoreConfiguration(final String name, final KRRFormat format, final Path path) {
		super();
		this.name = name;
		this.format = format;
		this.path = path;
	}

	private String name;
	private KRRFormat format;
	private Path path;

	@Override
	public String name() {
		return name;
	}

	public Path path() {
		return path;
	}

	@Override
	public KRRFormat format() {
		return format;
	}

	public Charset charset() {
		return format.charset();
	}



}
