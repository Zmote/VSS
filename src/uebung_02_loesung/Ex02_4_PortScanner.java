package uebung_02_loesung;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Ex02_4_PortScanner {

    private static class ScanResult {
        public final int port;
        public final boolean isOpen;

        ScanResult(int port, boolean isOpen) {
            this.port = port;
            this.isOpen = isOpen;
        }
    }

    public static void main(final String[] args) throws ExecutionException, InterruptedException {
        ExecutorService es = Executors.newFixedThreadPool(20);
        List<Future<ScanResult>> results = new ArrayList<>();

        for (int port = Integer.parseInt(args[1]); port <= Integer.parseInt(args[2]); port++) {
            final int currentPort = port;
            results.add(es.submit(new Callable<ScanResult>() {
                @Override
                public ScanResult call() throws Exception {
                    try (Socket s = new Socket()) {
                        s.connect(new InetSocketAddress(args[0], currentPort), 200);
                        return new ScanResult(currentPort, true);
                    } catch (Exception ex) {
                        return new ScanResult(currentPort, false);
                    }
                }
            }));
        }

        es.shutdown();

        for (Future<ScanResult> f : results) {
            ScanResult sr = f.get();
            if (sr.isOpen) {
                System.out.printf("Port %d is open\n", sr.port);
            }
        }
    }
}
