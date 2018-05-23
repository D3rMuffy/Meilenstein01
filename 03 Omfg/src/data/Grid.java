/*
 * Copyright (c) 2018.
 * Der Code wurde fuer das Institut fuer Informatik der Universitaet zu Koeln erstellt.
 * Dieser Code oder Teile des Codes duerfen nur fuer die Lehre verwendet werden.
 * Die Veroeffentlichung sowie die Weitergabe des Codes ist nur Lehrbeauftragen des Instituts fuer Informatik gestattet.
 * Autor: Philipp Klinke (klinke@informatik.uni-koeln.de)
 */

package data;

import java.util.Arrays;

/**
 * Repraesentiert das Gitternetz (also das Spielfeld) des Sudokus.
 * Dabei bestellt das Gitternetz aus einem Mehrdimensionalen Array mit Zellen.
 * Wichtig: Alle Indexwerte beginnen bei 1 und nicht - wie bei einem Array ueblich - bei 0.
 * Es wird davon ausgegangen, dass die Zellen (Cell) einmal bei Initialisierung des Grids gesetzt werden.
 * Entsprechend gibt es keine Methoden um Zellen zu setzen. Man kann jedoch einfach den Nutzwert (Value) (siehe Cell.java)
 * einer Zelle veraendern um so das Grid zu manipulieren.
 * @author Philipp Klinke (klinke@informatik.uni-koeln.de)
 */
public class Grid {

	private Cell[][] grid;

	/**
	 * Erzeugt das Gitternetz und initialisiert die Felder mit -1.
	 * @param dim Dimension des Gitters z. B. "4" bei 4 x 4
	 */
	public Grid(int dim){
		grid = new Cell[dim][dim];

		for (int rIndex = 0; rIndex < grid.length; rIndex++)
			for (int cIndex = 0; cIndex < grid.length; cIndex++)
				grid[rIndex][cIndex] = new Cell(rIndex + 1, cIndex + 1);
	}

	/**
	 * @param rIndex Index der Zeile
	 * @param cIndex Index der Spalte
	 * @return - Inhalt des Feldes
	 */
	public int getValue(int rIndex, int cIndex){
		return grid[rIndex - 1][cIndex - 1].getValue();
	}

	/**
	 * @param rIndex Index der Zeile
	 * @param cIndex Index der Spalte
	 * @param value Wert der eingefuegt werden soll
	 */
	public void setValue(int rIndex, int cIndex, int value){
		grid[rIndex - 1][cIndex - 1].setValue(value);
	}

	/**
	 * Gibt den Inhalt der rIndex-ten Zeile zurueck.
	 * @param rIndex Index der Zeile
	 * @return Inhalt der rIndex-te Zeile
	 */
	public int[] getRowValues(int rIndex){
		int[] ret = new int[grid.length];

		for (int cIndex = 0; cIndex < grid.length; cIndex++)
			ret[cIndex] = grid[rIndex - 1][cIndex].getValue();

		return ret;
	}

	/**
	 * @param rIndex Index der Zeile
	 * @param values Array mit Werten welche in die rIndex-te Zeile eingefuegt werden sollen
	 */
	public void setRowValues(int rIndex, int[] values){
		for (int cIndex = 0; cIndex < grid.length; cIndex++)
			grid[rIndex - 1][cIndex].setValue(values[cIndex]);
	}

	/**
	 * Gibt den Inhalt der cIndex-ten Spalte zurueck.
	 * @param cIndex Index der Spalte
	 * @return Inhalt der cIndex-ten Spalte
	 */
	public int[] getColValues(int cIndex){
		int[] ret = new int[grid.length];

		for (int rIndex = 0; rIndex < grid.length; rIndex++)
			ret[rIndex] = grid[rIndex][cIndex - 1].getValue();

		return ret;
	}

	/**
	 * @param cIndex Index der Spalte
	 * @param values Array mit Werten welche in die cIndex-te Spalte eingefuegt werden sollen
	 */
	public void setColValues(int cIndex, int[] values){
		for (int rIndex = 0; rIndex < grid.length; rIndex++)
			grid[rIndex][cIndex - 1].setValue(values[rIndex]);
	}

	/**
	 * Gibt die jeweilige Zelle an der Stelle [rIndex][cIndex] zurueck.
	 * @param rIndex Index der Spalte
	 * @param cIndex Index der zeile
	 * @return Zelle an der Stell [rIndex][cIndex]
	 */
	public Cell getCell(int rIndex, int cIndex){
		return grid[rIndex - 1][cIndex - 1];
	}

	/**
	 * Gibt String-Repraesentation des Gitters zurueck.
	 * @return String-Repraesentation
	 */
	@Override
	public String toString(){
		return Arrays.deepToString(grid);
	}

	/**
	 * Gibt den Inhalt des Gitternetzes auf der Konsole aus.
	 */
	public void print() {
		for (Cell[] row : grid) System.out.println(Arrays.deepToString(row));
	}
}
