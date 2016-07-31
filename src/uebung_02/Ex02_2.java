package uebung_02;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;


public class Ex02_2{

    public static void main(String[] args) throws IOException {
//        if (args.length != 2) {
//            System.err.println("Usage: java Ex02_2_HttpClient host path");
//            System.exit(1);
//        }
    	String host = "www.palazzo-castelmur.ch";
    	String filePath = "palazzo.html"; //ohne Host

        try (Socket s = new Socket(host, 80);
             OutputStreamWriter out = new OutputStreamWriter(s.getOutputStream());
             BufferedReader is = new BufferedReader(new InputStreamReader(s.getInputStream()))) {

            out.write("GET /" + filePath + " HTTP/1.\r\n");
            out.write("Host: " + host + "\r\n");
            out.write("\r\n");
            out.flush();

            int c;
            while ((c = is.read()) != -1) {
                System.out.print((char) c);
            }
        }
    }
}
