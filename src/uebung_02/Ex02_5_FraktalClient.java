package uebung_02;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class Ex02_5_FraktalClient {

    public static void main(String[] args) throws Exception {
        ImageIcon icon = fetchFraktalFromServer();
        showInWindow(icon);
    }

    private static void showInWindow(final ImageIcon icon) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame("Apfelmännchen");
                frame.getContentPane().add(new ImagePanel(icon));
                frame.setSize(icon.getIconWidth() + 6, icon.getIconHeight() + 25);
                frame.setResizable(false);
                frame.addWindowListener(new WindowAdapter() {
                    public void windowClosing(WindowEvent we) {
                        System.exit(0);
                    }
                });
                frame.setVisible(true);
            }
        });
    }

    private static ImageIcon fetchFraktalFromServer() throws IOException, ClassNotFoundException {
		return null;
        //TODO Uebergabe der Hilfsklasse zur Berechnung des Fraktals an den Server
        //TODO dann berechnetes ImageIcon entgegennehmen und mit showInWindow darstellen

    }

    private static class ImagePanel extends JPanel {
        private static final long serialVersionUID = 1L;
        private final ImageIcon image;

        public ImagePanel(ImageIcon image) {
            this.image = image;
        }

        public void paintComponent(Graphics g) {
            g.drawImage(image.getImage(), 0, 0, this);
        }
    }
}