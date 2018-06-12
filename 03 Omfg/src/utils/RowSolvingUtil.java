package utils;



import java.util.List;

import data.Cell;
import data.Grid;

public interface RowSolvingUtil {
	/**
	* Beinhaltet hilfreiche Methoden,
	* um Sudokus loesen zu koennen.
	* Die Methoden beziehen sich auf die Einheit Row.
	*/
	
	//Feasibility and Whitespaces
	boolean isValidRow(Grid grid, Cell anchor);
	List<Cell> getRowWhiteSpaces(Grid grid, Cell anchor);
	
	//Singles
	//FullHouse Singles
	boolean hasFullHouseRow(Grid grid);
	boolean isFullHouseRow(Grid grid, Cell anchor);
	
	//NakedSingles
	boolean isRowWithNakedSingleCell(Grid grid, Cell anchor);
	Cell getRowMinimalNakedSingleCell(Grid grid, Cell anchor);
	
	//HiddenSingles
	boolean isRowWithHiddenSingleCell(Grid grid, Cell anchor);
	Cell getRowMinimalHiddenSingleCell(Grid grid, Cell anchor);
	
	//Pairs
	//Naked Pairs
	boolean isRowWithNakedPairCells(Grid grid, Cell anchor);
	Cell[] getRowMinimalNakedPairCells(Grid grid, Cell anchor);
	
	//HiddenPairs
	boolean isRowWithHiddenPairCells(Grid grid, Cell anchor);
	Cell[] getRowMinimalHiddenPairCells(Grid grid, Cell anchor);
	
	//RowBasedSolver
	List<Grid> solveRowBased(Grid grid);
}
