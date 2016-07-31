package uebung_02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Ex01_aClient {
	public static void main(String[] args) throws IOException, InterruptedException {
		int times = 1000;
		while(times > 0){
		 try (Socket s = new Socket()) {
	            s.connect(new InetSocketAddress(InetAddress.getLocalHost(), Ex03_DaytimeScanner.PORT), 500);

	            try (PrintWriter out = new PrintWriter(s.getOutputStream());
	                BufferedReader is = new BufferedReader(new InputStreamReader(s.getInputStream()))) {
	                // Thread.sleep(5000);
	                out.println("Hello World");
	                out.flush();
	                System.out.println(is.readLine());
	            }
	        }
		 times--;
		}
	}
}
