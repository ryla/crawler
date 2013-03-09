import java.net.URL;

public class Crawler {

	private String contents;
	private URL url;
	
	public Crawler() {
		this.contents = "";
		this.url = null;
	}
	
	public Crawler(URL url) {
		this.contents = "";
		this.setURL(url);
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
