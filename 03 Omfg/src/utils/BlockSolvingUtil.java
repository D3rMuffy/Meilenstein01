package utils;

import java.util.List;

import data.Cell;
import data.Grid;

public interface BlockSolvingUtil {
	/**
	* Beinhaltet hilfreiche Methoden,
	* um Sudokus loesen zu koennen.
	* Die Methoden beziehen sich auf die Einheit ’Block’.
	*/
	
	//Feasibility and Whitespaces
	boolean isValidBlock(Grid grid, Cell anchor);
	List<Cell> getBlockWhiteSpaces(Grid grid, Cell anchor);
	
	//Singles
	//FullHouse Singles
	boolean hasFullHouseBlock(Grid grid);
	boolean isFullHouseBlock(Grid grid, Cell anchor);
	
	//NakedSingles
	boolean isBlockWithNakedSingleCell(Grid grid, Cell anchor);
	Cell getBlockMinimalNakedSingleCell(Grid grid, Cell anchor);
	
	//HiddenSingles
	boolean isBlockWithHiddenSingleCell(Grid grid, Cell anchor);
	Cell getBlockMinimalHiddenSingleCell(Grid grid, Cell anchor);
	
	//Pairs
	//Naked Pairs
	boolean isBlockWithNakedPair(Grid grid, Cell anchor);
	Cell[] getBlockNakedPairCells(Grid grid, Cell anchor);
	
	//HiddenPairs
	boolean isBlockWithHiddenPair(Grid grid, Cell anchor);
	Cell[] getBlockHiddenPairCells(Grid grid, Cell anchor);
	
	//BlockBasedSolver
	List<Grid> solveBlockBased(Grid grid);
}
