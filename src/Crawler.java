import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.HttpURLConnection;
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
	
	public void fetch() {
		HttpURLConnection conn = null;
		try {
			conn = (HttpURLConnection) this.url.openConnection();
			conn.connect();
			InputStream in = conn.getInputStream();
			int value = in.read();
			while (value != -1){
				value = in.read();
				this.rawContents += (char) value;
			}
		} catch (IOException e) {
			this.rawContents = "";
		} finally {
			conn.disconnect();
		}
		System.out.print(this.rawContents);
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
