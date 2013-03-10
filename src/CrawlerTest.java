import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Test;


public class CrawlerTest {

	@Test
	public void fetchTest() {
		FileReader f = null;
		try {
			URL u = new URL("https://raw.github.com/ryla/crawler/master/test.html");
			Crawler c = new Crawler(u);
			c.fetch();
			f = new FileReader("test.html");
			String s = "";
			int i = f.read();
			while (i != -1) {
				s += (char) i;
				i = f.read();
			}
			f.close();
			assertEquals(s, c.getRawContents());
		} catch (MalformedURLException e) {
			fail("Malformed URL");
		} catch (FileNotFoundException e) {
			fail("File Not Found");
		} catch (IOException e) {
			fail("IO Error");
		}
	}

}
