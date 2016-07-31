package uebung_02_loesung;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;


public class Ex02_2_HttpClient {

    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            System.err.println("Usage: java Ex02_2_HttpClient host path");
            System.exit(1);
        }

        try (Socket s = new Socket(args[0], 80);
             OutputStreamWriter out = new OutputStreamWriter(s.getOutputStream());
             BufferedReader is = new BufferedReader(new InputStreamReader(s.getInputStream()))) {

            out.write("GET /" + args[1] + " HTTP/1.\r\n");
            out.write("Host: " + args[0] + "\r\n");
            out.write("\r\n");
            out.flush();

            int c;
            while ((c = is.read()) != -1) {
                System.out.print((char) c);
            }
        }
    }
}