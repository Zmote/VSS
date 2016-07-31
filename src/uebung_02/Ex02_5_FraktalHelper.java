package uebung_02;

import java.io.Serializable;

/**
 * Hilfsklasse, um die Parameter von calcFraktal() serialisiert über den Socket
 * zu übertragen.
 */
public class Ex02_5_FraktalHelper implements Serializable {
	private static final long serialVersionUID = 1L;

	public final double xMin;
	public final double xMax;
	public final double yMin;
	public final double yMax;
	public final int width;
	public final int maxDeep;

	public Ex02_5_FraktalHelper(double xMin, double xMax, double yMin, double yMax, int width, int maxDeep) {
		this.xMin = xMin;
		this.xMax = xMax;
		this.yMin = yMin;
		this.yMax = yMax;
		this.width = width;
		this.maxDeep = maxDeep;
	}
}
