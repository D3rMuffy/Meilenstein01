package utils;

import data.Cell;
import data.Grid;

public interface BlockIsoUtil { 
	/** 
	*Beinhaltet hilfreiche Methoden, 
	* um mit zueinander isomorphen Sudokus zu arbeiten. 
	* Methoden beziehen sich auf die Einheit ’Block’. 
	*/
	
	//BlockPermutation
	void applyBlockPermutation(Grid grid, Cell[] image);
	boolean isBlockPermutation(Grid grid1, Grid grid2);
	Cell[] getBlockPermutationImage(Grid grid1, Grid grid2);
	
	//ValuePermutation
	void applyBlockValuePermutation(Grid grid, Cell anchor, int[] image);
	int[] getBlockValuePermutationImage(Grid grid1, Grid grid2, Cell anchor);
	// Achtung: Diese Methode ist neu! (2018 05 03)
	boolean isBlockValuePermutation(Grid grid1, Grid grid2, Cell anchor);


}

