package uebung_01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;

public class Aufgabe3_ClientServer {

	public static void main(String[] args) throws IOException {
//		 if (args.length != 1) {
//	            System.err.println("Usage: java Ex01_3_Client <StringToReverse>");
//	            System.exit(1);
//	        }
		String param = "My name is Zafer";

	        System.out.println("[URLConnectionClient]Connect");
	        URL url = new URL("http://localhost:" + Ex01_3_Server.PORT);
	        URLConnection connection = url.openConnection();
	        connection.setDoOutput(true);

	        try (PrintWriter toServer = new PrintWriter(connection.getOutputStream())) {
	            System.out.println("[URLConnectionClient]Send");
	            toServer.println("String=" + param);
	        }

	        try (BufferedReader fromServer = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
	            System.out.println("[URLConnectionClient]Read");
	            String reversed = fromServer.readLine();
	            reversed=fromServer.readLine();
	            System.out.println("[URLConnectionClient]String reversed: " + reversed);

	        }
	}
}
