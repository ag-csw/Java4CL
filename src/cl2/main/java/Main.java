import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

	private static final Logger LOG = LoggerFactory.getLogger(Main.class);
	
	public static void main(String[] args) {
		LOG.debug("Hello. This is the Java4CL package. The current time is {}.", System.currentTimeMillis());
	}

}