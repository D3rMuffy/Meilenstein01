package utils;

import data.Cell;
import data.Grid;

public interface RowIsoUtil {
	/*
	* Beinhaltet hilfreiche Methoden,
	* um mit zueinander isomorphen Sudokus zu arbeiten. 
	* Methoden beziehen sich auf die Einheit Row (also Zeile). 
	*/
	
	//BlockInternRow
	void applyBlockInternRowPermutation(Grid grid, Cell anchor, int[] image);
	boolean isBlockInternRowPermutation(Grid grid1, Grid grid2);
	int[] getBlockInternRowPermutationImage(Grid grid1, Grid grid2, Cell anchor);
	
	//ValuePermutation
	void applyRowValuePermutation(Grid grid, Cell anchor, int[] image);
	int[] getRowValuePermutationImage(Grid grid1, Grid grid2, Cell anchor);
	// Achtung: Diese Methode ist neu! (2018 05 03)
	boolean isRowValuePermutation(Grid grid1, Grid grid2, Cell anchor);

}
