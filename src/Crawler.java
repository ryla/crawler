<<<<<<< HEAD
package de.vogella.regex.test;

=======
import java.io.BufferedInputStream;
import java.io.BufferedReader;
>>>>>>> 374bb03edcc48478e179ec9f05885cf3e88f87e1
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashSet;
<<<<<<< HEAD
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
=======
import java.util.Set;
>>>>>>> 374bb03edcc48478e179ec9f05885cf3e88f87e1

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
