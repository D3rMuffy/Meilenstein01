package utils;

public interface ColSolvingUtil {
	/**
	* Beinhaltet hilfreiche Methoden,
	* um Sudokus loesen zu koennen.
	* Die Methoden beziehen sich auf die Einheit ’Col’.
	*/
	
	//Feasibility and Whitespaces
	boolean isValidCol(Grid grid, Cell anchor);
	List<Cell> getColWhiteSpaces(Grid grid, Cell anchor);
	
	//Singles
	//FullHouse Singles
	boolean hasFullHouseCol(Grid grid);
	boolean isFullHouseCol(Grid grid, Cell anchor);
	
	//NakedSingles
	boolean isColWithNakedSingleCell(Grid grid, Cell anchor);
	Cell getColMinimalNakedSingleCell(Grid grid, Cell anchor);
	
	//HiddenSingles
	boolean isColWithHiddenSingleCell(Grid grid, Cell anchor);
	Cell getColMinimalHiddenSingleCell(Grid grid, Cell anchor);
	
	//Pairs
	//Naked Pairs
	boolean isColWithNakedPairCells(Grid grid, Cell anchor);
	Cell[] getColMinimalNakedPairCells(Grid grid, Cell anchor);
	
	//HiddenPairs
	boolean isColWithHiddenPairCells(Grid grid, Cell anchor);
	Cell[] getColMinimalHiddenPairCells(Grid grid, Cell anchor);
	
	//ColBasedSolver
	List<Grid> solveColBased(Grid grid);
}
