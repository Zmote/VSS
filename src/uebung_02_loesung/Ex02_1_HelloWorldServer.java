package uebung_02_loesung;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;

public class Ex02_1_HelloWorldServer {
    public static final int PORT = 9999;

    public static void main(String[] args) throws IOException, InterruptedException {
        try (ServerSocket ss = new ServerSocket(PORT)) {
            while (true) {
                try (Socket cs = ss.accept();
                     PrintWriter out = new PrintWriter(cs.getOutputStream(), true);
                     BufferedReader in = new BufferedReader(new InputStreamReader(cs.getInputStream()))) {

                    // cs.setSoTimeout(1000); // setting the socket timeout must happen before the first blocking read call

                    String input = in.readLine();
                    if (input == null || input.equals("bye")) {
                        break;
                    }

                    String reply = String.format("%s (myAddress=%s:%d, yourAddress=%s:%d)",
                            input,
                            ss.getInetAddress(),
                            ss.getLocalPort(),
                            cs.getInetAddress(),
                            cs.getPort());
                    out.println(reply);
                }
            }
        }
    }
}