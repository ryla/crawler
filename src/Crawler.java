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
		parseContents();
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
		List<String> links = new ArrayList<String>();
		
		String cont=this.rawContents;
		Pattern pattern = Pattern.compile("<a\\b[^>]*href=\"[^>]*>(.*?)</a>");
		Pattern link = Pattern.compile("href=\'[^>]*\'>");
		Matcher matcher = pattern.matcher(cont);
		Matcher tagmatch = pattern.matcher(cont);
		String url;
		
		while (tagmatch.find()) {
		Matcher match = link.matcher(tagmatch.group());
		matcher.find();
		String lin = matcher.group().replaceFirst("href=\"", "")
		   .replaceFirst("\">", "");
		if (valid(link)) {
		    links.add(makeAbsolute(url, link));
		}
		  
		    return links;
	}

	private void parseContents(){
		//String input = "<asdf>t es t<asdf><asdfa sdf>as.!?aaa d<as><><A>";
		Pattern p = Pattern.compile("(<.*?>)");
		Matcher n = p.matcher(rawContents);
		String a = n.replaceAll(" ");
		Pattern b = Pattern.compile("\\s+");
		Matcher c = b.matcher(a);
		contents = c.replaceAll(" ");
	}
	
	
}
