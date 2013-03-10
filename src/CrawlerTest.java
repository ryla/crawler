import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Test;


public class CrawlerTest {

	@Test
	public void rawTest() {
		Crawler c = crawlerBuilder("https://raw.github.com/ryla/crawler/master/test.html");
		try {
			FileReader f = new FileReader("test.html");
			String s = "";
			int i = f.read();
			while (i != -1) {
				s += (char) i;
				i = f.read();
			}
			f.close();
			assertEquals(s, c.getRawContents());
		} catch (FileNotFoundException e) {
			fail("File Not Found");
		} catch (IOException e) {
			fail("IO Error");
		}
	}
	
	public Crawler crawlerBuilder(String url) {
		try {
			URL u = new URL(url);
			Crawler c = new Crawler(u);
			c.fetch();
			return c;
		} catch (MalformedURLException e) {
			fail("Malformed URL");
		}
		return null;
	}

}
