import java.net.URL;
import java.util.Set;
import java.util.HashSet;

public class Crawler {

	private String contents;
	private String rawContents;
	private URL url;
	private HashSet<URL> urlSet;

	public Crawler() {
		this(null);
	}
	
	public Crawler(URL url) {
		this.contents = "";
		this.rawContents = "";
		this.url = url;
		this.urlSet = new HashSet<URL>();
	}
	
	public Set<URL> getURLSet() {
		return urlSet;
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
