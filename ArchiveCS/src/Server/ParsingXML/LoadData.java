package Server.ParsingXML;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import general.person.Person;



/**
 * The Class LoadData.
 */
public abstract class LoadData {

	/**
	 * Unzips personal data and calls method load from extended classes
	 *
	 * @param fileName the file name
	 * @return the loaded personal data
	 */
	public Person loadPersonalData(String fileName) {
		Person person = null;
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(new String("D:\\����\\���\\ArchiveCS\\Catalog\\" + fileName + ".zip"));

		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		ZipInputStream zin = new ZipInputStream(fis);
		ZipEntry entry;
		try {
			zin.closeEntry();
			while ((entry = zin.getNextEntry()) != null) {
				if (entry.getName().equals((new String(fileName + ".xml")))) {
					person = load(zin);
					break;
				}
			}
			zin.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return person;
	}

	/**
	 * Loads personal data using specified parser
	 *
	 * @param zin the ZipInputStream, which contains stream to the needed file
	 * @return the personal data
	 */
	protected abstract Person load(ZipInputStream zin);
}
