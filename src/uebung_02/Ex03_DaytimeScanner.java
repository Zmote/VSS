package uebung_02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class Ex03_DaytimeScanner {
	public static int PORT = 1025;
	
	public static void main(String[] args) throws IOException {
		
		try(ServerSocket server = new ServerSocket(PORT)){
			while(true){
				new DayTimeThread(server.accept()).start();
			}
			
		}
	}
}

class DayTimeThread extends Thread{
	private Socket client;
	DayTimeThread(Socket client){
		this.client = client;
	}
	@Override
	public void run(){
		try(PrintWriter out = new PrintWriter(client.getOutputStream(),true);
			BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()))){
			String message = "no message received";
			try {
				message = in.readLine();
			} catch (Exception e) {
				e.printStackTrace();
			}
			Date date = new Date();
			out.println(date + ": " + message);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}






