package uebung_02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Ex01_aServer {
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
