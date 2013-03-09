import java.net.URL;
import java.util.ArrayList;

public class Crawler {

	private String contents;
	private String rawContents;
	private URL url;
	private ArrayList<URL> urlList;

	public Crawler() {
		this(null);
	}
	
	public Crawler(URL url) {
		this.contents = "";
		this.rawContents = "";
		this.url = url;
		this.urlList = new ArrayList<URL>();
	}
	
	public ArrayList<URL> getURLList() {
		return urlList;
	}
	
	public String getContents() {
		return contents;
	}
	
	public URL getURL() {
		return url;
	}
	
	public void setURL(URL url) {
		this.url = url;
	}

}
