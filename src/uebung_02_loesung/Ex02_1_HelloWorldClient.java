package uebung_02_loesung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Ex02_1_HelloWorldClient {

    public static void main(String[] args) throws IOException, InterruptedException {
//        if (args.length != 1) {
//            System.err.println("Usage: java Ex02_1_HelloWorldClient string");
//            System.exit(1);
//        }

        try (Socket s = new Socket()) {
            s.connect(new InetSocketAddress(InetAddress.getLocalHost(), Ex02_1_HelloWorldServer.PORT), 500);

            try (PrintWriter out = new PrintWriter(s.getOutputStream());
                BufferedReader is = new BufferedReader(new InputStreamReader(s.getInputStream()))) {
                // Thread.sleep(5000);
                out.println("Hello World");
                out.flush();
                System.out.println(is.readLine());
            }
        }
    }
}
