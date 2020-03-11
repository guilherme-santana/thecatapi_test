package suport;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ReadProperties {

	public Properties read() throws Exception, IOException {
		Properties prop = new Properties();
		try {
			File resourcesDirectory = new File("src/test/java/arquivoConfig/config.properties");
			prop.load(new FileInputStream(resourcesDirectory));
		} catch (IOException e) {
			e.printStackTrace();
			throw new Exception("Arquivo nao foi encontrado");
		}
		return prop;
	}

}
