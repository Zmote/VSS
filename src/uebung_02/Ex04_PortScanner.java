package uebung_02;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Ex04_PortScanner {
	
	private static final int START_PORT = 100;
	private static final int END_PORT = 6000;
	private static int port;
	
	public static void main(String[] args) throws IOException {
		for(port = START_PORT; port <= END_PORT;port++){
			new Thread(() -> {
				if(portIsOpen("localhost",port,0)){
					System.out.println("Port available at: " + port);
				}
			}).start();
		}
	}
	
	public static boolean portIsOpen(String ip, int port, int timeout) {
        try {
            Socket socket = new Socket();
            socket.connect(new InetSocketAddress(ip, port), timeout);
            socket.close();
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

}
