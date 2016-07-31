package uebung_02;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.MemoryImageSource;

import javax.swing.ImageIcon;

/**
 * Die Klasse berechnet ein Fraktal aus der JuliaMenge, in diesem Fall das
 * sogenannte Apfelmännchen.
 */
public class Ex02_5_FraktalGenerator {

	/**
	 * Berechnet das Fraktal mit den angegebenen Koordinaten, der angegebenen
	 * Breite in Pixel sowie der maximalen Rechentiefe pro Punkt.
	 * 
	 * Die Höhe des Bildes wird berechnet, so dass es zu keinen Verzerrungen
	 * kommt. Die Funktion liefert ein ImageIcon mit dem berechneten Bild.
	 */
	public ImageIcon calcFraktal(double xMin, double xMax, double yMin,
			double yMax, int width, int maxDeep) {
		double x, y;
		int height = (int) ((double) width * (yMax - yMin) / (xMax - xMin));
		double dx = (xMax - xMin) / (width - 1);
		double dy = (yMax - yMin) / (height - 1);

		// Alle Bildpunkte berechnen
		int data[] = new int[width * height];
		int counter = 0;

		// von oben nach unten
		for (int i = 0; i < height; i++) {
			y = yMax - i * dy;

			// von links nach rechts
			for (int j = 0; j < width; j++) {
				x = xMin + j * dx;
				data[counter++] = calcPoint(x, y, maxDeep);
			}
		}

		// ersetzt Datenwerte durch RGB-Werte
		int red, blue, green, opaque;
		for (int i = 0; i < data.length; i++) {
			red = 0;
			blue = (data[i] * 16) % 255;
			green = (data[i] * 16) % 255;
			opaque = 255;
			if (data[i] == -1) {
				red = 0;
				blue = 0;
				green = 0;
			}
			data[i] = (opaque << 24) | (red << 18) | (green << 8) | blue;
		}

		// ImageIcon erzeugen
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image img = toolkit.createImage(new MemoryImageSource(width, height,
				data, 0, width));
		return new ImageIcon(img);
	}

	// Bestimmt die Anzahl Rekursionen für einen einzelnen Punkt.
	// Die Abbruchbedingungen sind die obere Grenze (1e6) oder maxDeep.
	private int calcPoint(double x, double y, int maxDeep) {
		double xtemp, r, xn, yn;
		xtemp = r = xn = yn = 0.0;
		int counter = 0;

		while (r < 1e6) {
			xtemp = xn;
			xn = xn * xn - yn * yn + x;
			yn = 2 * xtemp * yn + y;
			r = xn * xn + yn * yn;
			counter++;
			if (counter == maxDeep) {
				counter = -1;
				break;
			}
		}
		return counter;
	}
}
