import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
	
	public String getRawContents() {
		return this.rawContents;
	}
	
	private void parseURL(){
		Pattern link;
		Pattern htmltag;
		String cont=this.rawContents;
		String s = "This is my test <a href='Can you find me?'> </a>";
		htmltag = Pattern.compile("<a//b[^>]*href= \"[^>]*>(.*?)</a>");
		link = Pattern.compile("href=\"[^>]*\">");
		
		Matcher tagmatch = htmltag.matcher(cont);
		while(tagmatch.find()){
			Matcher matcher = link.matcher(tagmatch.group());
			matcher.find();
			try {
				this.urlSet.add(new URL(this.url, link.toString()));
			} catch (MalformedURLException e) { }
		}
		
	}
	
	
}
