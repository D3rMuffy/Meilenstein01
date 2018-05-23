/*
 * Copyright (c) 2018.
 * Der Code wurde fuer das Institut fuer Informatik der Universitaet zu Koeln erstellt.
 * Dieser Code oder Teile des Codes duerfen nur fuer die Lehre verwendet werden.
 * Die Veroeffentlichung sowie die Weitergabe des Codes ist nur Lehrbeauftragen des Instituts fuer Informatik gestattet.
 * Autor: Philipp Klinke (klinke@informatik.uni-koeln.de)
 */

package data;

/**
 * Kapselt alle Informationen zu einer Zelle innerhalb des Gitternetzes.
 * Eine Zelle wird zu Beginn in einem Gitternetz platziert und aendert seine Position nicht.
 * Aus diesem Grund sind rIndex und cIndex als final deklariert und es gibt keine Setter.
 * Man kann jedoch den Wert einer Zelle veraendern.
 * @author Philipp Klinke (klinke@informatik.uni-koeln.de)
 */
public class Cell {
	private int value = -1;
	private final int rIndex;
	private final int cIndex;

	/**
	 * @param rIndex Zeilenindex an dem sich die Zelle befindet.
	 * @param cIndex Spaltenindex an dem sich die Zelle befindet.
	 */
	public Cell(int rIndex, int cIndex) {
		this.rIndex = rIndex;
		this.cIndex = cIndex;
	}

	/**
	 * Gibt den Nutzwert der Zelle zurueck. Also den Wert den ein Spieler in einem Sudokufeld eintragen wuerde.
	 * @return Wert der Zelle [0.. 9]
	 */
	public int getValue() {
		return value;
	}

	/**
	 * Setzt den Wert der Zelle den ein Spieler in eine Sudokufeld eintragen wuerde.
	 * @param value Wertebereich [0.. 9]
	 */
	public void setValue(int value) {
		this.value = value;
	}

	/**
	 * Der Zeilenindex der die Position der Zelle angibt.
	 * @return Zeilenindex mit Wertebereich [1.. n]
	 */
	public int getrIndex() {
		return rIndex;
	}

	/**
	 * Der Spaltenindex der die Position der Zelle angibt.
	 * @return Spaltenindex mit Wertebereich [1.. n]
	 */
	public int getcIndex() {
		return cIndex;
	}

	@Override
	public String toString() {
		return  Integer.toString(value);
	}
}
