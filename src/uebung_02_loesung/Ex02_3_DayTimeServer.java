package uebung_02_loesung;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class Ex02_3_DayTimeServer {
    private static final int DAYTIME_PORT = 1313;

    public static void main(String[] args) throws Exception {
        try (ServerSocket ss = new ServerSocket(DAYTIME_PORT)) {
            while (true) {
                new DateTimeThread(ss.accept()).start();
            }
        }
    }

    private static class DateTimeThread extends Thread {
        private Socket cs;

        public DateTimeThread(Socket cs) {
            this.cs = cs;
        }

        public void run() {
            try (PrintWriter out = new PrintWriter(cs.getOutputStream(), true)) {
                out.println(new Date());
            } catch (IOException e) {
                throw new RuntimeException(e);
            } finally {
                try {
                    cs.close();
                } catch (IOException e) {
                    System.err.println("Couldn't close a socket, what's going on?");
                }
            }
        }
    }
}