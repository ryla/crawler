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
		Crawler c = crawlerBuilder();
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
	
	@Test
	public void urlTest() {
		Crawler c = crawlerBuilder();
		assertEquals(1, c.getURLSet().size());
		try {
			assertTrue(c.getURLSet().contains(new URL("https://github.com/ryla/crawler")));
		} catch (MalformedURLException e) {
			fail("Malformed URL");
		}
	}
	
	@Test
	public void contentsTest() {
		Crawler c = crawlerBuilder();
		String exp = "Test Page Test Page This is a test HTML page for Crawler. Click here to go to its Github repo.";
		assertEquals(exp, c.getContents());
	}
	
	public Crawler crawlerBuilder() {
		return crawlerBuilder("https://raw.github.com/ryla/crawler/master/test.html");
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
	
	@Test
	public void nonRaw(){
		Crawler c = new Crawler();
		assertEquals(c.getContents(), " t es t  as.!?aaa d   ");
	}

}
