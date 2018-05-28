package utils;

import java.util.List;

import data.Cell;
import data.Grid;

public interface GridSolvingUtil {
	/**
	* Beinhaltet hilfreiche Methoden,
	* um Sudokus loesen zu koennen.
	*/
	
	//Feasibility and Whitespaces
	boolean isValid(Grid grid);
	List<Cell> getWhiteSpaces(Grid grid);
	
	//Singles
	//FullHouse Singles
	boolean hasFullHouseUnit(Grid grid);
	
	//NakedSingles
	boolean hasNakedSingleCell(Grid grid);
	Cell getMinimalNakedSingleCell(Grid grid);
	
	//HiddenSingles
	boolean hasHiddenSingleCell(Grid grid1, Cell anchor);
	Cell getMinimalHiddenSingleCell(Grid grid);
	
	//Pairs
	//Naked Pairs
	boolean hasNakedPair(Grid grid);
	Cell[] getMinimalNakedPairCells(Grid grid);
	
	//HiddenPairs
	boolean hasHiddenPairCells(Grid grid);
	Cell[] getMinimalHiddenPairCells(Grid grid);
}
