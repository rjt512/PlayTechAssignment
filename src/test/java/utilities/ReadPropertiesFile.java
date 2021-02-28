package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadPropertiesFile {
	static Properties prop = new Properties();

	public ReadPropertiesFile() {
		getProperties();
	}

	public static void getProperties() {

		File file = new File("./propertyFile.properties");
							 		
		FileInputStream fileInput = null;
		try {
			fileInput = new FileInputStream(file);

			prop.load(fileInput);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String returnPropertyValue(String key) {
		return prop.getProperty(key);
	}

}
