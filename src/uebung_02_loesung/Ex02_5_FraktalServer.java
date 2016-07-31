package uebung_02_loesung;



import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.ImageIcon;


public class Ex02_5_FraktalServer {
    public static final int PORT = 16666;

    public static void main(String[] args) throws Exception {

            try (ServerSocket ss = new ServerSocket(PORT)) {
                //TODO Hilfsklasse zur Fraktalberechnung vom Client entgegennehmen
                //TODO Mit Hilfe des Fraktalgenerators ImageIcon generieren lassen
                //TODO Rückgabe des ImageIcon an den Client
                Ex02_5_FraktalGenerator generator = new Ex02_5_FraktalGenerator();

                while (true) {
                    try (Socket cs = ss.accept();
                         ObjectInputStream in = new ObjectInputStream(cs.getInputStream());
                         ObjectOutputStream out = new ObjectOutputStream(cs.getOutputStream())) {

                        Object obj = in.readObject();

                        if (!(obj instanceof Ex02_5_FraktalHelper)) {
                            System.err.println("[FraktalServer] falsches Objekt erhalten");
                            continue;
                        }

                        Ex02_5_FraktalHelper r = (Ex02_5_FraktalHelper) obj;
                        ImageIcon image = generator.calcFraktal(r.xMin, r.xMax, r.yMin, r.yMax, r.width, r.maxDeep);
                        System.out.println("[FraktalServer] Bild berechnet");
                        out.writeObject(image);
                    }
                }
            }
        }
    
}
