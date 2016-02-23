package uebung_01;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Aufgabe2_URL {
	static int fileNumber = 1;
	
	public static void main(String[] args) throws IOException {
//		a)
//		outputURLInformation("http://www.hsr.ch");
//		outputURLInformation("https://wiki.selfhtml.org/wiki/HTML/Tabellen/Aufbau_einer_Tabelle#definieren");
//		outputURLInformation("https://unterricht.hsr.ch:443");
//		outputURLInformation("http://www.google.ch/#aq=1&aqi=g5&aql=&hl=en&q=hsr+rapperswil");
//		b)
//		outputHTML("http://www.20min.ch");
//		c)
		saveImage("http://vignette1.wikia.nocookie.net/dragonball/images/f/f8/Son_Goku.jpg/revision/latest?cb=20090420221446&path-prefix=de");
		saveImage("http://cdn.wegotthiscovered.com/wp-content/uploads/naruto_movie-lionsgate.jpg");
		
	}
	
	static byte[] readFully(InputStream input) throws IOException
	{
	    byte[] buffer = new byte[8192];
	    int bytesRead;
	    ByteArrayOutputStream output = new ByteArrayOutputStream();
	    while ((bytesRead = input.read(buffer)) != -1)
	    {
	        output.write(buffer, 0, bytesRead);
	    }
	    return output.toByteArray();
	}
	
	static void saveImage(String url) throws MalformedURLException, IOException {
		InputStream is= new URL(url).openStream();
		File file = new File("C:\\Users\\Zmote\\Desktop\\savedImageFromWeb" + fileNumber + ".jpg");
		fileNumber++;
		file.createNewFile();
		FileOutputStream fos = new FileOutputStream(file);
		fos.write(readFully(is));
		fos.flush();
		fos.close();
	}

	static void outputHTML(String url) throws IOException {
		URL myUrl = new URL(url);
		InputStream is = myUrl.openStream();
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		String line;
		while((line = br.readLine()) != null){
			System.out.println(line);
		}
	}

	static void outputURLInformation(String url) throws MalformedURLException{
		URL myUrl = new URL(url);
		System.out.println("URL: " + url);
		System.out.println("Protokoll: " + myUrl.getProtocol());
		System.out.println("Host: " + myUrl.getHost());
		System.out.println("Port: " + myUrl.getPort());
		System.out.println("Pfad: " + myUrl.getPath());
		System.out.println("Datei: " + myUrl.getFile());
		System.out.println("Referenz: " + myUrl.getRef());
		System.out.println("----------------------");
	}

}
