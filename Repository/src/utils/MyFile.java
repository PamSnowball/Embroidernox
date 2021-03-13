package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.jar.JarEntry;

public class MyFile {
	private InputStream input = null;
	
	private String path;
	
	private String name;
	
	private String separate(String path) {
		return '/' + path;
	}
	
	private String getClassPath(Class<?> clazz) {
		return new File(clazz.getProtectionDomain().getCodeSource().getLocation().getPath()).getAbsolutePath();
	}
	
	public MyFile(JarEntry entry, String path) {
		this.path = getClassPath(entry.getClass()) + "/res/entities/" + path;
		
		this.name = entry.getName();
		
		this.input = entry.getClass().getResourceAsStream("/res/entities/" + path);
	}
	
	public MyFile(String path) {
		this.path = separate(path);
		String[] dirs = path.split("/");
		this.name = dirs[dirs.length - 1];
	}
	
	public MyFile(String... paths) {
		String[] dirs = this.path.split("/");
		
		this.name = dirs[dirs.length - 1];
		this.path = "";
		
		for (String string : paths) this.path = String.valueOf(this.path) + separate(string);
	}
	
	public MyFile(MyFile file, String subFile) {
		this.path = String.valueOf(file.path) + separate(subFile);
		this.name = subFile;
	}
	
	public MyFile(MyFile file, String... subFiles) {
		String[] dirs = this.path.split("/");
		
		this.name = dirs[dirs.length - 1];
		this.path = file.path;
		
		for (String subFile : subFiles) this.path = String.valueOf(this.path) + separate(subFile);
	}
	
	public String getPath() {
		return this.path;
	}
	
	public String toString() {
		return getPath();
	}
	
	public InputStream getInputStream() {
		if (input != null) {
			return input;
		} else {
			return getClass().getResourceAsStream(this.path);
		}
	}
	
	public URL getUrl() {
		return getClass().getResource(this.path);
	}
	
	public CSVReader openCsvReader() throws Exception {
		return new CSVReader(this);
	}
  
	public BufferedReader getReader() throws Exception {
		try {
			return new BufferedReader(new InputStreamReader(getInputStream()));
		} catch (Exception e) {
			System.err.println("Couldn't get reader for " + this.path);
			throw e;
		} 
	}
	
	public String getName() {
		return this.name;
	}
}
