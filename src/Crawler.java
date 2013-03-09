import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

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
		BufferedReader reader = null;
		try {
			conn = (HttpURLConnection) this.url.openConnection();
			conn.connect();
			reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			int value = reader.read();
			while (value != -1){
				this.rawContents += (char) value;
				value = reader.read();
			}
		} catch (IOException e) {
			this.rawContents = "";
		} finally {
			conn.disconnect();
		}
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
